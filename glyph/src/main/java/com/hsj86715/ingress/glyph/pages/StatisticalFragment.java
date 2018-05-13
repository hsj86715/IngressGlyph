package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.data.Constants;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.AbstractChartView;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.GLYPH_LEARN;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.GLYPH_PRACTISE;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_LENGTH;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_PRACTISE;
import static com.hsj86715.ingress.glyph.pages.StatisticalFragment.StatisType.HACK_PRACTISE_COST;

/**
 * @author hushujun
 */
public class StatisticalFragment extends Fragment implements ViewportChangeListener {
    @IntDef({GLYPH_LEARN, GLYPH_PRACTISE, HACK_PRACTISE, HACK_PRACTISE_COST, HACK_LENGTH})
    @interface StatisType {
        int GLYPH_LEARN = 0, GLYPH_PRACTISE = 1, HACK_PRACTISE = 2, HACK_PRACTISE_COST = 3, HACK_LENGTH = 4;
    }

    private ViewGroup mContainer;
    private AbstractChartView mChartView;

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
//        mChartView = new ColumnChartView(view.getContext());
//        mContainer.addView(mChartView);
//
//        mChartView.setZoomType(ZoomType.HORIZONTAL);
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
                    if (mChartView != null && mChartView instanceof ColumnChartView) {
                        return true;
                    } else {
                        generateGlyphLearn(mGlyphInfos);
                    }
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
                    if (mChartView != null && mChartView instanceof PieChartView) {
                        return true;
                    } else {
                        generateHackLength(mHackLists);
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateGlyphLearn(List<GlyphInfo> glyphInfos) {
        ColumnChartView chartView = new ColumnChartView(getContext());
        chartView.setZoomType(ZoomType.HORIZONTAL);

        mContainer.removeAllViews();
        mContainer.addView(chartView);
        mChartView = chartView;

        if (glyphInfos == null || glyphInfos.isEmpty()) {
            chartView.setColumnChartData(null);
            return;
        }
        int numSubcolumns = 1;
        int numColumns = glyphInfos.size();
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        List axisXValues = new ArrayList();

        for (int i = 0; i < numColumns; i++) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; j++) {
                GlyphInfo glyphInfo = glyphInfos.get(i);
                values.add(new SubcolumnValue(glyphInfo.getLearnCount(), ChartUtils.pickColor()));
                axisXValues.add(new AxisValue(i).setLabel(glyphInfo.getName()));
            }
            Column column = new Column(values);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK)
                .setName("Name").setHasTiltedLabels(true).setMaxLabelChars(8));
        data.setAxisYLeft(new Axis().setHasLines(true).setName("Learn Count").setTextColor(Color.BLACK)
                .setMaxLabelChars(5));
        chartView.setColumnChartData(data);
    }

    private void generateGlyphPractise(List<GlyphInfo> glyphInfos) {
        ComboLineColumnChartView chartView = new ComboLineColumnChartView(getContext());
        chartView.setZoomType(ZoomType.HORIZONTAL);

        mContainer.removeAllViews();
        mContainer.addView(chartView);
        mChartView = chartView;

        if (glyphInfos == null || glyphInfos.isEmpty()) {
            chartView.setComboLineColumnChartData(null);
            return;
        }

        int numSubcolumns = 1;
        int numColumns = glyphInfos.size();
        List<Column> columns = new ArrayList<>();
        List axisXValues = new ArrayList();

        List<Line> lines = new ArrayList<>();
        List<PointValue> pointValues = new ArrayList<>();

        for (int i = 0; i < numColumns; i++) {
            List<SubcolumnValue> columnValues = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; j++) {
                GlyphInfo glyphInfo = glyphInfos.get(i);
                columnValues.add(new SubcolumnValue(glyphInfo.getPractiseCount(), ChartUtils.pickColor()));
                axisXValues.add(new AxisValue(i).setLabel(glyphInfo.getName()));

                pointValues.add(new PointValue(i, glyphInfo.getPractiseCorrect()));
            }
            Column column = new Column(columnValues);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }
        ColumnChartData columnData = new ColumnChartData(columns);

        Line line = new Line(pointValues);
        line.setColor(ChartUtils.COLORS[0]);
        line.setCubic(false);
        line.setHasLabels(false);
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);
        LineChartData lineData = new LineChartData(lines);

        ComboLineColumnChartData chartData = new ComboLineColumnChartData(columnData, lineData);
        chartData.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK)
                .setName("Name").setHasTiltedLabels(true).setMaxLabelChars(8));
        chartData.setAxisYLeft(new Axis().setHasLines(true).setName("Practise Count").setTextColor(Color.BLACK)
                .setMaxLabelChars(5));
        chartView.setComboLineColumnChartData(chartData);
    }

    private void generateHackPractise(List<HackList> hackLists) {
        ComboLineColumnChartView chartView = new ComboLineColumnChartView(getContext());
        chartView.setZoomType(ZoomType.HORIZONTAL);

        mContainer.removeAllViews();
        mContainer.addView(chartView);
        mChartView = chartView;
        if (hackLists == null || hackLists.isEmpty()) {
            chartView.setComboLineColumnChartData(null);
            return;
        }

        Map<String, List<HackList>> hackListMap = GlyphModel.getInstance(getContext()).getHackList(hackLists);
        Set<String> keySet = hackListMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<Column> columns = new ArrayList<>();
        List axisXValues = new ArrayList();

        List<Line> lines = new ArrayList<>();
        List<PointValue> pointValues = new ArrayList<>();
        int index = 0;
        Random random = new Random();
        while (iterator.hasNext()) {
            String key = iterator.next();
            axisXValues.add(new AxisValue(index).setLabel(key));
            List<HackList> subHackList = hackListMap.get(key);
            List<SubcolumnValue> columnValues = new ArrayList<>();
            for (int i = 0; i < subHackList.size(); i++) {
                HackList hackList = subHackList.get(i);
                columnValues.add(new SubcolumnValue(random.nextInt(20), ChartUtils.pickColor()));

                pointValues.add(new PointValue(index, random.nextInt(10)));
                index++;
            }
            Column column = new Column(columnValues);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            index++;
        }
        ColumnChartData columnData = new ColumnChartData(columns);

        Line line = new Line(pointValues);
        line.setColor(ChartUtils.COLORS[0]);
        line.setCubic(false);
        line.setHasLabels(false);
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);
        LineChartData lineData = new LineChartData(lines);

        ComboLineColumnChartData chartData = new ComboLineColumnChartData(columnData, lineData);
        chartData.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK)
                .setName("Head").setHasTiltedLabels(true).setMaxLabelChars(8));
        chartData.setAxisYLeft(new Axis().setHasLines(true).setName("Practise Count").setTextColor(Color.BLACK)
                .setMaxLabelChars(5));
        chartView.setComboLineColumnChartData(chartData);
    }

    private void generateHackPractiseCost(List<HackList> hackLists) {
        // TODO: 2018/5/13 next version complete
    }

    private void generateHackLength(List<HackList> hackLists) {
        PieChartView chartView = new PieChartView(getContext());
        chartView.setZoomType(ZoomType.HORIZONTAL);

        mContainer.removeAllViews();
        mContainer.addView(chartView);
        mChartView = chartView;
        if (hackLists == null || hackLists.isEmpty()) {
            chartView.setPieChartData(null);
            return;
        }
        int[] count = new int[4];
        final int index_2 = 0, index_3 = 1, index_4 = 2, index_5 = 3;
        for (HackList hackList : hackLists) {
            if (hackList.getLength() == 2) {
                count[index_2] += hackList.getPractiseCount();
            } else if (hackList.getLength() == 3) {
                count[index_3] += hackList.getPractiseCount();
            } else if (hackList.getLength() == 4) {
                count[index_4] += hackList.getPractiseCount();
            } else if (hackList.getLength() == 5) {
                count[index_5] += hackList.getPractiseCount();
            }
        }
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            SliceValue sliceValue = new SliceValue(count[i], ChartUtils.pickColor());
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(false);
        data.setHasCenterCircle(false);
        chartView.setPieChartData(data);
    }

    @Override
    public void onViewportChanged(Viewport viewport) {
        mChartView.setCurrentViewport(viewport);
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
