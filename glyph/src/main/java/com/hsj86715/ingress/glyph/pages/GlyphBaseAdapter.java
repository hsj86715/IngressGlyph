package com.hsj86715.ingress.glyph.pages;

import android.view.View;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.data.BaseGlyphData;
import com.hsj86715.ingress.glyphres.data.BaseGlyphData.Category;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hushujun on 2017/5/16.
 */

public class GlyphBaseAdapter extends BaseRecyclerAdapter {
    private List<String> mGlyphKeys;

    public void setGlyphCategory(@Category String category) {
        Map<String, int[]> glyphs = BaseGlyphData.getInstance().getGlyphByCategory(category);
        mGlyphKeys = new ArrayList<>(glyphs.keySet());
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.glyphPreView.setSequences(mGlyphKeys.get(position));
        holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphKeys.get(position)));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onSequenceClicked(mGlyphKeys.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mGlyphKeys.size();
    }
}
