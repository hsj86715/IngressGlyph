import '../../bar_chart/bar_chart_painter.dart';
import '../../line_chart/line_chart_painter.dart';
import '../../pie_chart/pie_chart_painter.dart';
import 'package:flutter/material.dart';

import 'base_chart_data.dart';
import 'touch_input.dart';

/// this class is base class of our painters and
/// it is responsible to draw borders of charts.
/// concrete samples :
/// [LineChartPainter], [BarChartPainter], [PieChartPainter]
/// there is a data [D] that extends from [BaseChartData],
/// that contains needed data to draw chart border in this phase.
/// [data] is the currently showing data (it may produced by an animation using lerp function),
/// [targetData] is the target data, that animation is going to show (if animating)
abstract class BaseChartPainter<D extends BaseChartData> extends CustomPainter {
  final D data;
  final D targetData;
  Paint borderPaint;

  BaseChartPainter(this.data, this.targetData)
    : super() {
    borderPaint = Paint()
      ..style = PaintingStyle.stroke;
  }

  @override
  void paint(Canvas canvas, Size size) {
    drawViewBorder(canvas, size);
  }

  void drawViewBorder(Canvas canvas, Size viewSize) {
    if (!data.borderData.show) {
      return;
    }

    final chartViewSize = getChartUsableDrawSize(viewSize);

    final topLeft = Offset(getLeftOffsetDrawSize(), getTopOffsetDrawSize());
    final topRight = Offset(
        getLeftOffsetDrawSize() + chartViewSize.width, getTopOffsetDrawSize());
    final bottomLeft = Offset(
        getLeftOffsetDrawSize(), getTopOffsetDrawSize() + chartViewSize.height);
    final bottomRight = Offset(getLeftOffsetDrawSize() + chartViewSize.width,
        getTopOffsetDrawSize() + chartViewSize.height);

    /// Draw Top Line
    borderPaint.color = data.borderData.border.top.color;
    borderPaint.strokeWidth = data.borderData.border.top.width;
    canvas.drawLine(topLeft, topRight, borderPaint);

    /// Draw Right Line
    borderPaint.color = data.borderData.border.right.color;
    borderPaint.strokeWidth = data.borderData.border.right.width;
    canvas.drawLine(topRight, bottomRight, borderPaint);

    /// Draw Bottom Line
    borderPaint.color = data.borderData.border.bottom.color;
    borderPaint.strokeWidth = data.borderData.border.bottom.width;
    canvas.drawLine(bottomRight, bottomLeft, borderPaint);

    /// Draw Left Line
    borderPaint.color = data.borderData.border.left.color;
    borderPaint.strokeWidth = data.borderData.border.left.width;
    canvas.drawLine(bottomLeft, topLeft, borderPaint);
  }

  /// calculate the size that we can draw our chart.
  /// [getExtraNeededHorizontalSpace] and [getExtraNeededVerticalSpace]
  /// is the needed space to draw horizontal and vertical
  /// stuff around our chart.
  /// then we subtract them from raw [viewSize]
  Size getChartUsableDrawSize(Size viewSize) {
    final usableWidth = viewSize.width - getExtraNeededHorizontalSpace();
    final usableHeight = viewSize.height - getExtraNeededVerticalSpace();
    return Size(usableWidth, usableHeight);
  }

  /// extra needed space to show horizontal contents around the chart,
  /// like: left, right padding, left, right titles, and so on,
  /// each child class can override this function.
  double getExtraNeededHorizontalSpace() => 0;

  /// extra needed space to show vertical contents around the chart,
  /// like: tob, bottom padding, top, bottom titles, and so on,
  /// each child class can override this function.
  double getExtraNeededVerticalSpace() => 0;

  /// left offset to draw the chart
  /// we should use this to offset our x axis when we drawing the chart,
  /// and the width space we can use to draw chart is[getChartUsableDrawSize.width]
  double getLeftOffsetDrawSize() => 0;

  /// top offset to draw the chart
  /// we should use this to offset our y axis when we drawing the chart,
  /// and the height space we can use to draw chart is[getChartUsableDrawSize.height]
  double getTopOffsetDrawSize() => 0;
}

mixin TouchHandler<T extends BaseTouchResponse> {
  T handleTouch(FlTouchInput touchInput, Size size,) =>
    throw UnsupportedError('not implemented');
}