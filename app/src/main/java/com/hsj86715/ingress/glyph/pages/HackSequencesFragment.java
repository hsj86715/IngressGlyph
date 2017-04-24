package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.data.BaseGlyphData;
import com.hsj86715.ingress.glyph.view.HackSequenceListener;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;

/**
 * Created by hushujun on 2017/4/21.
 */

public class HackSequencesFragment extends Fragment {
    private HackSequenceListener mSequenceListener;
    private HackSequencesAdapter mHackAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_simple_recycle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView gridView = (RecyclerView) view.findViewById(R.id.grid_pairs);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        gridView.setLayoutManager(gridLayoutManager);
        gridView.addItemDecoration(new SimpleItemDecoration(gridLayoutManager));
        mHackAdapter = new HackSequencesAdapter();
        mHackAdapter.setHackSequences(BaseGlyphData.getInstance().getFiveSequences());
        gridView.setAdapter(mHackAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof HackSequenceListener) {
            mSequenceListener = (HackSequenceListener) activity;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mHackAdapter.setHackListener(mSequenceListener);
    }
}
