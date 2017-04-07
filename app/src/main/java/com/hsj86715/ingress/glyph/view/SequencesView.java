//package com.hsj86715.ingress.glyph.view;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.graphics.Typeface;
//import android.os.Build;
//import android.os.Parcelable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//import com.hsj86715.ingress.glyph.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by hushujun on 2017/4/5.
// */
//
//public class SequencesView extends SurfaceView implements SurfaceHolder.Callback2 {
//    private static final String TAG = "SequencesView";
//    private float mDensity;
//    private boolean mDrawPointIdx = true;
//    private boolean mDrawGlyphByStep = true;
//    private Paint mPaint;
//
//    private List<Point> mGlyphPoints = new ArrayList<>();
//    private int[][] mHackSequences;
//    private int mGlyphStepIdx = 1;
//    private int mSequencesIdx = 0;
//    private Thread mDrawThread;
//
//    public SequencesView(Context context) {
//        this(context, null);
//    }
//
//    public SequencesView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SequencesView(Context context, AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public SequencesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SequencesView, defStyleAttr, defStyleRes);
//        mDrawPointIdx = ta.getBoolean(R.styleable.SequencesView_drawPointIndex, true);
//        mDrawGlyphByStep = ta.getBoolean(R.styleable.SequencesView_drawGlyphBySteps, true);
//        ta.recycle();
//        setZOrderOnTop(true);
//        getHolder().setFormat(PixelFormat.TRANSLUCENT);
//        getHolder().addCallback(this);
//    }
//
//    @Override
//    public void onScreenStateChanged(int screenState) {
//        super.onScreenStateChanged(screenState);
//        if (screenState == SCREEN_STATE_OFF) {
//            mGlyphPoints.clear();
//        }
//    }
//
//    @Override
//    protected void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mGlyphPoints.clear();
//    }
//
//    @Override
//    protected Parcelable onSaveInstanceState() {
//        return super.onSaveInstanceState();
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(state);
//    }
//
//    @Override
//    public void surfaceRedrawNeeded(SurfaceHolder holder) {
//        Log.i(TAG, "surfaceRedrawNeeded");
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        mDensity = getResources().getDisplayMetrics().density;
//        mPaint = new Paint();
////        drawHexagram();
//        mDrawThread = new Thread(new DrawRunnable());
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
////        mDrawThread.stop();
//        mGlyphPoints.clear();
//    }
//
//    public void drawPath(int[]... pointsPositions) {
//        if (pointsPositions == null) {
//            return;
//        }
//        mHackSequences = pointsPositions;
//        mGlyphStepIdx = 1;
//
//        mDrawThread.start();
////        drawHexagram();
////        drawSequence(mHackSequences[mSequencesIdx]);
//    }
//
//    public boolean isDrawing() {
////        if (mDrawRunnable != null) {
////            return mDrawRunnable.isDrawing;
////        } else {
//            return false;
////        }
//    }
//
//    private void drawSequence(int[] glyphPath) {
//        Canvas canvas = getHolder().lockCanvas();
//        mPaint.setStrokeWidth(getMeasuredWidth() / 40 > 10 ? 10 : getMeasuredWidth() / 40);
//        mPaint.setARGB(255, 0, 0, 0);
////        if (mDrawGlyphByStep) {
////            drawByStep(canvas, mPaint, glyphPath);
////        } else {
//        drawFullPath(canvas, mPaint, glyphPath);
////        }
//        getHolder().unlockCanvasAndPost(canvas);
//        mPaint.reset();
//    }
//
//    private void drawFullPath(Canvas canvas, Paint paint, int[] glyphPath) {
//        drawLines(canvas, paint, glyphPath.length, glyphPath);
//    }
//
//    private void drawByStep(Canvas canvas, Paint paint, int[] glyphPath) {
//        if (mGlyphStepIdx < glyphPath.length) {
//            drawLines(canvas, paint, mGlyphStepIdx, glyphPath);
//            mGlyphStepIdx++;
//        } else {
//            drawFullPath(canvas, paint, glyphPath);
//        }
//    }
//
//    private void drawLines(Canvas canvas, Paint paint, int lineCount, int[] glyphPath) {
//        int position = glyphPath[0] - 1;
//        float startX = mGlyphPoints.get(position).x;
//        float startY = mGlyphPoints.get(position).y;
//        for (int i = 1; i < lineCount; i++) {
//            position = glyphPath[i] - 1;
//            Point point = mGlyphPoints.get(position);
//            canvas.drawLine(startX, startY, point.x, point.y, paint);
//            startX = point.x;
//            startY = point.y;
//        }
//    }
//
//    private void drawHexagram() {
//        mGlyphPoints.clear();
//        Canvas canvas = getHolder().lockCanvas();
//        canvas.drawColor(Color.WHITE);
//        float pointRadius = getMeasuredWidth() / 15 > 10 * mDensity ? 10 * mDensity : getMeasuredWidth() / 15;
//        int center = getMeasuredWidth() / 2;
//        float radius = center - pointRadius;
//        drawGlyphPoints(radius, radius, canvas, pointRadius);
//
//        float cx, cy;
//        Path path = new Path();
//        for (int degree = 30; degree < 360; degree += 60) {
//            if (degree % 90 != 0) {
//                cx = (float) (Math.cos(Math.toRadians(degree)) * radius / 2);
//                cy = (float) (Math.sin(Math.toRadians(degree)) * radius / 2);
//                drawGlyphPoints(radius + cx, radius - cy, canvas, pointRadius);
//            }
//            cx = (float) (Math.cos(Math.toRadians(degree)) * radius);
//            cy = (float) (Math.sin(Math.toRadians(degree)) * radius);
//            drawGlyphPoints(cx + radius, radius - cy, canvas, pointRadius);
//            if (degree == 30) {
//                path.moveTo(cx + center, center - cy);
//            } else {
//                path.lineTo(cx + center, center - cy);
//            }
//        }
//
//        path.close();
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(2);
//        mPaint.setARGB(128, 128, 128, 128);
//        canvas.drawPath(path, mPaint);
//        getHolder().unlockCanvasAndPost(canvas);
//        mPaint.reset();
//    }
//
//    private void drawGlyphPoints(float cx, float cy, Canvas canvas, float pointRadius) {
//        cx += pointRadius;
//        cy += pointRadius;
//        mPaint.setARGB(255, 128, 128, 128);
//        canvas.drawCircle(cx, cy, pointRadius, mPaint);
//        mPaint.setARGB(255, 255, 255, 255);
//        canvas.drawCircle(cx, cy, pointRadius * 0.75f, mPaint);
//        mGlyphPoints.add(new Point(cx, cy));
//
//        if (mDrawPointIdx) {
//            mPaint.setTextAlign(Paint.Align.CENTER);
//            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
//            mPaint.setTextSize(pointRadius);
//            mPaint.setARGB(255, 255, 128, 0);
//            String idxStr = String.valueOf(mGlyphPoints.size());
//            Rect textBounds = new Rect();
//            mPaint.getTextBounds(idxStr, 0, idxStr.length(), textBounds);
//            canvas.drawText(idxStr, cx, cy - textBounds.centerY(), mPaint);
//        }
//        mPaint.reset();
//    }
//
//    private class DrawRunnable implements Runnable {
//        private static final int DELAY = 50;//ms
//        private boolean isDrawing = false;
//
//        @Override
//        public void run() {
//            if (isDrawing) {
//                drawHexagram();
//                if (mHackSequences != null && mHackSequences.length > 0) {
//                    Canvas canvas = getHolder().lockCanvas();
//                    mPaint.setStrokeWidth(getMeasuredWidth() / 40 > 10 ? 10 : getMeasuredWidth() / 40);
//                    mPaint.setARGB(255, 0, 0, 0);
//                    if (mDrawGlyphByStep) {
//                        if (mGlyphStepIdx < mHackSequences[mSequencesIdx].length) {
//                            drawLines(canvas, mPaint, mGlyphStepIdx, mHackSequences[mSequencesIdx]);
//                            mGlyphStepIdx++;
//                            try {
//                                Thread.sleep(DELAY);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                                stop();
//                            }
//                        } else {
//                            drawFullPath(canvas, mPaint, mHackSequences[mSequencesIdx]);
//                            if (mSequencesIdx < mHackSequences.length) {
//                                mSequencesIdx++;
//                                mGlyphStepIdx = 1;
//                                try {
//                                    Thread.sleep(DELAY);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                    stop();
//                                }
//                            } else {
//                                stop();
//                            }
//                        }
//                    } else {
//                        drawFullPath(canvas, mPaint, mHackSequences[mSequencesIdx]);
//                        stop();
//                    }
//                    getHolder().unlockCanvasAndPost(canvas);
//                    mPaint.reset();
//                }
//            }
//        }
//
//        private void stop() {
//            isDrawing = false;
//        }
//    }
//
//    private interface SequenceDrawComplete {
//        void onDrawSequenceComplete();
//    }
//
//    private class Point {
//        float x;
//        float y;
//
//        public Point(float x, float y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//}
