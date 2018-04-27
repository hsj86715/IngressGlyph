package com.hsj86715.ingress.glyph;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.pages.GlyphSequencesFragment;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.tools.Utils;
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

    private GlyphInfo mCurrentGlyph;
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
//
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                GlyphModel.getInstance(GlyphMainActivity.this).initBaseData();
                return null;
            }
        };
        asyncTask.execute();
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
        if (savedInstanceState.containsKey("glyph")) {
            mCurrentGlyph = savedInstanceState.getParcelable("glyph");
            mGlyphSequenceView.drawPath(mCurrentGlyph);
            mNameTx.setText(mCurrentGlyph.getName());
            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentGlyph.getName()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            outState = new Bundle();
        }
        outState.putParcelable("glyph", mCurrentGlyph);
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
    public void onSequenceClicked(GlyphInfo glyphInfo) {
        if (mGlyphSequenceView.isDrawing()) {
//            Toast.makeText(this, R.string.toast_last_is_drawing, Toast.LENGTH_SHORT).show();
            Snackbar.make(mGlyphSequenceView, R.string.toast_last_is_drawing, Snackbar.LENGTH_SHORT).show();
        } else {
            mGlyphSequenceView.drawPath(glyphInfo);
            mNameTx.setText(glyphInfo.getName());
            mNameTx.setSelected(true);
            mGlyphContainer.setBackgroundColor(Utils.stringToColor(glyphInfo.getName()));

            mCurrentGlyph = glyphInfo;

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT, glyphInfo.getName());
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Sequence");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    @Override
    public void clearSequence() {
//        mCurrentPath = null;
//        mCurrentGlyph = "";
//        if (mNameTx != null) {
//            mNameTx.setText(mCurrentGlyph);
//        }
//        if (mGlyphSequenceView != null) {
//            mGlyphSequenceView.clear();
//        }
    }
}
