package com.hsj86715.ingress.glyph.pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;

import java.util.List;

public class StatisHackCostFragment extends Fragment {
    private BarChart mBarChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_statis_hack_cost, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBarChart = view.findViewById(R.id.statis_barChart);

        mBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBarChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);

        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBarShadow(false);

        mBarChart.setDrawValueAboveBar(false);
        mBarChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mBarChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);


        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
    }

    private void generateHackPractiseCost(List<HackList> hackLists) {

    }

    private class HackListTask extends AsyncTask<Integer, Void, List<HackList>> {
        @Override
        protected List<HackList> doInBackground(Integer... integers) {
            return GlyphModel.getInstance(getActivity()).getHackList(integers[0]);
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
