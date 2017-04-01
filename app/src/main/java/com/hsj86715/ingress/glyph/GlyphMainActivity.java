package com.hsj86715.ingress.glyph;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hushujun on 16/5/16.
 */
public class GlyphMainActivity extends Activity implements AdapterView.OnItemClickListener,
        RadioGroup.OnCheckedChangeListener {
    private IngressGlyphView glyphView;
    private TextView nameTx;
    private GlyphAdapter mGlyphAdapter;

    private int[] mCurrentPath;
    private String mCurrentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glyphView = (IngressGlyphView) findViewById(R.id.glyph_view);
        nameTx = (TextView) findViewById(R.id.text_view);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        mGlyphAdapter = new GlyphAdapter();
        mGlyphAdapter.setGlyphCategory(GLYPH.C_ALL);
        gridView.setAdapter(mGlyphAdapter);
        gridView.setOnItemClickListener(this);

        RadioGroup rg = (RadioGroup) findViewById(R.id.categories);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("path") && savedInstanceState.containsKey("name")) {
            mCurrentPath = savedInstanceState.getIntArray("path");
            if (mCurrentPath != null) {
                glyphView.setPath(mCurrentPath);
            }
            mCurrentName = savedInstanceState.getString("name");
            nameTx.setText(mCurrentName);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mCurrentPath != null) {
            outState.putIntArray("path", mCurrentPath);
            outState.putString("name", mCurrentName);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (glyphView.isDrawing()) {
            Toast.makeText(this, "上一个图形还未演示完成", Toast.LENGTH_SHORT).show();
        } else {
            mCurrentPath = mGlyphAdapter.getItem(position).getPath();
            glyphView.setPath(mCurrentPath);
            mCurrentName = mGlyphAdapter.getItem(position).getName();
            nameTx.setText(mCurrentName);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        String category = null;
        switch (checkedId) {
            case R.id.category_human:
                category = GLYPH.C_HUMAN;
                break;
            case R.id.category_action:
                category = GLYPH.C_ACTION;
                break;
            case R.id.category_thought:
                category = GLYPH.C_THOUGHT;
                break;
            case R.id.category_fludire:
                category = GLYPH.C_FLU_DIRE;
                break;
            case R.id.category_ts:
                category = GLYPH.C_TIME_SPACE;
                break;
            case R.id.category_conenv:
                category = GLYPH.C_COND_ENV;
                break;
            case R.id.category_all:
            default:
                category = GLYPH.C_ALL;
                break;
        }
        mGlyphAdapter.setGlyphCategory(category);
    }
}
