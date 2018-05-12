package com.hsj86715.ingress.glyphres.view.practise;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hsj86715.ingress.glyphres.R;
import com.hsj86715.ingress.glyphres.data.HackList;

import cn.com.farmcode.utility.tools.Logger;
import cn.com.farmcode.utility.tools.TimeCounter;

import static com.hsj86715.ingress.glyphres.view.practise.DrawThread.MSG_STEP_CHANGE;
import static com.hsj86715.ingress.glyphres.view.practise.DrawThread.MSG_TRY_STEP_TIME;


/**
 * @author hushujun
 */
public class PractiseView extends SurfaceView implements SurfaceHolder.Callback, Handler.Callback,
        GestureDetector.OnGestureListener {
    public static final int STEP_PREPARE = 0;
    public static final int STEP_SHOW = 1;
    public static final int STEP_TRY = 2;
    public static final int STEP_STOP = 3;

    private static final int MINI_FLING_VELOCITY = 1000;

    @IntDef({STEP_PREPARE, STEP_SHOW, STEP_TRY, STEP_STOP})
    public @interface Step {
    }

    public interface Callback {
        /**
         * Called when the practise step change, this will happened inorder with {@link Step}.
         *
         * @param step
         */
        void onStepChanged(@Step int step);

        /**
         * Called in the {@link #STEP_TRY}, when user try draw an sequence end.
         *
         * @param hackIdx  the index of glyph in hack list
         * @param stepTime current glyph drawing time by user
         * @param result   current drawing is correct or not
         */
        void onTryStepEnd(int hackIdx, long stepTime, boolean result);

        /**
         * Called when the whole hack list drawing finished.
         *
         * @param totalTime    the total time from first glyph draw begin to the last glyph draw end.
         * @param tryStepCosts each glyph drawing time cost by user
         * @param results      practise result for each glyph in hack list.
         */
        void onPractiseEnd(long totalTime, long[] tryStepCosts, boolean[] results);

        /**
         * Called when fling the view with velocityX>300 in STOP step
         */
        void toNextHackList();

        /**
         * Called when fling the view with velocityX<-300 in STOP step
         */
        void toPreviousHackList();

        /**
         * Called when fling the view with Math.abs(velocityY)>300 in STOP step
         */
        void retryCurrentHackList();
    }

    private SurfaceHolder mHolder;
    private DrawThread mDrawThread;

    private float mLastX, mLastY;
    private Handler mUIHandler;

    private Callback mCallback;

    private GestureDetectorCompat mDetectorCompat;

    public PractiseView(Context context) {
        this(context, null);
    }

    public PractiseView(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.AppTheme);
    }

    public PractiseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PractiseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg == null || mCallback == null) {
            return false;
        }
        switch (msg.what) {
            case MSG_STEP_CHANGE:
                int step = msg.arg1;
                mCallback.onStepChanged(step);
                if (step == STEP_STOP && msg.obj != null) {
                    DrawThread.TryEndResult result = (DrawThread.TryEndResult) msg.obj;
                    mCallback.onPractiseEnd(result.totalTime, result.stepCosts, result.stepResults);
                }
                return true;
            case MSG_TRY_STEP_TIME:
                if (msg.obj != null) {
                    mCallback.onTryStepEnd(msg.arg1, (long) msg.arg2, (Boolean) msg.obj);
                }
                return true;
            default:
                return false;
        }
    }

    private void init(Context context, AttributeSet attrs) {
        mUIHandler = new Handler(context.getMainLooper(), this);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        //保持屏幕长亮
        setKeepScreenOn(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        mDetectorCompat = new GestureDetectorCompat(context, this);
    }

    public void setHackList(HackList hackList) {
        if (mDrawThread != null) {
            mDrawThread.setHackList(hackList);
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int miniWidth = getSuggestedMinimumWidth();
        final int miniHeight = getSuggestedMinimumHeight();
        int width = measureSize(miniWidth, widthMeasureSpec);
        int height = measureSize(miniHeight, heightMeasureSpec);
        int size = Math.min(width, height);
        setMeasuredDimension(size, (int) (size + 50 * getResources().getDisplayMetrics().density));
    }

    private int measureSize(int defWidth, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                defWidth = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                defWidth = Math.max(defWidth, specSize);
        }
        return defWidth;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDrawThread == null) {
            return super.onTouchEvent(event);
        }
        if (mDrawThread.isStopStep() && mCallback != null) {
            return mDetectorCompat.onTouchEvent(event);
        } else if (mDrawThread.isInTryStep()) {
            return handleTryMotionEvent(event);
        }
        return true;
    }

    private boolean handleTryMotionEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TimeCounter.stepStart();

                mDrawThread.mMovePath.clear();
                mLastX = x;
                mLastY = y;
                mDrawThread.mTouchPath.moveTo(mLastX, mLastY);

                mDrawThread.mMovePath.add(new float[]{mLastX, mLastY});
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {
                    mDrawThread.mTouchPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                    mLastX = x;
                    mLastY = y;

                    mDrawThread.mMovePath.add(new float[]{mLastX, mLastY});
                }
                break;
            case MotionEvent.ACTION_UP:
                long stepTime = TimeCounter.stepEnd();
                Logger.w("Try step cost:" + stepTime);

                if (mDrawThread != null) {
                    mDrawThread.tryNextSequence(stepTime);
                }
                mDrawThread.mTouchPath.reset();
                mDrawThread.mPossibleGlyphPath.reset();
                mDrawThread.mMovePath.clear();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (Math.abs(velocityX) > MINI_FLING_VELOCITY) {
                if (velocityX > 0) {
                    mCallback.toNextHackList();
                } else {
                    mCallback.toPreviousHackList();
                }
                return true;
            }
        } else if (Math.abs(velocityX) < Math.abs(velocityY)) {
            if (Math.abs(velocityY) > MINI_FLING_VELOCITY) {
                mCallback.retryCurrentHackList();
            }
        }
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mDrawThread == null) {
            mDrawThread = new DrawThread(getResources().getDisplayMetrics().density, getMeasuredWidth(), mHolder, mUIHandler);
        }
        mDrawThread.start();
        mDrawThread.isDrawing = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawThread.isDrawing = false;
    }
}
