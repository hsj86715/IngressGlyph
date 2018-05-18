package com.hsj86715.ingress.glyph.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.hsj86715.ingress.glyph.R;

/**
 * @author hushujun
 */
public class XYMarkerView extends MarkerView {
    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;
    private IAxisValueFormatter yAxisValueFormatter;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public XYMarkerView(Context context, IAxisValueFormatter xFormatter, IAxisValueFormatter yFormatter) {
        super(context, R.layout.xy_marker_view);
        this.xAxisValueFormatter = xFormatter;
        this.yAxisValueFormatter = yFormatter;
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String yValue = yAxisValueFormatter == null ? String.valueOf(e.getY()) : yAxisValueFormatter.getFormattedValue(e.getY(), null);
        tvContent.setText(xAxisValueFormatter.getFormattedValue(e.getX(), null) + ", " + yValue);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
