package com.hsj86715.ingress.glyphres.view.practise;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.SurfaceHolder;

import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.HackList;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.com.farmcode.utility.tools.Logger;
import cn.com.farmcode.utility.tools.TimeCounter;

/**
 * @author hushujun
 */
class DrawThread extends Thread {

    static final int MSG_STEP_CHANGE = 0;
    static final int MSG_TRY_STEP_TIME = 1;

    protected boolean isDrawing = true;
    private static final int PREPARE_INTERVAL = 1000;
    private static final int SHOW_INTERVAL = 2000;
    private static final int TRY_INTERVAL = 30;
    private static final int STOP_INTERVAL = 150;

    private Handler mUIHandler;

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mTouchPaint, mPaint;
    protected Path mTouchPath, mPossibleGlyphPath;

    private float mDensity;
    private List<PointF> mGlyphPoints = new ArrayList<>();
    protected List<float[]> mMovePath = new CopyOnWriteArrayList<>();
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

    DrawThread(float density, int measuredWidth, SurfaceHolder holder, @NonNull Handler uiHandler) {
        super("Practise-Draw-Thread");
        mUIHandler = uiHandler;
        mDensity = density;
        mHolder = holder;

        mTouchPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTouchPaint.setStrokeWidth(20f);
        mTouchPaint.setColor(Color.parseColor("#FF4081"));
        mTouchPaint.setStyle(Paint.Style.STROKE);
        mTouchPaint.setStrokeJoin(Paint.Join.ROUND);
        mTouchPaint.setStrokeCap(Paint.Cap.ROUND);
        mTouchPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID));

        mPaint = new Paint();

        mPointRadius = measuredWidth / 15 > 10 * mDensity ? 10 * mDensity : measuredWidth / 15;
        mCenter = measuredWidth / 2;
        mRadius = mCenter - mPointRadius;

        mTouchPath = new Path();
        mPossibleGlyphPath = new Path();
    }

    void setHackList(HackList hackList) {
        mHackList = hackList;
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
        super.run();
        while (isDrawing) {
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
            draw();
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

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            switch (mStep) {
                case PractiseView.STEP_PREPARE:
                    drawPrepareStep(mCanvas, mPaint);
                    break;
                case PractiseView.STEP_SHOW:
                    drawShowStep(mCanvas, mPaint);
                    break;
                case PractiseView.STEP_TRY:
                    drawTryStep(mCanvas, mPaint);
                    break;
                case PractiseView.STEP_STOP:
                default:
                    drawStopStep(mCanvas, mPaint);
                    break;
            }
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    private void drawPrepareStep(Canvas canvas, Paint paint) {
        Logger.i("drawPrepareStep, " + mPrepareCountDown);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
        canvas.drawColor(Color.WHITE);

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
        canvas.drawColor(Color.WHITE);

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
        canvas.drawColor(Color.WHITE);
        if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
            mGlyphPoints.clear();
        }
        drawHexagram(canvas, paint);
        canvas.drawPath(mTouchPath, mTouchPaint);
        if (!mMovePath.isEmpty()) {
            findPossibleGlyphPath();
            paint.setStrokeWidth(mRadius / 20 > 15 ? 15 : mRadius / 20);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setARGB(255, 0, 0, 0);
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
        canvas.drawColor(Color.WHITE);
        if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
            mGlyphPoints.clear();
        }
        drawHexagram(canvas, paint);
        if (mCurrentListCostTime > 0) {
            drawSequenceName(canvas, Utils.timeToSecondStr(mCurrentListCostTime), paint, mCenter,
                    (mRadius + mPointRadius) * 2 + 24 * mDensity);
        }
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
                    if (mPossiblePoints.contains(pointF)) {
                        break;
                    } else {
                        mPossiblePoints.add(pointF);
                        break;
                    }
                }
            }
        }
    }

    private boolean compareResult(int index) {
        boolean result = false;
//        List<Line> glyphLines;
//        List<Line> userLines;
        PointF[] pointG = mGlyphPath.get(index);
//            glyphLines = new ArrayList<>();
//            for (int j = 0; j < pointFS.length - 1; j++) {
//                Line line = new Line(pointFS[j], pointFS[j + 1]);
//                glyphLines.add(line);
//            }
//
        PointF[] pointU = mUserGlyphPath.get(index);
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
//            userLines = new ArrayList<>();
//            for (int j = 0; j < pointFS.length - 1; j++) {
//                Line line = new Line(pointFS[j], pointFS[j + 1]);
//                userLines.add(line);
//            }
//
//            if (userLines.removeAll(glyphLines)) {
//                results[i] = userLines.size() == 0;
//            } else {
//                results[i] = false;
//            }
        return result;
    }

    private void drawHexagram(Canvas canvas, Paint paint) {
        drawEmptyCircle(mRadius, mRadius, paint, canvas, mPointRadius);
        float cx, cy;
        Path path = new Path();
        for (int degree = 30; degree < 360; degree += 60) {
            if (degree % 90 != 0) {
                cx = (float) (Math.cos(Math.toRadians(degree)) * mRadius / 2);
                cy = (float) (Math.sin(Math.toRadians(degree)) * mRadius / 2);
                drawEmptyCircle(mRadius + cx, mRadius - cy, paint, canvas, mPointRadius);
            }
            cx = (float) (Math.cos(Math.toRadians(degree)) * mRadius);
            cy = (float) (Math.sin(Math.toRadians(degree)) * mRadius);
            drawEmptyCircle(cx + mRadius, mRadius - cy, paint, canvas, mPointRadius);
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
        paint.setARGB(255, 128, 128, 128);
        canvas.drawCircle(cx, cy, pointRadius, paint);
        paint.setARGB(255, 255, 255, 255);
        canvas.drawCircle(cx, cy, pointRadius * 0.75f, paint);
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
        paint.setARGB(255, 0, 0, 0);
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

//    private class Line {
//        private PointF start;
//        private PointF end;
//
//        Line(PointF start, PointF end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            Line line = (Line) o;
//            return (start.equals(line.start) && end.equals(line.end))
//                    || (start.equals(line.end) && end.equals(line.start));
//        }
//    }

    protected class TryEndResult {
        long totalTime;
        boolean[] stepResults;
        long[] stepCosts;
    }
}
