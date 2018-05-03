package com.hsj86715.ingress.glyph.pages;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 2017/5/16.
 * @author hushujun
 */

public class GlyphPairsAdapter extends BaseRecyclerAdapter {
    private List<GlyphInfo[]> mGlyphPairs;

    public GlyphPairsAdapter(List<GlyphInfo[]> glyphPairs) {
        if (glyphPairs == null) {
            this.mGlyphPairs = new ArrayList<>();
        } else {
            this.mGlyphPairs = glyphPairs;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlyphInfo[] infos = mGlyphPairs.get(position);
        holder.glyphPreView.setSequences(infos);
        holder.glyphPreView.setHackSequenceListener(mClickListener);
    }

    @Override
    public int getItemCount() {
        return mGlyphPairs.size();
    }

}
