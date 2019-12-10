import '../../../fl_chart.dart';
import '../base/base_chart/base_chart_painter.dart';
import '../base/base_chart/touch_input.dart';
import '../../utils/utils.dart';
import 'package:flutter/cupertino.dart';

import 'line_chart_data.dart';
import 'line_chart_painter.dart';

class LineChart extends ImplicitlyAnimatedWidget {
  final LineChartData data;

  const LineChart(
    this.data, {
    Duration swapAnimationDuration = const Duration(milliseconds: 150),
  }) : super(duration: swapAnimationDuration);

  @override
  LineChartState createState() => LineChartState();
}

class LineChartState extends AnimatedWidgetBaseState<LineChart> {
  /// we handle under the hood animations (implicit animations) via this tween,
  /// it lerps between the old [LineChartData] to the new one.
  LineChartDataTween _lineChartDataTween;

  TouchHandler _touchHandler;

  final GlobalKey _chartKey = GlobalKey();

  final List<MapEntry<int, List<LineBarSpot>>> _showingTouchedTooltips = [];

  final Map<int, List<int>> _showingTouchedIndicators = {};

  @override
  Widget build(BuildContext context) {

    final LineChartData showingData = _getDate();
    final Size chartSize = _getChartSize();
    final LineTouchData touchData = showingData.lineTouchData;

    return GestureDetector(
      onLongPressStart: (d) {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressStart(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressEnd: (d) async {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressEnd(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressMoveUpdate: (d) {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressMoveUpdate(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanCancel: () async {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlPanEnd(Offset.zero, Velocity(pixelsPerSecond: Offset.zero)), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanEnd: (DragEndDetails details) async {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlPanEnd(Offset.zero, details.velocity), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanDown: (DragDownDetails details) {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlPanStart(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanUpdate: (DragUpdateDetails details) {
        final LineTouchResponse response =
        _touchHandler?.handleTouch(FlPanMoveUpdate(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      child: CustomPaint(
        key: _chartKey,
        size: getDefaultSize(context),
        painter: LineChartPainter(
          _withTouchedIndicators(_lineChartDataTween.evaluate(animation)),
          _withTouchedIndicators(showingData),
          (touchHandler) {
            setState(() {
              _touchHandler = touchHandler;
            });
          },
        ),
      ),
    );
  }

  bool _canHandleTouch(LineTouchResponse response, LineTouchData touchData) {
    return response != null && touchData != null && touchData.touchCallback != null;
  }

  LineChartData _withTouchedIndicators(LineChartData lineChartData) {
    if (lineChartData == null) {
      return lineChartData;
    }

    if (!lineChartData.lineTouchData.enabled || !lineChartData.lineTouchData.handleBuiltInTouches) {
      return lineChartData;
    }

    return lineChartData.copyWith(
      showingTooltipIndicators: _showingTouchedTooltips,
      lineBarsData: lineChartData.lineBarsData.map((barData) {
        final index = lineChartData.lineBarsData.indexOf(barData);
        return barData.copyWith(
          showingIndicators: _showingTouchedIndicators[index] ?? [],
        );
      }).toList(),
    );
  }

  Size _getChartSize() {
//    if (_chartKey.currentContext != null) {
//      final RenderBox containerRenderBox = _chartKey.currentContext.findRenderObject();
//      return containerRenderBox.constraints.biggest;
//    } else {
      return getDefaultSize(context);
//    }
  }

  LineChartData _getDate() {
    final lineTouchData = widget.data.lineTouchData;
    if (lineTouchData.enabled && lineTouchData.handleBuiltInTouches) {
      return widget.data.copyWith(
        lineTouchData: widget.data.lineTouchData.copyWith(touchCallback: _handleBuiltInTouch),
      );
    }
    return widget.data;
  }

  void _handleBuiltInTouch(LineTouchResponse touchResponse) {
    if (widget.data.lineTouchData.touchCallback != null) {
      widget.data.lineTouchData.touchCallback(touchResponse);
    }

    if (touchResponse.touchInput is FlPanStart ||
        touchResponse.touchInput is FlPanMoveUpdate ||
        touchResponse.touchInput is FlLongPressStart ||
        touchResponse.touchInput is FlLongPressMoveUpdate) {
      setState(() {
        final sortedLineSpots = List.of(touchResponse.lineBarSpots);
        sortedLineSpots.sort((spot1, spot2) => spot2.y.compareTo(spot1.y));

        _showingTouchedIndicators.clear();
        for (int i = 0; i < touchResponse.lineBarSpots.length; i++) {
          final touchedBarSpot = touchResponse.lineBarSpots[i];
          final barPos = touchedBarSpot.barIndex;
          _showingTouchedIndicators[barPos] = [touchedBarSpot.spotIndex];
        }

        _showingTouchedTooltips.clear();
        _showingTouchedTooltips.add(MapEntry(0, sortedLineSpots));
      });
    } else {
      setState(() {
        _showingTouchedTooltips.clear();
        _showingTouchedIndicators.clear();
      });
    }
  }

  @override
  void forEachTween(visitor) {
    _lineChartDataTween = visitor(
      _lineChartDataTween,
      _getDate(),
      (dynamic value) => LineChartDataTween(begin: value),
    );
  }
}