import 'dart:math' as math;

import '../../../fl_chart.dart';
import '../base/base_chart/base_chart_painter.dart';
import '../../utils/utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

import 'pie_chart.dart';
import 'pie_chart_data.dart';

/// this class will paint the [PieChart] based on the [PieChartData]
class PieChartPainter extends BaseChartPainter<PieChartData> with TouchHandler<PieTouchResponse> {
  /// [sectionPaint] responsible to paint each section
  /// [sectionsSpaceClearPaint] responsible to clear the space between the sections
  /// [centerSpacePaint] responsible to draw the center space of our chart.
  Paint sectionPaint, sectionsSpaceClearPaint, centerSpacePaint;

  /// We hold this calculated angles to use in touch handling,
  List<double> sectionsAngle;

  PieChartPainter(
    PieChartData data,
    PieChartData targetData,
    Function(TouchHandler) touchHandler,
  ) : super(data, targetData,) {
    touchHandler(this);

    sectionPaint = Paint()..style = PaintingStyle.stroke;

    sectionsSpaceClearPaint = Paint()
      ..style = PaintingStyle.fill
      ..color = const Color(0x000000000)
      ..blendMode = BlendMode.srcOut;

    centerSpacePaint = Paint()
      ..style = PaintingStyle.fill
      ..color = data.centerSpaceColor;
  }

  @override
  void paint(Canvas canvas, Size size) {
    super.paint(canvas, size);
    if (data.sections.isEmpty) {
      return;
    }

    sectionsAngle = _calculateSectionsAngle(data.sections, data.sumValue);

    drawCenterSpace(canvas, size);
    drawSections(canvas, size, sectionsAngle);
    drawTexts(canvas, size);
  }

  List<double> _calculateSectionsAngle(List<PieChartSectionData> sections, double sumValue) {
    return sections.map((section) {
      return 360 * (section.value / sumValue);
    }).toList();
  }

  void drawCenterSpace(Canvas canvas, Size viewSize) {
    final double centerX = viewSize.width / 2;
    final double centerY = viewSize.height / 2;

    canvas.drawCircle(Offset(centerX, centerY), data.centerSpaceRadius, centerSpacePaint);
  }

  void drawSections(Canvas canvas, Size viewSize, List<double> sectionsAngle) {
    canvas.saveLayer(Rect.fromLTWH(0, 0, viewSize.width, viewSize.height), Paint());
    final Offset center = Offset(viewSize.width / 2, viewSize.height / 2);

    double tempAngle = data.startDegreeOffset;

    for (int i = 0; i < data.sections.length; i++) {
      final section = data.sections[i];
      final sectionDegree = sectionsAngle[i];

      final rect = Rect.fromCircle(
        center: center,
        radius: data.centerSpaceRadius + (section.radius / 2),
      );

      sectionPaint.color = section.color;
      sectionPaint.strokeWidth = section.radius;

      final double startAngle = tempAngle;
      final double sweepAngle = sectionDegree;
      canvas.drawArc(
        rect,
        radians(startAngle),
        radians(sweepAngle),
        false,
        sectionPaint,
      );

      tempAngle += sweepAngle;
    }

    removeSectionsSpace(canvas, viewSize);
  }

  /// firstly the sections draw close to eachOther without any space,
  /// then here we clear a line with given [PieChartData.width]
  void removeSectionsSpace(Canvas canvas, Size viewSize) {
    const double extraLineSize = 1;
    final Offset center = Offset(viewSize.width / 2, viewSize.height / 2);

    double tempAngle = data.startDegreeOffset;
    data.sections.asMap().forEach((index, section) {
      final int previousIndex = index == 0 ? data.sections.length - 1 : index - 1;
      final previousSection = data.sections[previousIndex];

      final double maxSectionRadius = math.max(section.radius, previousSection.radius);

      final double startAngle = tempAngle;
      final double sweepAngle = 360 * (section.value / data.sumValue);

      final Offset sectionsStartFrom = center +
          Offset(
            math.cos(radians(startAngle)) * (data.centerSpaceRadius - extraLineSize),
            math.sin(radians(startAngle)) * (data.centerSpaceRadius - extraLineSize),
          );

      final Offset sectionsStartTo = center +
          Offset(
            math.cos(radians(startAngle)) *
                (data.centerSpaceRadius + maxSectionRadius + extraLineSize),
            math.sin(radians(startAngle)) *
                (data.centerSpaceRadius + maxSectionRadius + extraLineSize),
          );

      sectionsSpaceClearPaint.strokeWidth = data.sectionsSpace;
      canvas.drawLine(sectionsStartFrom, sectionsStartTo, sectionsSpaceClearPaint);
      tempAngle += sweepAngle;
    });
    canvas.restore();
  }

  void drawTexts(Canvas canvas, Size viewSize) {
    final Offset center = Offset(viewSize.width / 2, viewSize.height / 2);

    double tempAngle = data.startDegreeOffset;
    for (int i = 0; i < data.sections.length; i++) {
      final PieChartSectionData section = data.sections[i];
      final double startAngle = tempAngle;
      final double sweepAngle = 360 * (section.value / data.sumValue);
      final double sectionCenterAngle = startAngle + (sweepAngle / 2);
      final Offset sectionCenterOffset = center +
          Offset(
            math.cos(radians(sectionCenterAngle)) *
                (data.centerSpaceRadius + (section.radius * section.titlePositionPercentageOffset)),
            math.sin(radians(sectionCenterAngle)) *
                (data.centerSpaceRadius + (section.radius * section.titlePositionPercentageOffset)),
          );

      if (section.showTitle) {
        final TextSpan span = TextSpan(style: section.titleStyle, text: section.title);
        final TextPainter tp =
            TextPainter(text: span, textAlign: TextAlign.center, textDirection: TextDirection.ltr);
        tp.layout();
        tp.paint(canvas, sectionCenterOffset - Offset(tp.width / 2, tp.height / 2));
      }

      tempAngle += sweepAngle;
    }
  }

  @override
  PieTouchResponse handleTouch(FlTouchInput touchInput, Size size) {
    return _getTouchedDetails(size, touchInput, sectionsAngle);
  }

  /// find touched section by the value of [touchInputNotifier]
  PieTouchResponse _getTouchedDetails(Size viewSize, FlTouchInput touchInput, List<double> sectionsAngle) {
    final center = Offset(viewSize.width / 2, viewSize.height / 2);

    if (touchInput.getOffset() == null) {
      return null;
    }

    final touchedPoint2 = touchInput.getOffset() - center;

    final touchX = touchedPoint2.dx;
    final touchY = touchedPoint2.dy;

    final touchR = math.sqrt(math.pow(touchX, 2) + math.pow(touchY, 2));
    double touchAngle = degrees(math.atan2(touchY, touchX));
    touchAngle = touchAngle < 0 ? (180 - touchAngle.abs()) + 180 : touchAngle;

    PieChartSectionData foundSectionData;
    int foundSectionDataPosition;

    /// Find the nearest section base on the touch spot
    double tempAngle = data.startDegreeOffset;
    for (int i = 0; i < data.sections.length; i++) {
      final section = data.sections[i];
      double sectionAngle = sectionsAngle[i];

      tempAngle %= 360;
      sectionAngle %= 360;

      /// degree criteria
      final space = data.sectionsSpace / 2;
      final fromDegree = tempAngle + space;
      final toDegree = sectionAngle + tempAngle - space;
      final isInDegree = touchAngle >= fromDegree && touchAngle <= toDegree;

      /// radius criteria
      final centerRadius = data.centerSpaceRadius;
      final sectionRadius = centerRadius + section.radius;
      final isInRadius = touchR > centerRadius && touchR <= sectionRadius;

      if (isInDegree && isInRadius) {
        foundSectionData = section;
        foundSectionDataPosition = i;
        break;
      }

      tempAngle += sectionAngle;
    }

    return PieTouchResponse(foundSectionData, foundSectionDataPosition, touchAngle, touchR, touchInput);
  }

  @override
  bool shouldRepaint(PieChartPainter oldDelegate) => oldDelegate.data != data;
}
