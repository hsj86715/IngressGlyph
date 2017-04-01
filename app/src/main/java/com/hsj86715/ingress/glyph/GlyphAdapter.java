package com.hsj86715.ingress.glyph;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hushujun on 16/5/17.
 */
public class GlyphAdapter extends BaseAdapter {
    private List<GLYPH> mCategoryGlyphs;

    public void setGlyphCategory(String category) {
        mCategoryGlyphs = GLYPH.getGlyphs(category);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCategoryGlyphs.size();
    }

    @Override
    public GLYPH getItem(int position) {
        return mCategoryGlyphs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_glyph, parent, false);
            holder = new ViewHolder();
            holder.glyphPreView = (IngressGlyphView) convertView.findViewById(R.id.item_glyph_pre);
            holder.nameTx = (TextView) convertView.findViewById(R.id.item_glyph_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTx.setText(mCategoryGlyphs.get(position).getName());
        holder.glyphPreView.setPath(mCategoryGlyphs.get(position).getPath());
        return convertView;
    }

    class ViewHolder {
        IngressGlyphView glyphPreView;
        TextView nameTx;
    }
}
