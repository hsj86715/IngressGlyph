package com.hsj86715.ingress.glyphres.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hsj86715.ingress.glyphres.R;
import com.hsj86715.ingress.glyphres.data.BaseGlyphData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 2017/5/17.
 */

public class MultiGlyphView extends View {
    private static final String TAG = "MultiGlyphView";

    private float mTextSize;
    private int mTextColor;
    private float mGlyphRadius;
    private float mSequencesDivider;
    private float mTextGlyphDivider;

    private SequenceClickListener mHackListener;
    private String[] mSequenceNames;
    private RectF[] mSequenceBounds;

    public MultiGlyphView(Context context) {
        this(context, null);
    }

    public MultiGlyphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiGlyphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiGlyphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MultiGlyphView, defStyleAttr, defStyleRes);
        mTextSize = ta.getDimensionPixelSize(R.styleable.MultiGlyphView_textSize, 14);
        mTextColor = ta.getColor(R.styleable.MultiGlyphView_textColor, Color.BLACK);
        mGlyphRadius = ta.getDimensionPixelSize(R.styleable.MultiGlyphView_glyphRadius, 20);
        mSequencesDivider = ta.getDimensionPixelSize(R.styleable.MultiGlyphView_sequencesDivider, 5);
        mTextGlyphDivider = ta.getDimensionPixelSize(R.styleable.MultiGlyphView_textGlyphDivider, 2);
        ta.recycle();
    }

    public void setHackSequenceListener(SequenceClickListener listener) {
        this.mHackListener = listener;
    }

    public void setSequences(String names) {
        if (!TextUtils.isEmpty(names)) {
            setSequences(new String[]{names});
        }
    }

    public void setSequences(String[] names) {
        this.mSequenceNames = names;
        if (mSequenceNames != null && mSequenceNames.length > 0) {
            mSequenceBounds = new RectF[mSequenceNames.length];
            invalidate();
        }
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        int miniHeight = (int) (getPaddingTop() + getPaddingBottom() + mGlyphRadius * 2 + mTextGlyphDivider + mTextSize * 2);
        return getBackground() == null ? miniHeight : Math.max(miniHeight, getBackground().getMinimumHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSequenceNames == null || mSequenceNames.length < 1) {
            return;
        }
        int count = mSequenceNames.length;
        final float height = getMeasuredHeight() - getPaddingBottom() - getPaddingTop() - mTextGlyphDivider - mTextSize * 2;
        final float width = (getMeasuredWidth() - mSequencesDivider * (count - 1)) / count;
        float diameter = Math.min(height, width);
        final float glyphRadius = Math.min(diameter * 0.45f, mGlyphRadius);
        final float pointRadius = glyphRadius * 0.05f;
        float cx, cy;
        Paint paint = new Paint();
        for (int i = 0; i < count; i++) {
            cx = getPaddingLeft() + mSequencesDivider * i + width * i + width / 2;
            cy = getPaddingTop() + height / 2;
            canvas.save();
            drawSequence(canvas, cx, cy, mSequenceNames[i], paint, glyphRadius, pointRadius);
            canvas.restore();
            if (mHackListener != null) {
                mSequenceBounds[i] = new RectF(cx - glyphRadius, cy - glyphRadius, cx + glyphRadius,
                        cy + glyphRadius + mTextGlyphDivider + mTextSize * 2);
            }
        }
    }

    private void drawSequence(Canvas canvas, float cx, float cy, String name, Paint paint,
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
        paint.setStrokeWidth(2);
        paint.setARGB(128, 0, 0, 0);
        canvas.drawPath(path, paint);
        paint.reset();

        drawPath(canvas, paint, name, glyphPoints, glyRadius);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);
        StaticLayout layout = new StaticLayout(name, new TextPaint(paint), (int) (glyRadius / 0.45f),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true);
        canvas.translate(cx, cy + glyRadius + mTextGlyphDivider);
        layout.draw(canvas);
        paint.reset();
    }

    private void drawEmptyCircle(float cx, float cy, Paint paint, Canvas canvas, float pointRadius,
                                 List<PointF> glyphPoints) {
        cx += pointRadius;
        cy += pointRadius;
        paint.setARGB(255, 128, 128, 128);
        canvas.drawCircle(cx, cy, pointRadius, paint);
        paint.setARGB(255, 255, 255, 255);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handler = false;
        if (mHackListener != null && mSequenceBounds != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                handler = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                for (int i = 0; i < mSequenceBounds.length; i++) {
                    if (mSequenceBounds[i].contains(event.getX(), event.getY())) {
                        mHackListener.onSequenceClicked(mSequenceNames[i]);
                        handler = true;
                        break;
                    }
                }
            }
        }
        return handler | super.onTouchEvent(event);
    }
}
