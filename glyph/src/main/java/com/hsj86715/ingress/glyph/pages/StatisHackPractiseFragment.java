package com.hsj86715.ingress.glyph.pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CombinedChart;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.IndexAxisValueFormatter;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;

import java.util.List;

public class StatisHackPractiseFragment extends Fragment {
    private CombinedChart mCombinedChart;
    private IndexAxisValueFormatter<String> xAxisFormatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_statis_hack_practise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCombinedChart = view.findViewById(R.id.statis_combinedChart);

    }

    private class HackListTask extends AsyncTask<Void, Void, List<HackList>> {
        @Override
        protected List<HackList> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getHackList(0);
        }

        @Override
        protected void onPostExecute(List<HackList> hackLists) {
//            mHackLists = hackLists;
//            if (mStatisType == HACK_PRACTISE) {
//                generateHackPractise(mHackLists);
//            } else if (mStatisType == HACK_PRACTISE_COST) {
//                generateHackPractiseCost(mHackLists);
//            } else if (mStatisType == HACK_LENGTH) {
//                generateHackLength(mHackLists);
//            }
        }
    }
}
