package com.hsj86715.ingress.glyphres.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.hsj86715.ingress.glyphres.data.GlyphInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 2017/5/26.
 * 此类用来生成app的icon图标
 */

public class IconView extends View {
    private static final float sValidRegion = 0.8f;
    private float mBorderWidth = 20;

    public IconView(Context context) {
        this(context, null);
    }

    public IconView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float height = getMeasuredHeight();
        final float width = getMeasuredWidth();
        float diameter = Math.min(height, width);
        mBorderWidth = diameter * 0.05f;
        final float borderRadius = (diameter - mBorderWidth) / 2;
        final float glyphRadius = (diameter - mBorderWidth) * sValidRegion / 2;
        final float pointRadius = glyphRadius * 0.05f;
        float cx, cy;
        Paint paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        cx = width / 2;
        cy = height / 2;
        canvas.save();
        drawIconBg(canvas, cx - mBorderWidth / 2, cy - mBorderWidth / 2, paint, borderRadius);
//        drawSequence(canvas, cx - pointRadius, cy - pointRadius, sIconPath, paint, glyphRadius, pointRadius);
        canvas.restore();
    }

    private void drawIconBg(Canvas canvas, float cx, float cy, Paint paint, float borderRadius) {
        Path path = new Path();
        float px, py;
        for (int degree = 30; degree < 360; degree += 60) {
            px = (float) (Math.cos(Math.toRadians(degree)) * borderRadius);
            py = (float) (Math.sin(Math.toRadians(degree)) * borderRadius);
            if (degree == 30) {
                path.moveTo(cx + px + mBorderWidth / 2, cy - py + mBorderWidth / 2);
            } else {
                path.lineTo(cx + px + mBorderWidth / 2, cy - py + mBorderWidth / 2);
            }
        }
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(255, 155, 200, 227);
        canvas.drawPath(path, paint);
        paint.reset();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mBorderWidth);
        paint.setARGB(255, 33, 168, 227);
        canvas.drawPath(path, paint);
        paint.reset();
    }

    protected void drawSequence(Canvas canvas, float cx, float cy, GlyphInfo glyphInfo, Paint paint,
                                float glyRadius, float pointRadius, int index) {
        List<PointF> glyphPoints = new ArrayList<>();
        drawEmptyCircle(cx, cy, paint, canvas, pointRadius, glyphPoints);
        Path path = new Path();
        float px, py;
        for (int degree = 30; degree < 360; degree += 60) {
            if (degree % 90 != 0) {
                px = (float) (Math.cos(Math.toRadians(degree)) * glyRadius / 2);
                py = (float) (Math.sin(Math.toRadians(degree)) * glyRadius / 2);
                drawEmptyCircle(px + cx, cy - py, paint, canvas, pointRadius, glyphPoints);
            }
            px = (float) (Math.cos(Math.toRadians(degree)) * glyRadius);
            py = (float) (Math.sin(Math.toRadians(degree)) * glyRadius);
            drawEmptyCircle(px + cx, cy - py, paint, canvas, pointRadius, glyphPoints);
            if (degree == 30) {
                path.moveTo(px + cx + pointRadius, cy - py + pointRadius);
            } else {
                path.lineTo(px + cx + pointRadius, cy - py + pointRadius);
            }
        }

        path.close();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setARGB(128,120,70,0);
        canvas.drawPath(path, paint);
        paint.reset();

        drawPath(canvas, paint, glyphInfo, glyphPoints, glyRadius,index);
    }

    protected int getPathColor(int index){
        return Color.argb(255,0,0,0);
    }

    private void drawEmptyCircle(float cx, float cy, Paint paint, Canvas canvas, float pointRadius,
                                 List<PointF> glyphPoints) {
        cx += pointRadius;
        cy += pointRadius;
        paint.setARGB(255, 240, 173, 50);
        canvas.drawCircle(cx, cy, pointRadius, paint);
        paint.setARGB(255, 33, 168, 227);
        canvas.drawCircle(cx, cy, pointRadius * 0.75f, paint);
        glyphPoints.add(new PointF(cx, cy));
    }

    private void drawPath(Canvas canvas, Paint paint, GlyphInfo glyphInfo, List<PointF> glyphPoints,
                          float glyRadius,int index) {
        paint.setStrokeWidth(glyRadius / 20 > 15 ? 15 : glyRadius / 20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(getPathColor(index));
        drawLines(canvas, glyphInfo.getPath(), glyphPoints, paint);
    }

    private void drawLines(Canvas canvas, int[] path, List<PointF> glyphPoints, Paint paint) {
        if (path == null || path.length < 1) {
            return;
        }
        int position = path[0] - 1;
        float startX = glyphPoints.get(position).x;
        float startY = glyphPoints.get(position).y;
        for (int i = 1; i < path.length; i++) {
            position = path[i] - 1;
            PointF point = glyphPoints.get(position);
            canvas.drawLine(startX, startY, point.x, point.y, paint);
            startX = point.x;
            startY = point.y;
        }
    }
}
