import '../../../fl_chart.dart';
import '../base/axis_chart/axis_chart_painter.dart';
import '../base/base_chart/base_chart_painter.dart';
import '../base/base_chart/touch_input.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

import 'scatter_chart_data.dart';

class ScatterChartPainter extends AxisChartPainter<ScatterChartData> with TouchHandler<ScatterTouchResponse> {

  /// [spotsPaint] is responsible to draw scatter spots
  Paint spotsPaint, bgTouchTooltipPaint;

  ScatterChartPainter(
    ScatterChartData data,
    ScatterChartData targetData,
    Function(TouchHandler) touchHandler,
    ) : super(data, targetData,) {
    touchHandler(this);

    spotsPaint = Paint()..style = PaintingStyle.fill;

    bgTouchTooltipPaint = Paint()
      ..style = PaintingStyle.fill
      ..color = Colors.white;

  }

  @override
  void paint(Canvas canvas, Size size) {
    super.paint(canvas, size);

    drawTitles(canvas, size);
    drawSpots(canvas, size);

    for(int i = 0; i < targetData.scatterSpots.length; i++) {
      if (!targetData.showingTooltipIndicators.contains(i)) {
        continue;
      }

      final ScatterSpot scatterSpot = targetData.scatterSpots[i];
      drawTouchTooltip(canvas, size, targetData.scatterTouchData.touchTooltipData, scatterSpot);
    }
  }

  void drawTitles(Canvas canvas, Size viewSize) {
    if (!data.titlesData.show) {
      return;
    }
    viewSize = getChartUsableDrawSize(viewSize);

    // Left Titles
    final leftTitles = data.titlesData.leftTitles;
    if (leftTitles.showTitles) {
      double verticalSeek = data.minY;
      while (verticalSeek <= data.maxY) {
        double x = 0 + getLeftOffsetDrawSize();
        double y = getPixelY(verticalSeek, viewSize);

        final String text = leftTitles.getTitles(verticalSeek);

        final TextSpan span = TextSpan(style: leftTitles.textStyle, text: text);
        final TextPainter tp =
        TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
        tp.layout(maxWidth: getExtraNeededHorizontalSpace());
        x -= tp.width + leftTitles.margin;
        y -= tp.height / 2;
        tp.paint(canvas, Offset(x, y));

        verticalSeek += leftTitles.interval;
      }
    }

    // Top titles
    final topTitles = data.titlesData.topTitles;
    if (topTitles.showTitles) {
      double horizontalSeek = data.minX;
      while (horizontalSeek <= data.maxX) {
        double x = getPixelX(horizontalSeek, viewSize);
        double y = getTopOffsetDrawSize();

        final String text = topTitles.getTitles(horizontalSeek);

        final TextSpan span = TextSpan(style: topTitles.textStyle, text: text);
        final TextPainter tp =
        TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
        tp.layout();

        x -= tp.width / 2;
        y -= topTitles.margin + tp.height;

        tp.paint(canvas, Offset(x, y));

        horizontalSeek += topTitles.interval;
      }
    }

    // Right Titles
    final rightTitles = data.titlesData.rightTitles;
    if (rightTitles.showTitles) {
      double verticalSeek = data.minY;
      while (verticalSeek <= data.maxY) {
        double x = viewSize.width + getLeftOffsetDrawSize();
        double y = getPixelY(verticalSeek, viewSize);

        final String text = rightTitles.getTitles(verticalSeek);

        final TextSpan span = TextSpan(style: rightTitles.textStyle, text: text);
        final TextPainter tp =
        TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
        tp.layout(maxWidth: getExtraNeededHorizontalSpace());
        x += rightTitles.margin;
        y -= tp.height / 2;
        tp.paint(canvas, Offset(x, y));

        verticalSeek += rightTitles.interval;
      }
    }

    // Bottom titles
    final bottomTitles = data.titlesData.bottomTitles;
    if (bottomTitles.showTitles) {
      double horizontalSeek = data.minX;
      while (horizontalSeek <= data.maxX) {
        double x = getPixelX(horizontalSeek, viewSize);
        double y = viewSize.height + getTopOffsetDrawSize();

        final String text = bottomTitles.getTitles(horizontalSeek);

        final TextSpan span = TextSpan(style: bottomTitles.textStyle, text: text);
        final TextPainter tp =
        TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
        tp.layout();

        x -= tp.width / 2;
        y += bottomTitles.margin;

        tp.paint(canvas, Offset(x, y));

        horizontalSeek += bottomTitles.interval;
      }
    }
  }

  void drawSpots(Canvas canvas, Size viewSize) {
    if (data.scatterSpots == null) {
      return;
    }
    final chartUsableSize = getChartUsableDrawSize(viewSize);
    for (final ScatterSpot scatterSpot in data.scatterSpots) {
      final double pixelX = getPixelX(scatterSpot.x, chartUsableSize);
      final double pixelY = getPixelY(scatterSpot.y, chartUsableSize);

      spotsPaint.color = scatterSpot.color;

      canvas.drawCircle(
        Offset(pixelX, pixelY),
        scatterSpot.radius,
        spotsPaint,
      );
    }
  }

  void drawTouchTooltip(Canvas canvas, Size viewSize, ScatterTouchTooltipData tooltipData,
    ScatterSpot showOnSpot) {

    final Size chartUsableSize = getChartUsableDrawSize(viewSize);

    final ScatterTooltipItem tooltipItem = tooltipData.getTooltipItems(showOnSpot);

    if (tooltipItem == null) {
      return;
    }

    final TextSpan span = TextSpan(style: tooltipItem.textStyle, text: tooltipItem.text);
    final TextPainter drawingTextPainter =
    TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
    drawingTextPainter.layout(maxWidth: tooltipData.maxContentWidth);

    final width = drawingTextPainter.width;
    final height = drawingTextPainter.height;

    /// if we have multiple bar lines,
    /// there are more than one FlCandidate on touch area,
    /// we should get the most top FlSpot Offset to draw the tooltip on top of it
    final Offset mostTopOffset = Offset(
      getPixelX(showOnSpot.x, chartUsableSize),
      getPixelY(showOnSpot.y, chartUsableSize),
    );

    final double tooltipWidth = width + tooltipData.tooltipPadding.horizontal;
    final double tooltipHeight = height + tooltipData.tooltipPadding.vertical;

    /// draw the background rect with rounded radius
    final Rect rect = Rect.fromLTWH(
      mostTopOffset.dx - (tooltipWidth / 2),
      mostTopOffset.dy - tooltipHeight - tooltipItem.bottomMargin,
      tooltipWidth,
      tooltipHeight);
    final Radius radius = Radius.circular(tooltipData.tooltipRoundedRadius);
    final RRect roundedRect = RRect.fromRectAndCorners(rect,
      topLeft: radius, topRight: radius, bottomLeft: radius, bottomRight: radius);
    bgTouchTooltipPaint.color = tooltipData.tooltipBgColor;
    canvas.drawRRect(roundedRect, bgTouchTooltipPaint);

    /// draw the texts one by one in below of each other
    final drawOffset = Offset(
      rect.center.dx - (drawingTextPainter.width / 2),
      rect.topCenter.dy + tooltipData.tooltipPadding.top,
    );
    drawingTextPainter.paint(canvas, drawOffset);
  }

  /// We add our needed horizontal space to parent needed.
  /// we have some titles that maybe draw in the left and right side of our chart,
  /// then we should draw the chart a with some left space,
  /// the left space is [getLeftOffsetDrawSize],
  /// and the whole space is [getExtraNeededHorizontalSpace]
  @override
  double getExtraNeededHorizontalSpace() {
    double sum = super.getExtraNeededHorizontalSpace();
    if (data.titlesData.show) {
      final leftSide = data.titlesData.leftTitles;
      if (leftSide.showTitles) {
        sum += leftSide.reservedSize + leftSide.margin;
      }

      final rightSide = data.titlesData.rightTitles;
      if (rightSide.showTitles) {
        sum += rightSide.reservedSize + rightSide.margin;
      }
    }
    return sum;
  }

  /// We add our needed vertical space to parent needed.
  /// we have some titles that maybe draw in the top and bottom side of our chart,
  /// then we should draw the chart a with some top space,
  /// the top space is [getTopOffsetDrawSize()],
  /// and the whole space is [getExtraNeededVerticalSpace]
  @override
  double getExtraNeededVerticalSpace() {
    double sum = super.getExtraNeededVerticalSpace();
    if (data.titlesData.show) {
      final topSide = data.titlesData.topTitles;
      if (topSide.showTitles) {
        sum += topSide.reservedSize + topSide.margin;
      }

      final bottomSide = data.titlesData.bottomTitles;
      if (bottomSide.showTitles) {
        sum += bottomSide.reservedSize + bottomSide.margin;
      }
    }
    return sum;
  }

  /// calculate left offset for draw the chart,
  /// maybe we want to show both left and right titles,
  /// then just the left titles will effect on this function.
  @override
  double getLeftOffsetDrawSize() {
    var sum = super.getLeftOffsetDrawSize();

    final leftTitles = data.titlesData.leftTitles;
    if (data.titlesData.show && leftTitles.showTitles) {
      sum += leftTitles.reservedSize + leftTitles.margin;
    }
    return sum;
  }

  /// calculate top offset for draw the chart,
  /// maybe we want to show both top and bottom titles,
  /// then just the top titles will effect on this function.
  @override
  double getTopOffsetDrawSize() {
    var sum = super.getTopOffsetDrawSize();

    final topTitles = data.titlesData.topTitles;
    if (data.titlesData.show && topTitles.showTitles) {
      sum += topTitles.reservedSize + topTitles.margin;
    }

    return sum;
  }

  @override
  ScatterTouchResponse handleTouch(FlTouchInput touchInput, Size size) {

    final Size chartViewSize = getChartUsableDrawSize(size);

    for (int i = 0; i < data.scatterSpots.length; i++) {
      final spot = data.scatterSpots[i];

      final spotPixelX = getPixelX(spot.x, chartViewSize);
      final spotPixelY = getPixelY(spot.y, chartViewSize);

      if ((touchInput.getOffset().dx - spotPixelX).abs() <= (spot.radius / 2) + data.scatterTouchData.touchSpotThreshold
        && (touchInput.getOffset().dy - spotPixelY).abs() <= (spot.radius / 2) + data.scatterTouchData.touchSpotThreshold) {
        return ScatterTouchResponse(touchInput, spot, i);
      }
    }

    return ScatterTouchResponse(touchInput, null, -1);
  }

  @override
  bool shouldRepaint(ScatterChartPainter oldDelegate) => oldDelegate.data != data;
}