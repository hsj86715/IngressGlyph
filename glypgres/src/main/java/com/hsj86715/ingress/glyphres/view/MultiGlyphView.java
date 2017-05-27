package com.hsj86715.ingress.glyphres.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hsj86715.ingress.glyphres.R;

/**
 * Created by hushujun on 2017/5/17.
 */

public class MultiGlyphView extends IconView {
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
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiGlyphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
            drawSequence(canvas, cx - pointRadius, cy - pointRadius, mSequenceNames[i], paint, glyphRadius, pointRadius);
            canvas.restore();
            if (mHackListener != null) {
                mSequenceBounds[i] = new RectF(cx - glyphRadius, cy - glyphRadius, cx + glyphRadius,
                        cy + glyphRadius + mTextGlyphDivider + mTextSize * 2);
            }
        }
    }

    @Override
    protected void drawSequence(Canvas canvas, float cx, float cy, String name, Paint paint,
                                float glyRadius, float pointRadius) {
        super.drawSequence(canvas, cx, cy, name, paint, glyRadius, pointRadius);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);
        StaticLayout layout = new StaticLayout(name, new TextPaint(paint), (int) (glyRadius / 0.45f),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true);
        canvas.translate(cx, cy + glyRadius + mTextGlyphDivider);
        layout.draw(canvas);
        paint.reset();
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
