package com.hsj86715.ingress.glyph.pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.model.GradientColor;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.IndexAxisValueFormatter;
import com.hsj86715.ingress.glyph.view.IntegerAxisValueFormatter;
import com.hsj86715.ingress.glyph.view.XYMarkerView;
import com.hsj86715.ingress.glyphres.data.Constants;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;

import java.util.ArrayList;
import java.util.List;

public class StatisGlyphLearnFragment extends Fragment {
    private BarChart mBarChart;
    private IndexAxisValueFormatter<String> xAxisFormatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_statis_glyph_learn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBarChart = view.findViewById(R.id.statis_barChart);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setMaxVisibleValueCount(20);
        mBarChart.setScaleYEnabled(false);
        mBarChart.setDrawGridBackground(false);

        xAxisFormatter = new IndexAxisValueFormatter<>();

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter yAxisFormatter = new IntegerAxisValueFormatter();

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(yAxisFormatter);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(5f);
        leftAxis.setSpaceBottom(0);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(yAxisFormatter);
        rightAxis.setSpaceTop(5f);
        rightAxis.setSpaceBottom(0);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(10f);
        l.setTextSize(12f);
        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(getContext(), xAxisFormatter, yAxisFormatter);
        mv.setChartView(mBarChart); // For bounds control
        mBarChart.setMarker(mv); // Set the marker to the chart

        new BaseGlyphTask().execute();
    }

    private void generateGlyphLearn(List<GlyphInfo> glyphInfos) {
        if (glyphInfos == null || glyphInfos.isEmpty()) {
            mBarChart.setData(null);
            return;
        }

        BarDataSet dataSet;
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for (int i = 0; i < glyphInfos.size(); i++) {
            GlyphInfo glyphInfo = glyphInfos.get(i);
            yVals.add(new BarEntry(i, glyphInfo.getLearnCount()));
            xAxisFormatter.add(glyphInfo.getName());
        }
        dataSet = new BarDataSet(yVals, getString(R.string.option_menu_base_learn));
        dataSet.setDrawIcons(false);

        dataSet.setGradientColors(initChartBarGradientColor());
        BarData barData = new BarData(dataSet);
        barData.setValueTextSize(10f);
        barData.setBarWidth(0.9f);

        mBarChart.setData(barData);
        mBarChart.animateXY(Constants.X_ANIMATE_DURATION, Constants.Y_ANIMATE_DURATION);
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
            generateGlyphLearn(glyphInfos);
        }
    }
}
