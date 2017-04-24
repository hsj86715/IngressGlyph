package com.hsj86715.ingress.glyph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.hsj86715.ingress.glyph.data.BaseGlyphData;
import com.hsj86715.ingress.glyph.pages.GlyphBaseFragment;
import com.hsj86715.ingress.glyph.pages.GlyphPairsFragment;
import com.hsj86715.ingress.glyph.pages.HackSequencesFragment;
import com.hsj86715.ingress.glyph.view.HackSequenceListener;
import com.hsj86715.ingress.glyph.view.IngressGlyphView;

/**
 * Created by hushujun on 16/5/16.
 */
public class GlyphMainActivity extends AppCompatActivity implements HackSequenceListener {
    private IngressGlyphView mGlyphSequenceView;
    private TextView nameTx;


    private int[] mCurrentPath;
    private String mCurrentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGlyphSequenceView = (IngressGlyphView) findViewById(R.id.glyph_view);
        nameTx = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("path") && savedInstanceState.containsKey("name")) {
            mCurrentPath = savedInstanceState.getIntArray("path");
            if (mCurrentPath != null) {
                mGlyphSequenceView.drawPath(mCurrentPath);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.base:
                getFragmentManager().beginTransaction().replace(R.id.frag_container, new GlyphBaseFragment()).commit();
                break;
            case R.id.pairs:
                getFragmentManager().beginTransaction().replace(R.id.frag_container, new GlyphPairsFragment()).commit();
                break;
            case R.id.two:
                getFragmentManager().beginTransaction().replace(R.id.frag_container, new HackSequencesFragment()).commit();
                break;
            case R.id.three:

                break;
            case R.id.four:

                break;
            case R.id.five:

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onSequenceClicked(@BaseGlyphData.GlyphName String sequenceName) {
        if (mGlyphSequenceView.isDrawing()) {
            Toast.makeText(this, "上一个图形还未演示完成", Toast.LENGTH_SHORT).show();
        } else {
            mCurrentPath = BaseGlyphData.getInstance().getGlyphPath(sequenceName);
            mGlyphSequenceView.drawPath(mCurrentPath);
            mCurrentName = sequenceName;
            nameTx.setText(mCurrentName);
        }
    }
}
