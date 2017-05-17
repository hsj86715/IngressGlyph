package com.hsj86715.ingress.glyph.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.view.MultiGlyphView;
import com.hsj86715.ingress.glyphres.view.SequenceClickListener;

/**
 * Created by hushujun on 2017/5/16.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {
    protected SequenceClickListener mClickListener;

    public void setSequenceClickListener(SequenceClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_glyph_base, parent, false));
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public MultiGlyphView glyphPreView;

        public ViewHolder(View itemView) {
            super(itemView);
            glyphPreView = (MultiGlyphView) itemView.findViewById(R.id.item_glyph_pre);
        }
    }
}
