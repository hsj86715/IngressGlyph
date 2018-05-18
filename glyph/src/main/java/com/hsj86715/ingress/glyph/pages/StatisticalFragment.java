package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.model.GradientColor;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.IndexAxisValueFormatter;
import com.hsj86715.ingress.glyph.view.IntegerAxisValueFormatter;
import com.hsj86715.ingress.glyph.view.XYMarkerView;
import com.hsj86715.ingress.glyphres.data.Constants;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;

import java.util.ArrayList;
import java.util.List;

import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.GLYPH_LEARN;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.GLYPH_PRACTISE;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_LENGTH;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_PRACTISE;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_PRACTISE_COST;

/**
 * @author hushujun
 */
public class StatisticalFragment extends Fragment {
    @IntDef({GLYPH_LEARN, GLYPH_PRACTISE, HACK_PRACTISE, HACK_PRACTISE_COST, HACK_LENGTH})
    @interface StatisType {
        int GLYPH_LEARN = 0, GLYPH_PRACTISE = 1, HACK_PRACTISE = 2, HACK_PRACTISE_COST = 3, HACK_LENGTH = 4;
    }

    private static final int X_ANIMATE_DURATION = 250;
    private static final int Y_ANIMATE_DURATION = X_ANIMATE_DURATION * 5;

    private ViewGroup mContainer;
    private Chart mMPChart;

    private List<GlyphInfo> mGlyphInfos;
    private List<HackList> mHackLists;
    private @StatisType
    int mStatisType = GLYPH_LEARN;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_statistical, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContainer = view.findViewById(R.id.statistical_container);
        new BaseGlyphTask().execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionStatistical");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        switch (id) {
            case R.id.statistical_glyph_learn:
                mStatisType = GLYPH_LEARN;
                if (mGlyphInfos == null || mGlyphInfos.isEmpty()) {
                    new BaseGlyphTask().execute();
                } else {
                    generateGlyphLearn(mGlyphInfos);
                }
                break;
            case R.id.statistical_glyph_practise:
                mStatisType = GLYPH_PRACTISE;
                if (mGlyphInfos == null || mGlyphInfos.isEmpty()) {
                    new BaseGlyphTask().execute();
                } else {
                    generateGlyphPractise(mGlyphInfos);
                }
                break;
            case R.id.statistical_hack_practise:
                mStatisType = HACK_PRACTISE;
                if (mHackLists == null || mHackLists.isEmpty()) {
                    new HackListTask().execute();
                } else {
                    generateHackPractise(mHackLists);
                }
                break;
            case R.id.statistical_hack_practise_cost:
                mStatisType = HACK_PRACTISE_COST;
                if (mHackLists == null || mHackLists.isEmpty()) {
                    new HackListTask().execute();
                } else {
                    generateHackPractiseCost(mHackLists);
                }
                break;
            case R.id.statistical_hack_length:
                mStatisType = HACK_LENGTH;
                if (mHackLists == null || mHackLists.isEmpty()) {
                    new HackListTask().execute();
                } else {
                    generateHackLength(mHackLists);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateGlyphLearn(List<GlyphInfo> glyphInfos) {
        BarChart barChart = new BarChart(getContext());
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(20);
        barChart.setScaleYEnabled(false);
        barChart.setDrawGridBackground(false);

        mContainer.removeAllViews();
        mContainer.addView(barChart, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mMPChart = barChart;

        if (glyphInfos == null || glyphInfos.isEmpty()) {
            barChart.setData(null);
            return;
        }

        IAxisValueFormatter xAxisFormatter = initChartXAxis(barChart, false);
        IAxisValueFormatter yAxisFormatter = initChartYAxis(barChart);

        initChartLegend(barChart);

        XYMarkerView mv = new XYMarkerView(getContext(), xAxisFormatter, yAxisFormatter);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart

        BarDataSet dataSet;
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for (int i = 0; i < glyphInfos.size(); i++) {
            GlyphInfo glyphInfo = glyphInfos.get(i);
            yVals.add(new BarEntry(i, glyphInfo.getLearnCount()));
            ((IndexAxisValueFormatter) xAxisFormatter).add(glyphInfo.getName());
        }
        dataSet = new BarDataSet(yVals, getString(R.string.option_menu_base_learn));
        dataSet.setDrawIcons(false);

        dataSet.setGradientColors(initChartBarGradientColor());
        BarData barData = new BarData(dataSet);
        barData.setValueTextSize(10f);
        barData.setBarWidth(0.9f);

        barChart.setData(barData);
        barChart.animateXY(X_ANIMATE_DURATION, Y_ANIMATE_DURATION);
    }

    private void generateGlyphPractise(List<GlyphInfo> glyphInfos) {
        CombinedChart comChart = new CombinedChart(getContext());
        comChart.getDescription().setEnabled(false);
        comChart.setDrawGridBackground(false);
        comChart.setDrawBarShadow(false);
        comChart.setScaleYEnabled(false);
        comChart.setHighlightFullBarEnabled(false);
        comChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE});

        mContainer.removeAllViews();
        mContainer.addView(comChart, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mMPChart = comChart;

        if (glyphInfos == null || glyphInfos.isEmpty()) {
            comChart.setData(null);
            return;
        }

        IAxisValueFormatter xAxisFormatter = initChartXAxis(comChart, true);
        IAxisValueFormatter yAxisFormatter = initChartYAxis(comChart);

        initChartLegend(comChart);

        XYMarkerView mv = new XYMarkerView(getContext(), xAxisFormatter, yAxisFormatter);
        mv.setChartView(comChart); // For bounds control
        comChart.setMarker(mv); // Set the marker to the chart

        ArrayList<BarEntry> yVals = new ArrayList<>();
        ArrayList<Entry> lineVals = new ArrayList<Entry>();
        for (int i = 0; i < glyphInfos.size(); i++) {
            GlyphInfo glyphInfo = glyphInfos.get(i);
            yVals.add(new BarEntry(i + 0.5f, glyphInfo.getPractiseCount()));
            lineVals.add(new Entry(i + 0.5f, glyphInfo.getPractiseCorrect()));
            ((IndexAxisValueFormatter) xAxisFormatter).add(glyphInfo.getName());
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
        comChart.setData(data);
        comChart.animateXY(X_ANIMATE_DURATION, Y_ANIMATE_DURATION);
    }

    private void generateHackPractise(List<HackList> hackLists) {
//        ComboLineColumnChartView chartView = new ComboLineColumnChartView(getContext());
//        chartView.setZoomType(ZoomType.HORIZONTAL);
//
//        mContainer.removeAllViews();
//        mContainer.addView(chartView);
//        mChartView = chartView;
//        if (hackLists == null || hackLists.isEmpty()) {
//            chartView.setComboLineColumnChartData(null);
//            return;
//        }
//
//        Map<String, List<HackList>> hackListMap = GlyphModel.getInstance(getContext()).getHackList(hackLists);
//        Set<String> keySet = hackListMap.keySet();
//        Iterator<String> iterator = keySet.iterator();
//
//        List<Column> columns = new ArrayList<>();
//        List axisXValues = new ArrayList();
//
//        List<Line> lines = new ArrayList<>();
//        List<PointValue> pointValues = new ArrayList<>();
//        int index = 0;
//        Random random = new Random();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            axisXValues.add(new AxisValue(index).setLabel(key));
//            List<HackList> subHackList = hackListMap.get(key);
//            List<SubcolumnValue> columnValues = new ArrayList<>();
//            for (int i = 0; i < subHackList.size(); i++) {
//                HackList hackList = subHackList.get(i);
//                columnValues.add(new SubcolumnValue(random.nextInt(20), ChartUtils.pickColor()));
//
//                pointValues.add(new PointValue(index, random.nextInt(10)));
//                index++;
//            }
//            Column column = new Column(columnValues);
//            column.setHasLabelsOnlyForSelected(false);
//            columns.add(column);
//            index++;
//        }
//        ColumnChartData columnData = new ColumnChartData(columns);
//
//        Line line = new Line(pointValues);
//        line.setColor(ChartUtils.COLORS[0]);
//        line.setCubic(false);
//        line.setHasLabels(false);
//        line.setHasLines(true);
//        line.setHasPoints(true);
//        lines.add(line);
//        LineChartData lineData = new LineChartData(lines);
//
//        ComboLineColumnChartData chartData = new ComboLineColumnChartData(columnData, lineData);
//        chartData.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK)
//                .setName("Head").setHasTiltedLabels(true).setMaxLabelChars(8));
//        chartData.setAxisYLeft(new Axis().setHasLines(true).setName("Practise Count").setTextColor(Color.BLACK)
//                .setMaxLabelChars(5));
//        chartView.setComboLineColumnChartData(chartData);
    }

    private void generateHackPractiseCost(List<HackList> hackLists) {
        // TODO: 2018/5/13 next version complete
    }

    private void generateHackLength(List<HackList> hackLists) {
//        PieChartView chartView = new PieChartView(getContext());
//        chartView.setZoomType(ZoomType.HORIZONTAL);
//
//        mContainer.removeAllViews();
//        mContainer.addView(chartView);
//        mChartView = chartView;
//        if (hackLists == null || hackLists.isEmpty()) {
//            chartView.setPieChartData(null);
//            return;
//        }
//        int[] count = new int[4];
//        final int index_2 = 0, index_3 = 1, index_4 = 2, index_5 = 3;
//        for (HackList hackList : hackLists) {
//            if (hackList.getLength() == 2) {
//                count[index_2] += hackList.getPractiseCount();
//            } else if (hackList.getLength() == 3) {
//                count[index_3] += hackList.getPractiseCount();
//            } else if (hackList.getLength() == 4) {
//                count[index_4] += hackList.getPractiseCount();
//            } else if (hackList.getLength() == 5) {
//                count[index_5] += hackList.getPractiseCount();
//            }
//        }
//        List<SliceValue> values = new ArrayList<>();
//        for (int i = 0; i < count.length; i++) {
//            SliceValue sliceValue = new SliceValue(count[i], ChartUtils.pickColor());
//            values.add(sliceValue);
//        }
//        PieChartData data = new PieChartData(values);
//        data.setHasLabels(true);
//        data.setHasLabelsOnlyForSelected(false);
//        data.setHasLabelsOutside(false);
//        data.setHasCenterCircle(false);
//        chartView.setPieChartData(data);
    }

    private IAxisValueFormatter initChartXAxis(BarLineChartBase barChart, boolean miniZero) {
        IAxisValueFormatter xAxisFormatter = new IndexAxisValueFormatter<String>();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        if (miniZero) {
            xAxis.setAxisMinimum(0f);
        }
        xAxis.setValueFormatter(xAxisFormatter);
        return xAxisFormatter;
    }

    private IAxisValueFormatter initChartYAxis(BarLineChartBase barChart) {
        IAxisValueFormatter yAxisFormatter = new IntegerAxisValueFormatter();

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(yAxisFormatter);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(5f);
        leftAxis.setSpaceBottom(0);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(yAxisFormatter);
        rightAxis.setSpaceTop(5f);
        rightAxis.setSpaceBottom(0);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        return yAxisFormatter;
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

    private void initChartLegend(Chart chart) {
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(10f);
        l.setTextSize(12f);
        l.setXEntrySpace(4f);
    }

    private class BaseGlyphTask extends AsyncTask<Void, Void, List<GlyphInfo>> {
        @Override
        protected List<GlyphInfo> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getGlyphInfoByCategory(Constants.C_ALL);
        }

        @Override
        protected void onPostExecute(List<GlyphInfo> glyphInfos) {
            mGlyphInfos = glyphInfos;
            if (mStatisType == GLYPH_LEARN) {
                generateGlyphLearn(mGlyphInfos);
            } else if (mStatisType == GLYPH_PRACTISE) {
                generateGlyphPractise(mGlyphInfos);
            }
        }
    }

    private class HackListTask extends AsyncTask<Void, Void, List<HackList>> {
        @Override
        protected List<HackList> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getHackList(0);
        }

        @Override
        protected void onPostExecute(List<HackList> hackLists) {
            mHackLists = hackLists;
            if (mStatisType == HACK_PRACTISE) {
                generateHackPractise(mHackLists);
            } else if (mStatisType == HACK_PRACTISE_COST) {
                generateHackPractiseCost(mHackLists);
            } else if (mStatisType == HACK_LENGTH) {
                generateHackLength(mHackLists);
            }
        }
    }
}
