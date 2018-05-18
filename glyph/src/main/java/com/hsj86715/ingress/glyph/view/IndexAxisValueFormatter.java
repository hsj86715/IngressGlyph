package com.hsj86715.ingress.glyph.view;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hushujun
 * @param <T>
 */
public class IndexAxisValueFormatter<T> implements IAxisValueFormatter {
    private List<T> mValues = new ArrayList<>();

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = Math.round(value);

        if (index < 0 || index >= mValues.size() || index != (int) value) {
            return "";
        }
        return mValues.get(index).toString();
    }

    public void add(T t) {
        mValues.add(t);
    }

    public void add(int index, T t) {
        mValues.add(index, t);
    }
}
