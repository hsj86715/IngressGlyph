package com.hsj86715.ingress.glyph.view;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * @author hushujun
 */
public class IntegerAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int integer = Math.round(value);
        if (integer != value) {
            return "";
        } else {
            return String.valueOf(integer);
        }
    }
}
