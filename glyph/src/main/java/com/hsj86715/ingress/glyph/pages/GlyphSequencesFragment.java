package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;
import com.hsj86715.ingress.glyphres.data.BaseGlyphData;
import com.hsj86715.ingress.glyphres.view.SequenceClickListener;

import java.util.Map;

/**
 * Created by hushujun on 2017/5/17.
 */

public class GlyphSequencesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "GlyphSequencesFragment";
    private static final String PAGE_BASE = "Base";
    private static final String PAGE_PAIRS = "Pairs";
    private static final String PAGE_2HACK = "2Hacks";
    private static final String PAGE_3HACK = "3Hacks";
    private static final String PAGE_4HACK = "4Hacks";
    private static final String PAGE_5HACK = "5Hacks";

    private SequenceClickListener mSequenceListener;

    private BaseRecyclerAdapter mGlyphAdapter;
    private HorizontalScrollView mCategoryContainer;
    private RadioGroup mCategoryRG;
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    //    private PinnedSectionDecoration mSectionDecoration;
    private FirebaseAnalytics mFirebaseAnalytics;

    private String mWhichPage = PAGE_BASE;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_sequences, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.grid_base);
        mRecyclerView.addItemDecoration(new SimpleItemDecoration());
        mCategoryContainer = view.findViewById(R.id.category_container);
        mCategoryRG = view.findViewById(R.id.categories);
        mCategoryRG.setOnCheckedChangeListener(this);

        if (savedInstanceState != null && savedInstanceState.containsKey("whichPage")) {
            mWhichPage = savedInstanceState.getString("whichPage");
            initPage(mWhichPage);
        } else {
            mCategoryContainer.setVisibility(View.VISIBLE);
            showBaseSequences();
        }
    }

    @GlyphPage
    private void initPage(String whichPage) {
        switch (whichPage) {
            case PAGE_PAIRS:
                showPairsSequences();
                break;
            case PAGE_2HACK:
                updateSequences(2);
                break;
            case PAGE_3HACK:
                updateSequences(3);
                break;
            case PAGE_4HACK:
                updateSequences(4);
                break;
            case PAGE_5HACK:
                updateSequences(5);
                break;
            case PAGE_BASE:
            default:
                showBaseSequences();
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SequenceClickListener) {
            mSequenceListener = (SequenceClickListener) activity;
        }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            outState = new Bundle();
        }
        outState.putString("whichPage", mWhichPage);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(item.getItemId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionMenu");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        switch (item.getItemId()) {
            case R.id.base:
                mWhichPage = PAGE_BASE;
                showBaseSequences();
                break;
            case R.id.pairs:
                mWhichPage = PAGE_PAIRS;
                showPairsSequences();
                break;
            case R.id.two:
                mWhichPage = PAGE_2HACK;
                updateSequences(2);
                break;
            case R.id.three:
                mWhichPage = PAGE_3HACK;
                updateSequences(3);
                break;
            case R.id.four:
                mWhichPage = PAGE_4HACK;
                updateSequences(4);
                break;
            case R.id.five:
                mWhichPage = PAGE_5HACK;
                updateSequences(5);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBaseSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphBaseAdapter) {
            return;
        } else {
            updateToolBarSubTitle("Base Glyphs");
//                    mRecyclerView.removeItemDecoration(mSectionDecoration);
            if (mSequenceListener != null) {
                mSequenceListener.clearSequence();
            }
            mCategoryContainer.setVisibility(View.VISIBLE);
            if (mLayoutManager != null && mLayoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(3);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 3);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
            mGlyphAdapter = new GlyphBaseAdapter();
            mGlyphAdapter.setSequenceClickListener(mSequenceListener);
            mRecyclerView.setAdapter(mGlyphAdapter);
            int checkedId = mCategoryRG.getCheckedRadioButtonId();
            onCheckedChanged(mCategoryRG, checkedId);
        }
    }

    private void showPairsSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphPairsAdapter) {
            return;
        } else {
            updateToolBarSubTitle("Glyph Pairs");
            if (mSequenceListener != null) {
                mSequenceListener.clearSequence();
            }
            if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphBaseAdapter) {
                mCategoryContainer.setVisibility(View.GONE);
            }
//                    mRecyclerView.removeItemDecoration(mSectionDecoration);
            if (mLayoutManager != null && mLayoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(2);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
            mGlyphAdapter = new GlyphPairsAdapter(BaseGlyphData.getInstance().getGlyphPairs());
            mGlyphAdapter.setSequenceClickListener(mSequenceListener);
            mRecyclerView.setAdapter(mGlyphAdapter);
        }
    }

    private void updateSequences(int sequenceLength) {
        Map<String, String[][]> data;
        switch (sequenceLength) {
            case 3:
                data = BaseGlyphData.getInstance().getThreeSequences();
                break;
            case 4:
                data = BaseGlyphData.getInstance().getFourSequences();
                break;
            case 5:
                data = BaseGlyphData.getInstance().getFiveSequences();
                break;
            case 2:
            default:
                data = BaseGlyphData.getInstance().getTwoSequences();
                break;
        }
        if (mLayoutManager == null || mLayoutManager instanceof GridLayoutManager) {
            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if (mGlyphAdapter != null && mGlyphAdapter instanceof HackSequencesAdapter) {
            if (((HackSequencesAdapter) mGlyphAdapter).getSequencesLength() == sequenceLength) {
                return;
            } else {
                if (mSequenceListener != null) {
                    mSequenceListener.clearSequence();
                }
                updateToolBarSubTitle(sequenceLength + " Glyph Hack Sequences");
                ((HackSequencesAdapter) mGlyphAdapter).setHackSequences(data, sequenceLength);
            }
        } else {
            if (mGlyphAdapter != null && mGlyphAdapter instanceof GlyphBaseAdapter) {
                mCategoryContainer.setVisibility(View.GONE);
            }
            if (mSequenceListener != null) {
                mSequenceListener.clearSequence();
            }
            updateToolBarSubTitle(sequenceLength + " Glyph Hack Sequences");
            mGlyphAdapter = new HackSequencesAdapter();
//            if (mSectionDecoration == null) {
//                mSectionDecoration = new PinnedSectionDecoration(getContext(),
//                        (PinnedSectionDecoration.DecorationCallback) mGlyphAdapter);
//            } else {
//                mSectionDecoration.setDecorationCallback((PinnedSectionDecoration.DecorationCallback) mGlyphAdapter);
//            }
//            mRecyclerView.addItemDecoration(mSectionDecoration);
            mGlyphAdapter.setSequenceClickListener(mSequenceListener);
            ((HackSequencesAdapter) mGlyphAdapter).setHackSequences(data, sequenceLength);
            mRecyclerView.setAdapter(mGlyphAdapter);
        }
    }

    private void updateToolBarSubTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(title);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        String category = null;
        switch (checkedId) {
            case R.id.category_human:
                category = BaseGlyphData.C_HUMAN;
                break;
            case R.id.category_action:
                category = BaseGlyphData.C_ACTION;
                break;
            case R.id.category_thought:
                category = BaseGlyphData.C_THOUGHT;
                break;
            case R.id.category_fludire:
                category = BaseGlyphData.C_FLU_DIRE;
                break;
            case R.id.category_ts:
                category = BaseGlyphData.C_TIME_SPACE;
                break;
            case R.id.category_conenv:
                category = BaseGlyphData.C_COND_ENV;
                break;
//            case R.id.category_new:
//                category = BaseGlyphData.C_NEW_ADDED;
//                break;
//            case R.id.category_no:
//                category = BaseGlyphData.C_NO_CAREGORY;
//                break;
            case R.id.category_all:
            default:
                category = BaseGlyphData.C_ALL;
                break;
        }
        if (mGlyphAdapter instanceof GlyphBaseAdapter) {
            ((GlyphBaseAdapter) mGlyphAdapter).setGlyphCategory(category);
        }

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(checkedId));
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @StringDef({PAGE_BASE, PAGE_PAIRS, PAGE_2HACK, PAGE_3HACK, PAGE_4HACK, PAGE_5HACK})
    @interface GlyphPage {

    }
}
