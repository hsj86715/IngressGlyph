package com.hsj86715.ingress.glyph;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hushujun on 16/5/17.
 */
public class GlyphAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return GLYPH.getGlyphs().size();
    }

    @Override
    public GLYPH getItem(int position) {
        return GLYPH.getGlyphs().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setText(GLYPH.getGlyphs().get(position).getName());
        textView.setPadding(5, 10, 5, 10);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
