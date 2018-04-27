package com.hsj86715.ingress.glyph.pages;

import android.view.View;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.List;

/**
 * Created by hushujun on 2017/5/16.
 */

public class GlyphBaseAdapter extends BaseRecyclerAdapter {
    private List<GlyphInfo> mGlyphInfos;

    public void setGlyphCategory(List<GlyphInfo> glyphInfos) {
        mGlyphInfos = glyphInfos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final GlyphInfo info = mGlyphInfos.get(position);
        holder.glyphPreView.setSequences(info);
        holder.itemView.setBackgroundColor(Utils.stringToColor(info.getName()));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onSequenceClicked(info);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mGlyphInfos == null) {
            return 0;
        }
        return mGlyphInfos.size();
    }
}
