package com.hsj86715.ingress.glyph.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsj86715.ingress.glyph.R;

import java.util.Map;

/**
 * Created by hushujun on 2017/4/6.
 */

public class HackSequencesView extends LinearLayout {
    private static final String TAG = "HackSequencesView";
    private HackSequenceListener mHackListener;

    public HackSequencesView(Context context) {
        this(context, null);
    }

    public HackSequencesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HackSequencesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HackSequencesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setSequenceClickListener(HackSequenceListener listener) {
        this.mHackListener = listener;
    }

    public void setHackSequence(Map<String, int[]> sequences) {
        if (sequences == null || sequences.isEmpty()) {
            removeAllViews();
        } else {
            int childCount = getChildCount();
            int sequenceSize = sequences.size();
            if (childCount < sequenceSize) {
                int more = sequenceSize - childCount;
                LayoutInflater inflater = LayoutInflater.from(getContext());
                while (more > 0) {
                    View view = inflater.inflate(R.layout.item_simple_sequence, this, false);
                    addView(view);
                    more--;
                }
            } else if (childCount > sequenceSize) {
                int reduce = childCount - sequenceSize;
                removeViews(0, reduce);
            }

            Map.Entry<String, int[]>[] sequenceEntries = new Map.Entry[sequenceSize];
            sequenceEntries = sequences.entrySet().toArray(sequenceEntries);
            childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                final Map.Entry<String, int[]> sequenceEntry = sequenceEntries[i];
                ((IngressGlyphView) childView.findViewById(R.id.item_sequence_pre)).drawPath(sequenceEntry.getValue());
                ((TextView) childView.findViewById(R.id.item_sequence_name)).setText(sequenceEntry.getKey());
                if (mHackListener != null) {
                    childView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHackListener.onSequenceClicked(sequenceEntry);
                        }
                    });
                }
            }
        }
    }
}
