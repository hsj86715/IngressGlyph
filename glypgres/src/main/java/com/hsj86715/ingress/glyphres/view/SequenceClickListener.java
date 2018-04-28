package com.hsj86715.ingress.glyphres.view;

import com.hsj86715.ingress.glyphres.data.GlyphInfo;

/**
 * Created by hushujun on 2017/5/17.
 * @author hushujun
 */

public interface SequenceClickListener {
    /**
     * When clicked sequence on screen will call this
     * @param glyphInfo
     */
    void onSequenceClicked(GlyphInfo glyphInfo);
}
