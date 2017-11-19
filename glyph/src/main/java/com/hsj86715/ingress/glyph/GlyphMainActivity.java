package com.hsj86715.ingress.glyph;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.pages.GlyphSequencesFragment;
import com.hsj86715.ingress.glyph.tools.Utils;
import com.hsj86715.ingress.glyphres.data.BaseGlyphData;
import com.hsj86715.ingress.glyphres.view.IngressGlyphView;
import com.hsj86715.ingress.glyphres.view.SequenceClickListener;

import java.util.Date;

/**
 * Created by hushujun on 16/5/16.
 */
public class GlyphMainActivity extends AppCompatActivity implements SequenceClickListener {
    private ViewGroup mGlyphContainer;
    private IngressGlyphView mGlyphSequenceView;
    private TextView mNameTx;

    private int[] mCurrentPath;
    private String mCurrentName;
    private Fragment mSequenceFrag;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mGlyphContainer = findViewById(R.id.glyph_container);
        mGlyphSequenceView = findViewById(R.id.glyph_view);
        mNameTx = findViewById(R.id.text_view);
        mSequenceFrag = new GlyphSequencesFragment();
        getFragmentManager().beginTransaction().add(R.id.frag_container, mSequenceFrag).commit();

        resumeSavedInstance(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.START_DATE, new Date().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    @Override
    protected void onDestroy() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.END_DATE, new Date().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle);
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resumeSavedInstance(savedInstanceState);
    }

    private void resumeSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        if (savedInstanceState.containsKey("path") && savedInstanceState.containsKey("name")) {
            mCurrentPath = savedInstanceState.getIntArray("path");
            mGlyphSequenceView.drawPath(mCurrentPath);
            mCurrentName = savedInstanceState.getString("name");
            mNameTx.setText(mCurrentName);
            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            outState = new Bundle();
        }
        outState.putIntArray("path", mCurrentPath);
        outState.putString("name", mCurrentName);
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
            mNameTx.setText(mCurrentName);
            mNameTx.setSelected(true);
            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT, sequenceName);
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Sequence");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    @Override
    public void clearSequence() {
//        mCurrentPath = null;
//        mCurrentName = "";
//        if (mNameTx != null) {
//            mNameTx.setText(mCurrentName);
//        }
//        if (mGlyphSequenceView != null) {
//            mGlyphSequenceView.clear();
//        }
    }
}
