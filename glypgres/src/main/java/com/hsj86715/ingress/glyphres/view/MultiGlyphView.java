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
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hsj86715.ingress.glyphres.R;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.tools.Utils;

/**
 * Created by hushujun on 2017/5/17.
 *
 * @author hushujun
 */

public class MultiGlyphView extends IconView {
    private static final String TAG = "MultiGlyphView";

    private float mTextSize;
    private int mTextColor;
    private float mGlyphRadius;
    private float mSequencesDivider;
    private float mTextGlyphDivider;

    private SequenceClickListener mHackListener;
    private GlyphInfo[] mSequences;
    private RectF[] mSequenceBounds;
    private boolean[] mResults;
    private long[] mSequenceCosts;

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

    public void setSequences(GlyphInfo... glyphInfos) {
        this.mSequences = glyphInfos;
        if (mSequences != null && mSequences.length > 0) {
            mSequenceBounds = new RectF[mSequences.length];
            invalidate();
        }
    }

    public void setSequenceResult(GlyphInfo[] glyphInfos, long[] costs, boolean[] results) {
        this.mSequences = glyphInfos;
        if (mSequences != null && mSequences.length > 0) {
            mSequenceBounds = new RectF[mSequences.length];
            if (costs != null) {
                this.mSequenceCosts = costs;
            }
            if (results != null) {
                this.mResults = results;
            }
            invalidate();
        }
    }

    public void clear() {
        mSequences = null;
        invalidate();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        int miniHeight = (int) (getPaddingTop() + getPaddingBottom() + mGlyphRadius * 2 + mTextGlyphDivider + mTextSize * 2);
        return getBackground() == null ? miniHeight : Math.max(miniHeight, getBackground().getMinimumHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSequences == null || mSequences.length < 1) {
            return;
        }
        int count = mSequences.length;
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
            drawSequence(canvas, cx - pointRadius, cy - pointRadius, mSequences[i], paint, glyphRadius, pointRadius, i);
            canvas.restore();
            mSequenceBounds[i] = new RectF(cx - glyphRadius, cy - glyphRadius, cx + glyphRadius,
                    cy + glyphRadius + mTextGlyphDivider + mTextSize * 2);
            if (mSequenceCosts != null && i < mSequenceCosts.length) {
                drawTimeCost(canvas, paint, mSequenceBounds[i], mSequenceCosts[i]);
            }
        }
    }

    @Override
    protected int getPathColor(int index) {
        if (mResults != null && index < mResults.length) {
            boolean result = mResults[index];
            if (result) {
                return Color.GREEN;
            } else {
                return Color.RED;
            }
        } else {
            return super.getPathColor(index);
        }
    }

    private int getTextColor(int index) {
        if (mResults != null && index < mResults.length) {
            boolean result = mResults[index];
            if (result) {
                return Color.GREEN;
            } else {
                return Color.RED;
            }
        } else {
            return mTextColor;
        }
    }

    @Override
    protected void drawSequence(Canvas canvas, float cx, float cy, GlyphInfo glyphInfo, Paint paint,
                                float glyRadius, float pointRadius, int index) {
        super.drawSequence(canvas, cx, cy, glyphInfo, paint, glyRadius, pointRadius, index);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mTextSize);
        paint.setColor(getTextColor(index));
        StaticLayout layout = new StaticLayout(glyphInfo.getName(), new TextPaint(paint), (int) (glyRadius / 0.45f),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true);
        canvas.translate(cx, cy + glyRadius + mTextGlyphDivider);
        layout.draw(canvas);
        paint.reset();
    }

    private void drawTimeCost(Canvas canvas, Paint paint, RectF rectF, long timeCost) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(mTextSize / 2);
        paint.setColor(mTextColor);
        canvas.drawText(Utils.timeToSeconds(timeCost), rectF.left, rectF.top - mTextSize / 2, paint);
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
                        mHackListener.onSequenceClicked(mSequences[i]);
                        handler = true;
                        break;
                    }
                }
            }
        }
        return handler | super.onTouchEvent(event);
    }
}
