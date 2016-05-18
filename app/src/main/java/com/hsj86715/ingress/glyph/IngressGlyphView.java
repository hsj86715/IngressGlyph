package com.hsj86715.ingress.glyph;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    private List<Point> mGlyphPoints = new ArrayList<>();
    private int[] mPointsPositions;
    private GlyphPathView mPathView = null;

    public IngressGlyphView(Context context) {
        this(context, null);
    }

    public IngressGlyphView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IngressGlyphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IngressGlyphView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mDensity = getResources().getDisplayMetrics().density;
        addView(new HexagramView(context));
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mGlyphPoints.clear();
    }

    public void drawPath(int[] pointsPositions) {
        if (pointsPositions == null || pointsPositions.length < 2) {
            return;
        }
        mPointsPositions = pointsPositions;
        if (mPathView == null) {
            mPathView = new GlyphPathView(getContext());
            addView(mPathView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            mPathView.invalidate();
        }
    }

    private class HexagramView extends View {

        public HexagramView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawHexagram(canvas, new Paint());
        }

        private void drawHexagram(Canvas canvas, Paint paint) {
            float pointRadius = getMeasuredWidth() / 15 > 10 * mDensity ? 10 * mDensity : getMeasuredWidth() / 15;
            int center = getMeasuredWidth() / 2;
            float radius = center - pointRadius;
            drawEmptyCircle(radius, radius, paint, canvas, pointRadius);

            float cx, cy;
            for (int degree = 30; degree < 360; degree += 60) {
                if (degree % 90 != 0) {
                    cx = (float) (Math.cos(Math.toRadians(degree)) * radius / 2);
                    cy = (float) (Math.sin(Math.toRadians(degree)) * radius / 2);
                    drawEmptyCircle(radius + cx, radius - cy, paint, canvas, pointRadius);
                }
                cx = (float) (Math.cos(Math.toRadians(degree)) * radius);
                cy = (float) (Math.sin(Math.toRadians(degree)) * radius);
                drawEmptyCircle(cx + radius, radius - cy, paint, canvas, pointRadius);
            }
        }

        private void drawEmptyCircle(float cx, float cy, Paint paint, Canvas canvas, float pointRadius) {
            cx += pointRadius;
            cy += pointRadius;
            paint.setARGB(255, 128, 128, 128);
            canvas.drawCircle(cx, cy, pointRadius, paint);
            paint.setARGB(255, 255, 255, 255);
            canvas.drawCircle(cx, cy, pointRadius * 0.75f, paint);
            mGlyphPoints.add(new Point(cx, cy));

//            paint.setTextAlign(Paint.Align.CENTER);
//            paint.setTextSize(pointRadius * 0.7f);
//            paint.setARGB(255, 0, 0, 255);
//            canvas.drawText(String.valueOf(mGlyphPoints.size()), cx, cy, paint);
        }
    }

    private class GlyphPathView extends View {

        public GlyphPathView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStrokeWidth(getMeasuredWidth() / 40 > 10 ? 10 : getMeasuredWidth() / 40);
            paint.setARGB(255, 0, 0, 0);
            drawPath(canvas, paint);
//            drawByStep(canvas, paint);
        }

        private void drawPath(Canvas canvas, Paint paint) {
            drawWholeLine(canvas, paint, mPointsPositions.length);
//            int position = mPointsPositions[0] - 1;
//            float startX = mGlyphPoints.get(position).x;
//            float startY = mGlyphPoints.get(position).y;
//            for (int i = 1; i < mPointsPositions.length; i++) {
//                position = mPointsPositions[i] - 1;
//                Log.i(TAG, "drawPath: position=" + position);
//                Point point = mGlyphPoints.get(position);
//                canvas.drawLine(startX, startY, point.x, point.y, paint);
//                startX = point.x;
//                startY = point.y;
//            }
        }

        private int positionIdx = 0;
        private int stepIdx = 1;

        private void drawByStep(Canvas canvas, Paint paint) {
            int position = mPointsPositions[positionIdx] - 1;
            Point startP = mGlyphPoints.get(position);

            if (positionIdx < mPointsPositions.length - 1) {
                Point nextP = mGlyphPoints.get(positionIdx + 1);
                if (positionIdx > 0) {//whole line
                    drawWholeLine(canvas, paint, positionIdx);
                }
                if (stepIdx < 5) {
                    drawPartLine(canvas, paint, startP, nextP);
                } else {
                    positionIdx++;
                    stepIdx = 1;
                }
            } else if (positionIdx == mPointsPositions.length) {
                drawPath(canvas, paint);
                positionIdx++;
            } else {
                positionIdx = 0;
            }
            postInvalidateDelayed(500);
        }

        private void drawWholeLine(Canvas canvas, Paint paint, int lineCount) {
            int position = mPointsPositions[0] - 1;
            float startX = mGlyphPoints.get(position).x;
            float startY = mGlyphPoints.get(position).y;
            for (int i = 1; i < lineCount; i++) {
                position = mPointsPositions[i] - 1;
                Point point = mGlyphPoints.get(position);
                canvas.drawLine(startX, startY, point.x, point.y, paint);
                startX = point.x;
                startY = point.y;
            }
        }

        private void drawPartLine(Canvas canvas, Paint paint, Point startP, Point endP) {
            canvas.drawLine(startP.x, startP.y, startP.x + (endP.x - startP.x) * stepIdx / 5,
                    startP.y + (endP.y - startP.y) * stepIdx / 5, paint);
            stepIdx++;
        }
    }

    private class Point {
        float x;
        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
