package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.IndexAxisValueFormatter;
import com.hsj86715.ingress.glyphres.data.Constants;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;

import java.util.ArrayList;
import java.util.List;

public class StatisGlyphPractiseFragment extends Fragment {
    private CombinedChart mCombinedChart;
    private IndexAxisValueFormatter<String> xAxisFormatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_statis_glyph_practise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCombinedChart = view.findViewById(R.id.statis_combinedChart);

        mCombinedChart.getDescription().setEnabled(false);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        mCombinedChart.setDrawOrder(new DrawOrder[]{DrawOrder.BAR, DrawOrder.LINE});

        Legend l = mCombinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = mCombinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mCombinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        xAxisFormatter = new IndexAxisValueFormatter<>();
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        new BaseGlyphTask().execute();
    }

    private void generateGlyphPractise(List<GlyphInfo> glyphInfos) {
        if (glyphInfos == null || glyphInfos.isEmpty()) {
            mCombinedChart.setData(null);
            return;
        }

        ArrayList<BarEntry> yVals = new ArrayList<>();
        ArrayList<Entry> lineVals = new ArrayList<Entry>();
        for (int i = 0; i < glyphInfos.size(); i++) {
            GlyphInfo glyphInfo = glyphInfos.get(i);
            yVals.add(new BarEntry(i + 0.5f, glyphInfo.getPractiseCount()));
            lineVals.add(new Entry(i + 0.5f, glyphInfo.getPractiseCorrect()));
            xAxisFormatter.add(glyphInfo.getName());
        }
        BarDataSet barDataSet = new BarDataSet(yVals, getString(R.string.option_menu_base_learn));
        barDataSet.setDrawIcons(false);

        barDataSet.setGradientColors(initChartBarGradientColor());
        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(10f);
        barData.setBarWidth(0.9f);

        LineData lineData = new LineData();
        LineDataSet set = new LineDataSet(lineVals, "Practise Correct");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(set);

        CombinedData data = new CombinedData();
        data.setData(lineData);
        data.setData(barData);
        mCombinedChart.setData(data);
        mCombinedChart.animateXY(Constants.X_ANIMATE_DURATION, Constants.Y_ANIMATE_DURATION);
    }

    private List<GradientColor> initChartBarGradientColor() {
        int[] colorRes = new int[]{android.R.color.holo_blue_bright, android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark, android.R.color.holo_green_light, android.R.color.holo_green_dark,
                android.R.color.holo_orange_light, android.R.color.holo_orange_dark, android.R.color.holo_purple,
                android.R.color.holo_red_light, android.R.color.holo_red_dark};
        List<GradientColor> gradientColors = new ArrayList<>();
        int startIdx;
        int endIdx;
        for (int i = 0; i < colorRes.length; i++) {
            startIdx = i;
            endIdx = startIdx + 1;
            if (endIdx == colorRes.length) {
                endIdx = 0;
            }
            gradientColors.add(new GradientColor(ContextCompat.getColor(getContext(), colorRes[startIdx]),
                    ContextCompat.getColor(getContext(), colorRes[endIdx])));
        }
        return gradientColors;
    }

    private class BaseGlyphTask extends AsyncTask<Void, Void, List<GlyphInfo>> {
        @Override
        protected List<GlyphInfo> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getGlyphInfoByCategory(Constants.C_ALL);
        }

        @Override
        protected void onPostExecute(List<GlyphInfo> glyphInfos) {
            generateGlyphPractise(glyphInfos);
        }
    }
}
