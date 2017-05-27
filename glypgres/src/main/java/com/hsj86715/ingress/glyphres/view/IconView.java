package com.hsj86715.ingress.glyphres.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.hsj86715.ingress.glyphres.data.BaseGlyphData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 2017/5/26.
 * 此类用来生成app的icon图标
 */

public class IconView extends View {
    private static final float sValidRegion = 0.75f;
    private static final String sIconPath = BaseGlyphData.H_ENLIGHTENMENT;
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
        mBorderWidth = diameter * 0.1f;
        final float borderRadius = (diameter - mBorderWidth) / 2;
        final float glyphRadius = (diameter - mBorderWidth) * sValidRegion / 2;
        final float pointRadius = glyphRadius * 0.05f;
        float cx, cy;
        Paint paint = new Paint();
        cx = (width - mBorderWidth) / 2;
        cy = (height - mBorderWidth) / 2;
        canvas.save();
        drawIconBg(canvas, cx, cy, paint, borderRadius);
        drawSequence(canvas, cx, cy, sIconPath, paint, glyphRadius, pointRadius);
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

    protected void drawSequence(Canvas canvas, float cx, float cy, String name, Paint paint,
                                float glyRadius, float pointRadius) {
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
        paint.setStrokeWidth(4);
        paint.setARGB(128, 128, 128, 128);
        canvas.drawPath(path, paint);
        paint.reset();

        drawPath(canvas, paint, name, glyphPoints, glyRadius);
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

    private void drawPath(Canvas canvas, Paint paint, String name, List<PointF> glyphPoints, float glyRadius) {
        paint.setStrokeWidth(glyRadius / 20 > 10 ? 10 : glyRadius / 20);
        paint.setARGB(255, 0, 0, 0);
        drawLines(canvas, BaseGlyphData.getInstance().getGlyphPath(name), glyphPoints, paint);
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
