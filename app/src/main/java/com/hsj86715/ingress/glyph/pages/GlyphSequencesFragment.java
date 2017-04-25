package com.hsj86715.ingress.glyph.pages;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.data.BaseGlyphData;
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyph.view.SequenceClickListener;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;

import java.util.Map;

/**
 * Created by hushujun on 2017/4/24.
 */

public class GlyphSequencesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "GlyphSequencesFragment";

    private SequenceClickListener mSequenceListener;

    private BaseRecyclerAdapter mGlyphAdapter;
    private HorizontalScrollView mCategoryContainer;
    private RadioGroup mCategoryRG;
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
//    private PinnedSectionDecoration mSectionDecoration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.grid_base);
        mLayoutManager = new GridLayoutManager(view.getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleItemDecoration());
        mGlyphAdapter = new GlyphBaseAdapter();
        mGlyphAdapter.setSequenceClickListener(mSequenceListener);
        ((GlyphBaseAdapter) mGlyphAdapter).setGlyphCategory(BaseGlyphData.C_ALL);
        mRecyclerView.setAdapter(mGlyphAdapter);
        mCategoryContainer = (HorizontalScrollView) view.findViewById(R.id.category_container);
        mCategoryRG = (RadioGroup) view.findViewById(R.id.categories);
        mCategoryRG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SequenceClickListener) {
            mSequenceListener = (SequenceClickListener) activity;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.base:
                if (mGlyphAdapter instanceof GlyphBaseAdapter) {
                    break;
                } else {
//                    mRecyclerView.removeItemDecoration(mSectionDecoration);
                    mCategoryContainer.setVisibility(View.VISIBLE);
                    if (mLayoutManager instanceof GridLayoutManager) {
                        ((GridLayoutManager) mLayoutManager).setSpanCount(3);
                    } else {
                        mLayoutManager = new GridLayoutManager(getContext(), 3);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                    }
                    mGlyphAdapter = new GlyphBaseAdapter();
                    mGlyphAdapter.setSequenceClickListener(mSequenceListener);
                    mRecyclerView.setAdapter(mGlyphAdapter);
                    int checkedId = mCategoryRG.getCheckedRadioButtonId();
                    onCheckedChanged(mCategoryRG, checkedId);
                }
                break;
            case R.id.pairs:
                if (mGlyphAdapter instanceof GlyphPairsAdapter) {
                    break;
                } else {
                    if (mGlyphAdapter instanceof GlyphBaseAdapter) {
                        mCategoryContainer.setVisibility(View.GONE);
                    }
//                    mRecyclerView.removeItemDecoration(mSectionDecoration);
                    if (mLayoutManager instanceof GridLayoutManager) {
                        ((GridLayoutManager) mLayoutManager).setSpanCount(2);
                    } else {
                        mLayoutManager = new GridLayoutManager(getContext(), 2);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                    }
                    mGlyphAdapter = new GlyphPairsAdapter(BaseGlyphData.getInstance().getGlyphPairs());
                    mGlyphAdapter.setSequenceClickListener(mSequenceListener);
                    mRecyclerView.setAdapter(mGlyphAdapter);
                }
                break;
            case R.id.two:
                updateSequences(2);
                break;
            case R.id.three:
                updateSequences(3);
                break;
            case R.id.four:
                updateSequences(4);
                break;
            case R.id.five:
                updateSequences(5);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.M)
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
        if (mLayoutManager instanceof GridLayoutManager) {
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if (mGlyphAdapter instanceof HackSequencesAdapter) {
            if (((HackSequencesAdapter) mGlyphAdapter).getSequencesLength() == sequenceLength) {
                return;
            } else {
                ((HackSequencesAdapter) mGlyphAdapter).setHackSequences(data, sequenceLength);
            }
        } else {
            if (mGlyphAdapter instanceof GlyphBaseAdapter) {
                mCategoryContainer.setVisibility(View.GONE);
            }
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
            case R.id.category_all:
            default:
                category = BaseGlyphData.C_ALL;
                break;
        }
        if (mGlyphAdapter instanceof GlyphBaseAdapter) {
            ((GlyphBaseAdapter) mGlyphAdapter).setGlyphCategory(category);
        }
    }
}
