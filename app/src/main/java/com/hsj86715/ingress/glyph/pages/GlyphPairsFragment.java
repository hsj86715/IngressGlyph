package com.hsj86715.ingress.glyph.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.HackSequenceListener;

/**
 * Created by hushujun on 2017/4/18.
 */

public class GlyphPairsFragment extends Fragment {

    private HackSequenceListener mSequenceListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_pairs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
