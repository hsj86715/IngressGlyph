import 'dart:async';
import 'dart:ui';

import '../base/base_chart/touch_input.dart';
import '../../utils/lerp.dart';
import 'package:flutter/material.dart';

import '../base/base_chart/base_chart_data.dart';
import 'pie_chart.dart';

/// Holds all data needed to draw [PieChart],
class PieChartData extends BaseChartData {
  final List<PieChartSectionData> sections;
  final double centerSpaceRadius;
  final Color centerSpaceColor;
  final PieTouchData pieTouchData;

  /// space between sections together
  final double sectionsSpace;

  /// determines where the sections will be drawn value should be (0 to 360),
  /// the default value is 0, it means the sections
  /// will be drawn from 0 degree (zero degree is right/center of a circle).
  final double startDegreeOffset;

  /// we hold this value to determine weight of each [FlBorderData.value].
  double sumValue;

  PieChartData({
    this.sections = const [],
    this.centerSpaceRadius = 80,
    this.centerSpaceColor = Colors.transparent,
    this.sectionsSpace = 2,
    this.startDegreeOffset = 0,
    this.pieTouchData = const PieTouchData(),
    FlBorderData borderData,
  }) : super(borderData: borderData, touchData: pieTouchData) {
    sumValue = sections
        .map((data) => data.value)
        .reduce((first, second) => first + second);
  }

  @override
  BaseChartData lerp(BaseChartData a, BaseChartData b, double t) {
    if (a is PieChartData && b is PieChartData && t != null) {
      return PieChartData(
        borderData: FlBorderData.lerp(a.borderData, b.borderData, t),
        centerSpaceColor: Color.lerp(a.centerSpaceColor, b.centerSpaceColor, t),
        centerSpaceRadius: lerpDouble(a.centerSpaceRadius, b.centerSpaceRadius, t),
        pieTouchData: b.pieTouchData,
        sectionsSpace: lerpDouble(a.sectionsSpace, b.sectionsSpace, t),
        startDegreeOffset: lerpDouble(a.startDegreeOffset, b.startDegreeOffset, t),
        sections: lerpPieChartSectionDataList(a.sections, b.sections, t),
      );
    } else {
      throw Exception('Illegal State');
    }
  }
}

/***** PieChartSectionData *****/

/// this class holds data about each section of the pie chart,
class PieChartSectionData {
  /// the [value] is weight of the section,
  /// for example if all values is 25, and we have 4 section,
  /// then the sum is 100 and each section takes 1/4 of whole circle (360/4) degree.
  final double value;
  final Color color;

  /// the [radius] is the width radius of each section
  final double radius;
  final bool showTitle;
  final TextStyle titleStyle;
  final String title;

  /// the [titlePositionPercentageOffset] is the place of showing title on the section
  /// the degree is statically on the center of each section,
  /// but the radius of drawing is depend of this field,
  /// this field should be between 0 and 1,
  /// if it is 0 the title will be drawn near the inside section,
  /// if it is 1 the title will be drawn near the outside of section,
  /// the default value is 0.5, means it draw on the center of section.
  final double titlePositionPercentageOffset;

  PieChartSectionData({
    this.value = 10,
    this.color = Colors.red,
    this.radius = 40,
    this.showTitle = true,
    this.titleStyle = const TextStyle(
        color: Colors.white, fontSize: 16, fontWeight: FontWeight.bold),
    this.title = '1',
    this.titlePositionPercentageOffset = 0.5,
  });

  PieChartSectionData copyWith({
    double value,
    Color color,
    double radius,
    bool showTitle,
    TextStyle titleStyle,
    String title,
    double titlePositionPercentageOffset,
  }) {
    return PieChartSectionData(
      value: value ?? this.value,
      color: color ?? this.color,
      radius: radius ?? this.radius,
      showTitle: showTitle ?? this.showTitle,
      titleStyle: titleStyle ?? this.titleStyle,
      title: title ?? this.title,
      titlePositionPercentageOffset:
          titlePositionPercentageOffset ?? this.titlePositionPercentageOffset,
    );
  }

  static PieChartSectionData lerp(PieChartSectionData a, PieChartSectionData b, double t) {
    return PieChartSectionData(
      color: Color.lerp(a.color, b.color, t),
      radius: lerpDouble(a.radius, b.radius, t),
      showTitle: b.showTitle,
      title: b.title,
      titlePositionPercentageOffset: lerpDouble(a.titlePositionPercentageOffset, b.titlePositionPercentageOffset, t),
      titleStyle: TextStyle.lerp(a.titleStyle, b.titleStyle, t),
      value: lerpDouble(a.value, b.value, t),
    );
  }
}

/// holds data for handling touch events on the [PieChart]
class PieTouchData extends FlTouchData {

  /// you can implement it to receive touches callback
  final Function(PieTouchResponse) touchCallback;
  
  const PieTouchData({
    bool enabled = true,
    bool enableNormalTouch = true,
    this.touchCallback,
  }) : super(enabled, enableNormalTouch);
}

/// holds the data of touch response on the [PieChart]
/// used in the [PieTouchData] in a [StreamSink]
class PieTouchResponse extends BaseTouchResponse {
  /// touch happened on this section
  final PieChartSectionData touchedSection;

  /// touch happened on this position
  final int touchedSectionIndex;

  /// touch happened with this angle on the [PieChart]
  final double touchAngle;

  /// touch happened with this radius on the [PieChart]
  final double touchRadius;

  PieTouchResponse(
    this.touchedSection,
    this.touchedSectionIndex,
    this.touchAngle,
    this.touchRadius,
    FlTouchInput touchInput,
  ) : super(touchInput);
}

class PieChartDataTween extends Tween<PieChartData> {

  PieChartDataTween({PieChartData begin, PieChartData end}) : super(begin: begin, end: end);

  @override
  PieChartData lerp(double t) => begin.lerp(begin, end, t);

}