package com.hsj86715.ingress.glyph.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 16/5/16.
 */
public class IngressGlyphView extends FrameLayout {
    private static final String TAG = "IngressGlyphView";
    private float mDensity;
    private boolean isDrawing = false;

    private List<Point> mGlyphPoints = new ArrayList<>();
    private int[][] mHackSequences;
    private int mSequencesIdx = 0;
    private GlyphPathView mPathView = null;

    public IngressGlyphView(Context context) {
        this(context, null);
    }

    public IngressGlyphView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IngressGlyphView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IngressGlyphView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mDensity = getResources().getDisplayMetrics().density;
        addView(new HexagramView(context));
    }

    /**
     * Show hack sequences one by one
     *
     * @param pointsPositions
     */
    public void drawPath(int[]... pointsPositions) {
        if (pointsPositions == null) {
            return;
        }
        mHackSequences = pointsPositions;
        if (mPathView == null) {
            mPathView = new GlyphPathView(getContext());
            addView(mPathView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            mPathView.stepIdx = 1;
            mPathView.invalidate();
        }
    }

    public void clear() {
        if (mPathView != null) {
            removeView(mPathView);
            mPathView = null;
        }
        invalidate();
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    private class HexagramView extends View {

        public HexagramView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mGlyphPoints != null && mGlyphPoints.size() > 0) {
                mGlyphPoints.clear();
            }
            drawHexagram(canvas, new Paint());
        }

        private void drawHexagram(Canvas canvas, Paint paint) {
            float pointRadius = getMeasuredWidth() / 15 > 10 * mDensity ? 10 * mDensity : getMeasuredWidth() / 15;
            int center = getMeasuredWidth() / 2;
            float radius = center - pointRadius;
            drawEmptyCircle(radius, radius, paint, canvas, pointRadius);

            float cx, cy;
            Path path = new Path();
            for (int degree = 30; degree < 360; degree += 60) {
                if (degree % 90 != 0) {
                    cx = (float) (Math.cos(Math.toRadians(degree)) * radius / 2);
                    cy = (float) (Math.sin(Math.toRadians(degree)) * radius / 2);
                    drawEmptyCircle(radius + cx, radius - cy, paint, canvas, pointRadius);
                }
                cx = (float) (Math.cos(Math.toRadians(degree)) * radius);
                cy = (float) (Math.sin(Math.toRadians(degree)) * radius);
                drawEmptyCircle(cx + radius, radius - cy, paint, canvas, pointRadius);
                if (degree == 30) {
                    path.moveTo(cx + center, center - cy);
                } else {
                    path.lineTo(cx + center, center - cy);
                }
            }

            path.close();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setARGB(128, 0, 0, 0);
            canvas.drawPath(path, paint);
        }

        private void drawEmptyCircle(float cx, float cy, Paint paint, Canvas canvas, float pointRadius) {
            cx += pointRadius;
            cy += pointRadius;
            paint.setARGB(255, 128, 128, 128);
            canvas.drawCircle(cx, cy, pointRadius, paint);
            paint.setARGB(255, 255, 255, 255);
            canvas.drawCircle(cx, cy, pointRadius * 0.75f, paint);
            mGlyphPoints.add(new Point((int) cx, (int) cy));

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(pointRadius);
            paint.setARGB(255, 255, 128, 0);
            String idxStr = String.valueOf(mGlyphPoints.size());
            Rect textBounds = new Rect();
            paint.getTextBounds(idxStr, 0, idxStr.length(), textBounds);
            canvas.drawText(idxStr, cx, cy - textBounds.centerY(), paint);
        }
    }

    private class GlyphPathView extends View {

        private int stepIdx = 1;
        private Paint paint;

        public GlyphPathView(Context context) {
            super(context);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mHackSequences != null && mHackSequences.length > 0) {
                paint.setStrokeWidth(getMeasuredWidth() / 40 > 10 ? 10 : getMeasuredWidth() / 40);
                paint.setARGB(255, 0, 0, 0);
                drawByStep(canvas);
            }
        }

        private void drawFullPath(Canvas canvas) {
            drawLines(canvas, mHackSequences[mSequencesIdx].length);
        }

        private void drawByStep(Canvas canvas) {
            if (stepIdx < mHackSequences[mSequencesIdx].length) {
                isDrawing = true;
                drawLines(canvas, stepIdx);
                postInvalidateDelayed(150);
                stepIdx++;
            } else {
                drawFullPath(canvas);
                if (mSequencesIdx < mHackSequences.length - 1) {
                    mSequencesIdx++;
                    stepIdx = 1;
                    drawByStep(canvas);
                } else {
                    isDrawing = false;
                }
            }
        }

        private void drawLines(Canvas canvas, int lineCount) {
            int position = mHackSequences[mSequencesIdx][0] - 1;
            float startX = mGlyphPoints.get(position).x;
            float startY = mGlyphPoints.get(position).y;
            for (int i = 1; i < lineCount; i++) {
                position = mHackSequences[mSequencesIdx][i] - 1;
                Point point = mGlyphPoints.get(position);
                canvas.drawLine(startX, startY, point.x, point.y, paint);
                startX = point.x;
                startY = point.y;
            }
        }
    }
}
