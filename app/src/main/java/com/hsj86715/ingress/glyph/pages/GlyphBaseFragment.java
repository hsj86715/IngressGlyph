package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.data.BaseGlyphData;
import com.hsj86715.ingress.glyph.view.HackSequenceListener;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Created by hushujun on 2017/4/7.
 */

public class GlyphBaseFragment extends Fragment implements HackSequenceListener,
        RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "GlyphBaseFragment";

    private GlyphBaseAdapter mGlyphAdapter;
    private HackSequenceListener mSequenceListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView gridView = (RecyclerView) view.findViewById(R.id.grid_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        gridView.setLayoutManager(gridLayoutManager);
        gridView.addItemDecoration(new SimpleItemDecoration(gridLayoutManager));
        mGlyphAdapter = new GlyphBaseAdapter();
        mGlyphAdapter.setGlyphCategory(BaseGlyphData.C_ALL);
        mGlyphAdapter.setSequenceClickListener(this);
        gridView.setAdapter(mGlyphAdapter);
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.categories);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof HackSequenceListener) {
            mSequenceListener = (HackSequenceListener) activity;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSequenceClicked(@NotNull Map.Entry<String, int[]> sequenceEntry) {
        if (mSequenceListener != null) {
            mSequenceListener.onSequenceClicked(sequenceEntry);
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
        mGlyphAdapter.setGlyphCategory(category);
    }
}
