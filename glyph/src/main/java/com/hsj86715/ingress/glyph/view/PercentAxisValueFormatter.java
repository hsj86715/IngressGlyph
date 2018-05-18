package com.hsj86715.ingress.glyph.view;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * @author hushujun
 */
public class PercentAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value > 1) {
            return "100 %";
        }
        if (value < 0) {
            return "0 %";
        }
        return Math.round(value * 100) + " %";
    }
}
