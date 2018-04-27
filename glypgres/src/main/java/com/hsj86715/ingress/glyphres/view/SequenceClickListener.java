package com.hsj86715.ingress.glyphres.view;

import com.hsj86715.ingress.glyphres.data.GlyphInfo;

/**
 * Created by hushujun on 2017/5/17.
 */

public interface SequenceClickListener {
    void onSequenceClicked(GlyphInfo glyphInfo);

    void clearSequence();
}
