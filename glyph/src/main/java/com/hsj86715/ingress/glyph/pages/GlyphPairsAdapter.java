package com.hsj86715.ingress.glyph.pages;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.tools.Utils;

/**
 * Created by hushujun on 2017/5/16.
 */

public class GlyphPairsAdapter extends BaseRecyclerAdapter {
    private String[][] mGlyphPairs;

    public GlyphPairsAdapter(String[][] glyphPairs) {
        if (glyphPairs == null) {
            this.mGlyphPairs = new String[][]{};
        } else {
            this.mGlyphPairs = glyphPairs;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.glyphPreView.setSequences(mGlyphPairs[position]);
        holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphPairs[position][0]));
        holder.glyphPreView.setHackSequenceListener(mClickListener);
    }

    @Override
    public int getItemCount() {
        return mGlyphPairs.length;
    }

}
