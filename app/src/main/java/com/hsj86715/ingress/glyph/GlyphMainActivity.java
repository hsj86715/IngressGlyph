package com.hsj86715.ingress.glyph;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by hushujun on 16/5/16.
 */
public class GlyphMainActivity extends Activity implements AdapterView.OnItemClickListener {
    private IngressGlyphView glyphView;
    private TextView nameTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glyphView = (IngressGlyphView) findViewById(R.id.glyph_view);
        nameTx = (TextView) findViewById(R.id.text_view);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new GlyphAdapter());
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        glyphView.drawPath(GLYPH.getGlyphs().get(position).getPositionPath());
        nameTx.setText(GLYPH.getGlyphs().get(position).getName());
    }
}
