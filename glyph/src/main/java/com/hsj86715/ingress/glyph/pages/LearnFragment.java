package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.EditGlyphActivity;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;
import com.hsj86715.ingress.glyphres.data.Constants;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.Name;
import com.hsj86715.ingress.glyphres.view.IngressGlyphView;
import com.hsj86715.ingress.glyphres.view.SequenceClickListener;

import java.util.List;

import cn.com.farmcode.utility.tools.Logger;

/**
 * @author hushujun
 */

public class LearnFragment extends Fragment implements SequenceClickListener {
    private IngressGlyphView mGlyphView;
    private TextView mGlyphNameTx, mGlyphAlisaTx, mGlyphCatTx;
    private RecyclerView mGlyphsRv;

    private BaseRecyclerAdapter mGlyphAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GlyphInfo mCurrentGlyph;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_learn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Logger.i("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        mGlyphView = view.findViewById(R.id.learn_glyph_view);
        mGlyphNameTx = view.findViewById(R.id.learn_glyph_name_text);
        mGlyphAlisaTx = view.findViewById(R.id.learn_glyph_alisa_text);
        mGlyphCatTx = view.findViewById(R.id.learn_glyph_cat_text);
        mGlyphsRv = view.findViewById(R.id.learn_glyphs_rv);
//        TextView editTx = view.findViewById(R.id.learn_glyph_edit_text);
//        editTx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), EditGlyphActivity.class);
//                intent.putExtra("glyph", mCurrentGlyph);
//                startActivityForResult(intent, 0);
//
//                Bundle bundle = new Bundle();
//                bundle.putString(FirebaseAnalytics.Param.CONTENT, mCurrentGlyph.getName());
//                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Glyph");
//                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
//            }
//        });

        mGlyphsRv.addItemDecoration(new SimpleItemDecoration());

        showBaseSequences();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionLearn");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        if (id == R.id.learn_base) {
            showBaseSequences();
        } else if (id == R.id.learn_pairs) {
            showPairsSequences();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBaseSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphBaseAdapter) {
            return;
        } else {
            updateToolBarSubTitle(R.string.option_menu_base);
            if (mLayoutManager != null && mLayoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(3);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 3);
                mGlyphsRv.setLayoutManager(mLayoutManager);
            }
            new BaseGlyphTask().execute();
        }
    }

    private void showPairsSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphPairsAdapter) {
            return;
        } else {
            updateToolBarSubTitle(R.string.option_menu_pairs);
            if (mLayoutManager != null && mLayoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(2);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                mGlyphsRv.setLayoutManager(mLayoutManager);
            }
            new PairsGlyphTask().execute();
        }
    }

    private void updateToolBarSubTitle(int titleRes) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setSubtitle(titleRes);
        }
    }

    private void updatePreview(GlyphInfo glyphInfo) {
        mGlyphView.drawPath(glyphInfo);
        mGlyphNameTx.setText(glyphInfo.getName());
        mGlyphNameTx.setSelected(true);
        mGlyphCatTx.setText(glyphInfo.getCategory());

        mCurrentGlyph = glyphInfo;
        new NameTask().execute(glyphInfo.getNameId());
//        new PathsTask().execute(glyphInfo.getPathId());

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT, glyphInfo.getName());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Glyph");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
    }

    @Override
    public void onSequenceClicked(GlyphInfo glyphInfo) {
        if (mGlyphView.isDrawing()) {
            Snackbar.make(mGlyphView, R.string.toast_last_is_drawing, Snackbar.LENGTH_SHORT).show();
        } else {
            updatePreview(glyphInfo);
            new UpdateLearnCountTask().execute(glyphInfo);
        }
    }

    private class BaseGlyphTask extends AsyncTask<Void, Void, List<GlyphInfo>> {

        @Override
        protected List<GlyphInfo> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getGlyphInfoByCategory(Constants.C_ALL);
        }

        @Override
        protected void onPostExecute(List<GlyphInfo> glyphInfos) {
            mGlyphAdapter = new GlyphBaseAdapter(glyphInfos);
            mGlyphAdapter.setSequenceClickListener(LearnFragment.this);
            mGlyphsRv.setAdapter(mGlyphAdapter);
            if (mCurrentGlyph == null) {
                updatePreview(glyphInfos.get(0));
            }
        }
    }

    private class PairsGlyphTask extends AsyncTask<Void, Void, List<GlyphInfo[]>> {

        @Override
        protected List<GlyphInfo[]> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getGlyphPairs();
        }

        @Override
        protected void onPostExecute(List<GlyphInfo[]> glyphInfos) {
            mGlyphAdapter = new GlyphPairsAdapter(glyphInfos);
            mGlyphAdapter.setSequenceClickListener(LearnFragment.this);
            mGlyphsRv.setAdapter(mGlyphAdapter);
        }
    }

    private class NameTask extends AsyncTask<Long, Void, Name> {

        @Override
        protected Name doInBackground(Long... longs) {
            return GlyphModel.getInstance(getActivity()).getGlyphNames(longs[0]);
        }

        @Override
        protected void onPostExecute(Name name) {
            if (name != null && name.getId() == mCurrentGlyph.getNameId()) {
                String alisa = name.getAlisaString();
                if (TextUtils.isEmpty(alisa)) {
                    mGlyphAlisaTx.setText(R.string.learn_glyph_alisa_empty);
                } else {
                    mGlyphAlisaTx.setText(alisa);
                }
            }
        }
    }

    private class UpdateLearnCountTask extends AsyncTask<GlyphInfo, Void, Integer> {
        @Override
        protected Integer doInBackground(GlyphInfo... longs) {
            return GlyphModel.getInstance(getActivity()).updateGlyphLearCount(longs[0]);
        }
    }

//    private class PathsTask extends AsyncTask<Integer, Void, Path> {
//
//        @Override
//        protected Path doInBackground(Integer... integers) {
//            return GlyphModel.getInstance(getActivity()).getGlyphPaths(integers[0]);
//        }
//
//        @Override
//        protected void onPostExecute(Path path) {
//            if (path != null && path.getId() == mCurrentGlyph.getPathId()) {
//                // TODO: 2018/4/28
//            }
//        }
//    }
}
