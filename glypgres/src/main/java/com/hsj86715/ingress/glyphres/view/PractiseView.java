package com.hsj86715.ingress.glyphres.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hsj86715.ingress.glyphres.BuildConfig;
import com.hsj86715.ingress.glyphres.R;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.HackList;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.com.farmcode.utility.tools.Logger;
import cn.com.farmcode.utility.tools.TimeCounter;

import static com.hsj86715.ingress.glyphres.view.PractiseView.DrawRunnable.MSG_STEP_CHANGE;
import static com.hsj86715.ingress.glyphres.view.PractiseView.DrawRunnable.MSG_TRY_STEP_TIME;


/**
 * @author hushujun
 */
public class PractiseView extends SurfaceView implements SurfaceHolder.Callback, Handler.Callback {
    public static final int STEP_PREPARE = 0;
    public static final int STEP_SHOW = 1;
    public static final int STEP_TRY = 2;
    public static final int STEP_STOP = 3;

    private static final int MINI_FLING_VELOCITY = 500;
    private int DEFAULT_PADDING;
    /**
     * The touch event move edge, 5 px
     */
    private static final int EDGE_REGION = 5;
    private boolean isUped = false;

    private static final int BG_COLOR = Color.BLACK;
    private static final int PATH_COLOR = Color.parseColor("#87CEFA");
    private static final int CIRCLE_COLOR = Color.parseColor("#B0C4DE");

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
    private DrawRunnable mDrawRunnable;

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
                    TryEndResult result = (TryEndResult) msg.obj;
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
        DEFAULT_PADDING = (int) (15 * context.getResources().getDisplayMetrics().density);
        mUIHandler = new Handler(context.getMainLooper(), this);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        //保持屏幕长亮
        setKeepScreenOn(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        mDetectorCompat = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (Math.abs(velocityX) > MINI_FLING_VELOCITY) {
                        if (velocityX > 0) {
                            mCallback.toPreviousHackList();
                        } else {
                            mCallback.toNextHackList();
                        }
                        return true;
                    }
                } else if (Math.abs(velocityX) < Math.abs(velocityY)) {
                    if (Math.abs(velocityY) > MINI_FLING_VELOCITY) {
                        mCallback.retryCurrentHackList();
                        return true;
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    public void setHackList(HackList hackList) {
        if (mDrawRunnable != null) {
            mDrawRunnable.setHackList(hackList);
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
        if (mDrawRunnable == null) {
            return super.onTouchEvent(event);
        }
        if (mDrawRunnable.isStopStep() && mCallback != null && mDetectorCompat != null) {
            return mDetectorCompat.onTouchEvent(event);
        } else if (mDrawRunnable.isInTryStep()) {
            return handleTryMotionEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private boolean handleTryMotionEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isUped = false;
                TimeCounter.stepStart();

                mDrawRunnable.mMovePath.clear();
                mLastX = x;
                mLastY = y;
                mDrawRunnable.mTouchPath.moveTo(mLastX, mLastY);

                mDrawRunnable.mMovePath.add(new float[]{mLastX, mLastY});
                break;
            case MotionEvent.ACTION_MOVE:
                if (!TimeCounter.isIsStepStarted()) {
                    TimeCounter.stepStart();
                }
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {
                    if (mDrawRunnable.mTouchPath.isEmpty()) {
                        mDrawRunnable.mTouchPath.moveTo(x, y);
                    } else {
                        mDrawRunnable.mTouchPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                    }
                    mLastX = x;
                    mLastY = y;

                    mDrawRunnable.mMovePath.add(new float[]{mLastX, mLastY});
                }
                break;
            case MotionEvent.ACTION_UP:
                long stepTime = TimeCounter.stepEnd();
                Logger.w("Try step cost:" + stepTime);

                if (mDrawRunnable != null) {
                    mDrawRunnable.tryNextSequence(stepTime);
                    mDrawRunnable.mTouchPath.reset();
                    mDrawRunnable.mPossibleGlyphPath.reset();
                    mDrawRunnable.mMovePath.clear();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Logger.i("surfaceCreated holder=" + holder);
        if (mDrawRunnable == null) {
            mDrawRunnable = new DrawRunnable(getResources().getDisplayMetrics().density, getMeasuredWidth());
        } else {
            mDrawRunnable.setStep(STEP_PREPARE);
        }
        mDrawRunnable.isDrawing = true;
        new Thread(mDrawRunnable).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logger.i("surfaceChanged holder=" + holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Logger.i("surfaceDestroyed holder=" + holder);
        mDrawRunnable.isDrawing = false;
    }


    /**
     * The drawing runnable
     */
    class DrawRunnable implements Runnable {

        static final int MSG_STEP_CHANGE = 0;
        static final int MSG_TRY_STEP_TIME = 1;

        private boolean isDrawing = true;
        private static final int PREPARE_INTERVAL = 1000;
        private static final int SHOW_INTERVAL = 2000;
        private static final int TRY_INTERVAL = 30;
        private static final int STOP_INTERVAL = 150;

        private Canvas mCanvas;
        private Paint mTouchPaint, mPaint;
        private Path mTouchPath, mPossibleGlyphPath;

        private float mDensity;
        private List<PointF> mGlyphPoints = new ArrayList<>();
        private List<float[]> mMovePath = new CopyOnWriteArrayList<>();
        private SparseArray<PointF[]> mGlyphPath = new SparseArray<>();
        private List<PointF> mPossiblePoints;
        private SparseArray<PointF[]> mUserGlyphPath = new SparseArray<>();
        private long[] mUserStepCost;
        private boolean[] mUserStepResults;

        final float mPointRadius;
        final int mCenter;
        final float mRadius;

        private @PractiseView.Step
        int mStep = PractiseView.STEP_STOP;

        private int mPrepareCountDown = 3;
        private int mShowHackIdx = 0;
        private int mTryHackIdx = 0;

        private HackList mHackList;
        private long mCurrentListCostTime = 0;

        DrawRunnable(float density, int measuredWidth) {
//            super("Practise-Draw-Thread");
            mDensity = density;

            mTouchPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            mTouchPaint.setStrokeWidth(20f);
            mTouchPaint.setColor(Color.parseColor("#FF4081"));
            mTouchPaint.setStyle(Paint.Style.STROKE);
            mTouchPaint.setStrokeJoin(Paint.Join.ROUND);
            mTouchPaint.setStrokeCap(Paint.Cap.ROUND);
            mTouchPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));

            mPaint = new Paint();
            int maxWidth = measuredWidth - DEFAULT_PADDING * 2;

            mPointRadius = maxWidth / 15 > 10 * mDensity ? 10 * mDensity : maxWidth / 15;
            mCenter = measuredWidth / 2;
            mRadius = mCenter - DEFAULT_PADDING - mPointRadius;

            mTouchPath = new Path();
            mPossibleGlyphPath = new Path();
        }

        void setHackList(HackList hackList) {
            mHackList = hackList;
            mShowHackIdx = 0;
            mTryHackIdx = 0;
            mPrepareCountDown = 3;
            setStep(PractiseView.STEP_PREPARE);
            Message msg = mUIHandler.obtainMessage(MSG_STEP_CHANGE);
            msg.arg1 = PractiseView.STEP_PREPARE;
            mUIHandler.sendMessage(msg);
        }

        void setStep(@PractiseView.Step int step) {
            mStep = step;
        }

        void tryNextSequence(long stepTime) {
            if (mPossiblePoints != null) {
                mUserGlyphPath.put(mTryHackIdx, mPossiblePoints.toArray(new PointF[mPossiblePoints.size()]));
            }
            mUserStepCost[mTryHackIdx] = stepTime;
            mUserStepResults[mTryHackIdx] = compareResult(mTryHackIdx);

            Message msg = mUIHandler.obtainMessage(MSG_TRY_STEP_TIME);
            msg.arg1 = mTryHackIdx;
            msg.arg2 = (int) stepTime;
            msg.obj = mUserStepResults[mTryHackIdx];
            mUIHandler.sendMessage(msg);

            mTryHackIdx++;
            if (mTryHackIdx >= mHackList.getLength()) {
                mCurrentListCostTime = TimeCounter.stop();
                Logger.e("Try total cost:" + mCurrentListCostTime);
                setStep(PractiseView.STEP_STOP);

                Message message = mUIHandler.obtainMessage(MSG_STEP_CHANGE);
                message.arg1 = PractiseView.STEP_STOP;
                TryEndResult result = new TryEndResult();
                result.totalTime = mCurrentListCostTime;
                result.stepCosts = mUserStepCost;
                result.stepResults = mUserStepResults;
                message.obj = result;
                mUIHandler.sendMessage(message);

                mTryHackIdx = 0;
            }
        }

        boolean isInTryStep() {
            return mStep == PractiseView.STEP_TRY;
        }

        boolean isStopStep() {
            return mStep == PractiseView.STEP_STOP;
        }

        @Override
        public void run() {
            while (isDrawing) {
                mCanvas = mHolder.lockCanvas();
                if (mCanvas == null) {
                    continue;
                }
                int interval;
                switch (mStep) {
                    case PractiseView.STEP_PREPARE:
                        interval = PREPARE_INTERVAL;
                        break;
                    case PractiseView.STEP_SHOW:
                        interval = SHOW_INTERVAL;
                        break;
                    case PractiseView.STEP_TRY:
                        interval = TRY_INTERVAL;
                        break;
                    case PractiseView.STEP_STOP:
                    default:
                        interval = STOP_INTERVAL;
                        break;
                }
                long start = System.currentTimeMillis();
                draw(mCanvas);
                long end = System.currentTimeMillis();

                if (end - start < interval) {
                    try {
                        Thread.sleep(interval - (end - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void draw(Canvas canvas) {
            try {
                switch (mStep) {
                    case PractiseView.STEP_PREPARE:
                        drawPrepareStep(canvas, mPaint);
                        break;
                    case PractiseView.STEP_SHOW:
                        drawShowStep(canvas, mPaint);
                        break;
                    case PractiseView.STEP_TRY:
                        drawTryStep(canvas, mPaint);
                        break;
                    case PractiseView.STEP_STOP:
                    default:
                        drawStopStep(canvas, mPaint);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }

        private void drawPrepareStep(Canvas canvas, Paint paint) {
            Logger.i("drawPrepareStep, " + mPrepareCountDown);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            canvas.drawColor(BG_COLOR);

            Path path = new Path();
            for (int degree = 30; degree < 360; degree += 60) {
                float cx = (float) (Math.cos(Math.toRadians(degree)) * mRadius);
                float cy = (float) (Math.sin(Math.toRadians(degree)) * mRadius);
                if (degree == 30) {
                    path.moveTo((float) (cx + mCenter + Math.cos(Math.toRadians(degree)) * mPointRadius),
                            (float) (mCenter - cy - Math.sin(Math.toRadians(degree)) * mPointRadius));
                } else {
                    path.lineTo((float) (cx + mCenter + Math.cos(Math.toRadians(degree)) * mPointRadius),
                            (float) (mCenter - cy - Math.sin(Math.toRadians(degree)) * mPointRadius));
                }
            }

            path.close();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(6);
            paint.setARGB(255, 120, 70, 0);
            canvas.drawPath(path, paint);
            paint.reset();

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextSize(mRadius);
            paint.setARGB(255, 255, 128, 0);
            String idxStr = String.valueOf(mPrepareCountDown);
            Rect textBounds = new Rect();
            paint.getTextBounds(idxStr, 0, idxStr.length(), textBounds);
            canvas.drawText(idxStr, mCenter, mCenter - textBounds.centerY(), paint);
            paint.reset();

            mPrepareCountDown--;
            if (mPrepareCountDown <= 0) {
                setStep(PractiseView.STEP_SHOW);

                Message msg = mUIHandler.obtainMessage(MSG_STEP_CHANGE);
                msg.arg1 = PractiseView.STEP_SHOW;
                mUIHandler.sendMessage(msg);

                mPrepareCountDown = 3;
            }
        }

        private void drawShowStep(Canvas canvas, Paint paint) {
            Logger.i("drawShowStep");
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            canvas.drawColor(BG_COLOR);

            if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
                mGlyphPoints.clear();
            }
            drawHexagram(canvas, paint);
            GlyphInfo glyphInfo = mHackList.getSequences()[mShowHackIdx];
            drawPath(canvas, paint, glyphInfo, mGlyphPoints, mRadius);
            drawSequenceName(canvas, glyphInfo.getName(), paint, mCenter,
                    (mRadius + mPointRadius) * 2 + 24 * mDensity);

            mShowHackIdx++;
            if (mShowHackIdx >= mHackList.getLength()) {
                setStep(PractiseView.STEP_TRY);
                mUserStepCost = new long[mHackList.getLength()];
                mUserStepResults = new boolean[mHackList.getLength()];

                Message msg = mUIHandler.obtainMessage(MSG_STEP_CHANGE);
                msg.arg1 = PractiseView.STEP_TRY;
                mUIHandler.sendMessage(msg);

                TimeCounter.start();//try to draw the hack sequences, start time counter
                mShowHackIdx = 0;
            }
        }

        private void drawTryStep(Canvas canvas, Paint paint) {
            Logger.i("drawTryStep");
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            canvas.drawColor(BG_COLOR);
            if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
                mGlyphPoints.clear();
            }
            drawHexagram(canvas, paint);
            canvas.drawPath(mTouchPath, mTouchPaint);
            if (!mMovePath.isEmpty()) {
                findPossibleGlyphPath();
                paint.setStrokeWidth(mRadius / 20 > 15 ? 15 : mRadius / 20);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setColor(PATH_COLOR);
                canvas.drawPath(mPossibleGlyphPath, paint);
                paint.reset();
            }
            if (mTryHackIdx < mHackList.getSequences().length) {
                GlyphInfo glyphInfo = mHackList.getSequences()[mTryHackIdx];
                drawSequenceName(canvas, glyphInfo.getName(), paint, mCenter,
                        (mRadius + mPointRadius) * 2 + 24 * mDensity);
            }
        }

        private void drawStopStep(Canvas canvas, Paint paint) {
            Logger.i("drawStopStep");
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            canvas.drawColor(BG_COLOR);
            if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
                mGlyphPoints.clear();
            }
            drawHexagram(canvas, paint);
            if (mCurrentListCostTime > 0) {
                drawSequenceName(canvas, Utils.timeToSecondStr(mCurrentListCostTime), paint, mCenter,
                        (mRadius + mPointRadius) * 2 + 24 * mDensity);
            }
            drawArrowHint(canvas, paint, mCenter);
        }

        private void findPossibleGlyphPath() {
            if (mPossiblePoints == null) {
                mPossiblePoints = new ArrayList<>();
            } else {
                mPossiblePoints.clear();
            }
            float startx = 0, starty = 0;
            for (float[] floats : mMovePath) {
                for (PointF pointF : mGlyphPoints) {
                    double distance = Math.abs(Math.sqrt((Math.pow((floats[0] - pointF.x), 2)
                            + Math.pow((floats[1] - pointF.y), 2))));
                    if (distance < mPointRadius * 3) {
                        if (startx == 0 || starty == 0) {
                            startx = pointF.x;
                            starty = pointF.y;
                            mPossibleGlyphPath.moveTo(pointF.x, pointF.y);
                        } else {
                            mPossibleGlyphPath.lineTo(pointF.x, pointF.y);
                            startx = pointF.x;
                            starty = pointF.y;
                        }
                        if (mPossiblePoints.isEmpty() || !mPossiblePoints.get(mPossiblePoints.size() - 1).equals(pointF)) {
                            mPossiblePoints.add(pointF);
                            break;
                        }
                    }
                }
            }
        }

        private boolean compareResult(int index) {
            PointF[] pointG = mGlyphPath.get(index);
            PointF[] pointU = mUserGlyphPath.get(index);
            if (pointU == null || pointU.length < 1) {
                return false;
            }
            boolean result = false;
            List<PointF> pointFListG = new ArrayList<>(Arrays.asList(pointG));
            List<PointF> pointFListU = new ArrayList<>(Arrays.asList(pointU));
            if (pointFListU.size() >= pointFListG.size()) {
                if (pointFListU.removeAll(pointFListG)) {
                    result = pointFListU.size() == 0;
                } else {
                    result = false;
                }
            } else {
                if (pointFListG.removeAll(pointFListU)) {
                    result = pointFListG.size() == 0;
                } else {
                    result = false;
                }
            }
            return result;
        }

        private void drawHexagram(Canvas canvas, Paint paint) {
            drawEmptyCircle(mCenter - mPointRadius, mCenter - mPointRadius, paint, canvas, mPointRadius);
            float cx, cy;
            Path path = new Path();
            for (int degree = 30; degree < 360; degree += 60) {
                if (degree % 90 != 0) {
                    cx = (float) (Math.cos(Math.toRadians(degree)) * mRadius / 2);
                    cy = (float) (Math.sin(Math.toRadians(degree)) * mRadius / 2);
                    drawEmptyCircle(mCenter - mPointRadius + cx, mCenter - mPointRadius - cy, paint, canvas, mPointRadius);
                }
                cx = (float) (Math.cos(Math.toRadians(degree)) * mRadius);
                cy = (float) (Math.sin(Math.toRadians(degree)) * mRadius);
                drawEmptyCircle(cx + mCenter - mPointRadius, mCenter - mPointRadius - cy, paint, canvas, mPointRadius);
                if (degree == 30) {
                    path.moveTo((float) (cx + mCenter + Math.cos(Math.toRadians(degree)) * mPointRadius),
                            (float) (mCenter - cy - Math.sin(Math.toRadians(degree)) * mPointRadius));
                } else {
                    path.lineTo((float) (cx + mCenter + Math.cos(Math.toRadians(degree)) * mPointRadius),
                            (float) (mCenter - cy - Math.sin(Math.toRadians(degree)) * mPointRadius));
                }
            }

            path.close();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(6);
            paint.setARGB(255, 120, 70, 0);
            canvas.drawPath(path, paint);
        }

        private void drawEmptyCircle(float cx, float cy, Paint paint, Canvas canvas, float pointRadius) {
            paint.reset();
            cx += pointRadius;
            cy += pointRadius;
            paint.setColor(CIRCLE_COLOR);
            canvas.drawCircle(cx, cy, pointRadius, paint);
            paint.setColor(BG_COLOR);
            canvas.drawCircle(cx, cy, pointRadius * 0.85f, paint);
            mGlyphPoints.add(new PointF(cx, cy));

            paint.reset();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextSize(pointRadius);
            paint.setARGB(255, 255, 128, 0);
            String idxStr = String.valueOf(mGlyphPoints.size());
            Rect textBounds = new Rect();
            paint.getTextBounds(idxStr, 0, idxStr.length(), textBounds);
            canvas.drawText(idxStr, cx, cy - textBounds.centerY(), paint);
        }

        private void drawPath(Canvas canvas, Paint paint, GlyphInfo glyphInfo, List<PointF> glyphPoints, float glyRadius) {
            paint.setStrokeWidth(glyRadius / 20 > 15 ? 15 : glyRadius / 20);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(PATH_COLOR);
            int[] path = glyphInfo.getPath();

            if (path == null || path.length < 1) {
                return;
            }
            PointF[] pointFS = new PointF[path.length];
            int position = path[0] - 1;
            PointF pointF = glyphPoints.get(position);
            pointFS[0] = pointF;
            float startX = pointF.x;
            float startY = pointF.y;
            for (int i = 1; i < path.length; i++) {
                position = path[i] - 1;
                PointF point = glyphPoints.get(position);
                pointFS[i] = point;
                canvas.drawLine(startX, startY, point.x, point.y, paint);
                startX = point.x;
                startY = point.y;
            }
            mGlyphPath.put(mShowHackIdx, pointFS);
        }

        private void drawSequenceName(Canvas canvas, String name, Paint paint, float cx, float cy) {
            paint.reset();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(24 * mDensity);
            paint.setARGB(255, 255, 128, 0);
            Rect textBounds = new Rect();
            paint.getTextBounds(name, 0, name.length(), textBounds);
            canvas.drawText(name, cx, cy - textBounds.centerY(), paint);
        }

        private void drawArrowHint(Canvas canvas, Paint paint, float cy) {
            paint.reset();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextSize(18 * mDensity);
            paint.setARGB(255, 128, 128, 128);
            Rect textBound = new Rect();
            paint.getTextBounds("<<", 0, 2, textBound);
            canvas.drawText("<<", 0, cy - textBound.centerY(), paint);

            paint.setTextAlign(Paint.Align.RIGHT);
            paint.getTextBounds(">>", 0, 2, textBound);
            canvas.drawText(">>", getWidth(), cy - textBound.centerY(), paint);
        }

    }

    class TryEndResult {
        long totalTime;
        boolean[] stepResults;
        long[] stepCosts;
    }
}
