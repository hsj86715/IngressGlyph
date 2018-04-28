package com.hsj86715.ingress.glyph.pages;

import android.view.View;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 2017/5/16.
 * @author hushujun
 */

public class GlyphBaseAdapter extends BaseRecyclerAdapter {
    private List<GlyphInfo> mGlyphInfos;

    public GlyphBaseAdapter(List<GlyphInfo> glyphInfos) {
        if (glyphInfos == null) {
            mGlyphInfos = new ArrayList<>();
        } else {
            mGlyphInfos = glyphInfos;
        }
    }

    public GlyphBaseAdapter() {

    }

    public void setGlyphCategory(List<GlyphInfo> glyphInfos) {
        mGlyphInfos = glyphInfos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final GlyphInfo info = mGlyphInfos.get(position);
        holder.glyphPreView.setSequences(info);
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
