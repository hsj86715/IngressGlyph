import '../../../fl_chart.dart';
import '../bar_chart/bar_chart_painter.dart';
import '../base/base_chart/base_chart_painter.dart';
import '../../utils/utils.dart';
import 'package:flutter/cupertino.dart';

class BarChart extends ImplicitlyAnimatedWidget {
  final BarChartData data;

  const BarChart(
    this.data, {
      Duration swapAnimationDuration = const Duration(milliseconds: 150),
    }) : super(duration: swapAnimationDuration);

  @override
  BarChartState createState() => BarChartState();
}

class BarChartState extends AnimatedWidgetBaseState<BarChart> {
  /// we handle under the hood animations (implicit animations) via this tween,
  /// it lerps between the old [BarChartData] to the new one.
  BarChartDataTween _barChartDataTween;

  TouchHandler _touchHandler;

  final GlobalKey _chartKey = GlobalKey();

  final Map<int, List<int>> _showingTouchedTooltips = {};

  @override
  Widget build(BuildContext context) {

    final BarChartData showingData = _getDate();
    final Size chartSize = _getChartSize();
    final BarTouchData touchData = showingData.barTouchData;

    return GestureDetector(
      onLongPressStart: (d) {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressStart(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressEnd: (d) async {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressEnd(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressMoveUpdate: (d) {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlLongPressMoveUpdate(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanCancel: () async {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlPanEnd(Offset.zero, Velocity(pixelsPerSecond: Offset.zero)), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanEnd: (DragEndDetails details) async {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlPanEnd(Offset.zero, details.velocity), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanDown: (DragDownDetails details) {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlPanStart(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanUpdate: (DragUpdateDetails details) {
        final BarTouchResponse response =
        _touchHandler?.handleTouch(FlPanMoveUpdate(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      child: CustomPaint(
        key: _chartKey,
        size: getDefaultSize(context),
        painter: BarChartPainter(
          _withTouchedIndicators(_barChartDataTween.evaluate(animation)),
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

  bool _canHandleTouch(BarTouchResponse response, BarTouchData touchData) {
    return response != null && touchData != null && touchData.touchCallback != null;
  }

  BarChartData _withTouchedIndicators(BarChartData barChartData) {
    if (barChartData == null) {
      return barChartData;
    }

    if (!barChartData.barTouchData.enabled || !barChartData.barTouchData.handleBuiltInTouches) {
      return barChartData;
    }

    final List<BarChartGroupData> newGroups = [];
    for (int i = 0; i < barChartData.barGroups.length; i++) {
      final group = barChartData.barGroups[i];

      newGroups.add(
        group.copyWith(
          showingTooltipIndicators: _showingTouchedTooltips[i],
        ),
      );
    }

    return barChartData.copyWith(
      barGroups: newGroups,
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

  BarChartData _getDate() {
    final barTouchData = widget.data.barTouchData;
    if (barTouchData.enabled && barTouchData.handleBuiltInTouches) {
      return widget.data.copyWith(
        barTouchData: widget.data.barTouchData.copyWith(touchCallback: _handleBuiltInTouch),
      );
    }
    return widget.data;
  }

  void _handleBuiltInTouch(BarTouchResponse touchResponse) {
    if (widget.data.barTouchData.touchCallback != null) {
      widget.data.barTouchData.touchCallback(touchResponse);
    }

    if (touchResponse.touchInput is FlPanStart ||
      touchResponse.touchInput is FlPanMoveUpdate ||
      touchResponse.touchInput is FlLongPressStart ||
      touchResponse.touchInput is FlLongPressMoveUpdate) {
      setState(() {
        if (touchResponse.spot == null) {
          _showingTouchedTooltips.clear();
          return;
        }
        final groupIndex = touchResponse.spot.touchedBarGroupIndex;
        final rodIndex = touchResponse.spot.touchedRodDataIndex;

        _showingTouchedTooltips.clear();
        _showingTouchedTooltips[groupIndex] = [rodIndex];
      });
    } else {
      setState(() {
        _showingTouchedTooltips.clear();
      });
    }
  }

  @override
  void forEachTween(visitor) {
    _barChartDataTween = visitor(
      _barChartDataTween,
      widget.data,
        (dynamic value) => BarChartDataTween(begin: value),
    );
  }
}