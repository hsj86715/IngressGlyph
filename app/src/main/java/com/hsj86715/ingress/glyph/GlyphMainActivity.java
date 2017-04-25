package com.hsj86715.ingress.glyph;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hsj86715.ingress.glyph.data.BaseGlyphData;
import com.hsj86715.ingress.glyph.view.IngressGlyphView;
import com.hsj86715.ingress.glyph.view.SequenceClickListener;

/**
 * Created by hushujun on 16/5/16.
 */
public class GlyphMainActivity extends AppCompatActivity implements SequenceClickListener {
    private View mGlyphContainer;
    private IngressGlyphView mGlyphSequenceView;
    private TextView nameTx;


    private int[] mCurrentPath;
    private String mCurrentName;
    private Fragment mSequenceFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGlyphContainer = findViewById(R.id.glyph_container);
        mGlyphSequenceView = (IngressGlyphView) findViewById(R.id.glyph_view);
        nameTx = (TextView) findViewById(R.id.text_view);
        mSequenceFrag = getFragmentManager().findFragmentById(R.id.frag);
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
//            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));
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
        return mSequenceFrag.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    @Override
    public void onSequenceClicked(@BaseGlyphData.GlyphName String sequenceName) {
        if (mGlyphSequenceView.isDrawing()) {
            Toast.makeText(this, R.string.toast_last_is_drawing, Toast.LENGTH_SHORT).show();
        } else {
            mCurrentPath = BaseGlyphData.getInstance().getGlyphPath(sequenceName);
            mGlyphSequenceView.drawPath(mCurrentPath);
            mCurrentName = sequenceName;
            nameTx.setText(mCurrentName);
//            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));
        }
    }
}
