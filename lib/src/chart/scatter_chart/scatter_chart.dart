import '../../../fl_chart.dart';
import '../base/base_chart/base_chart_painter.dart';
import '../base/base_chart/touch_input.dart';
import 'scatter_chart_data.dart';
import 'scatter_chart_painter.dart';
import '../../utils/utils.dart';
import 'package:flutter/cupertino.dart';

class ScatterChart extends ImplicitlyAnimatedWidget {
  final ScatterChartData data;

  const ScatterChart(
    this.data, {
    Duration swapAnimationDuration = const Duration(milliseconds: 150),
  }) : super(duration: swapAnimationDuration);

  @override
  ScatterChartState createState() => ScatterChartState();
}

class ScatterChartState extends AnimatedWidgetBaseState<ScatterChart> {
  /// we handle under the hood animations (implicit animations) via this tween,
  /// it lerps between the old [ScatterChartData] to the new one.
  ScatterChartDataTween _scatterChartDataTween;

  TouchHandler<ScatterTouchResponse> _touchHandler;

  final GlobalKey _chartKey = GlobalKey();
  
  List<int> touchedSpots = [];

  @override
  Widget build(BuildContext context) {
    final ScatterChartData showingData = _getDate();
    final Size chartSize = _getChartSize();
    final ScatterTouchData touchData = showingData.scatterTouchData;

    return GestureDetector(
      onLongPressStart: (d) {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlLongPressStart(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressEnd: (d) async {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlLongPressEnd(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onLongPressMoveUpdate: (d) {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlLongPressMoveUpdate(d.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanCancel: () async {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlPanEnd(Offset.zero, Velocity(pixelsPerSecond: Offset.zero)), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanEnd: (DragEndDetails details) async {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlPanEnd(Offset.zero, details.velocity), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanDown: (DragDownDetails details) {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlPanStart(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      onPanUpdate: (DragUpdateDetails details) {
        final ScatterTouchResponse response =
            _touchHandler?.handleTouch(FlPanMoveUpdate(details.localPosition), chartSize);
        if (_canHandleTouch(response, touchData)) {
          touchData.touchCallback(response);
        }
      },
      child: CustomPaint(
        key: _chartKey,
        size: chartSize,
        painter: ScatterChartPainter(
          _withTouchedIndicators(_scatterChartDataTween.evaluate(animation)),
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

  bool _canHandleTouch(ScatterTouchResponse response, ScatterTouchData touchData) {
    return response != null && touchData != null && touchData.touchCallback != null;
  }

  ScatterChartData _withTouchedIndicators(ScatterChartData scatterChartData) {
    if (scatterChartData == null) {
      return scatterChartData;
    }

    if (!scatterChartData.scatterTouchData.enabled || !scatterChartData.scatterTouchData.handleBuiltInTouches) {
      return scatterChartData;
    }

    return scatterChartData.copyWith(
      showingTooltipIndicators: touchedSpots,
    );
  }

  Size _getChartSize() {
    if (_chartKey.currentContext != null) {
      final RenderBox containerRenderBox = _chartKey.currentContext.findRenderObject();
      return containerRenderBox.constraints.biggest;
    } else {
      return getDefaultSize(context);
    }
  }

  ScatterChartData _getDate() {
    final scatterTouchData = widget.data.scatterTouchData;
    if (scatterTouchData.enabled && scatterTouchData.handleBuiltInTouches) {
      return widget.data.copyWith(
        scatterTouchData: widget.data.scatterTouchData.copyWith(touchCallback: _handleBuiltInTouch),
      );
    }
    return widget.data;
  }

  void _handleBuiltInTouch(ScatterTouchResponse touchResponse) {
    if (widget.data.scatterTouchData.touchCallback != null) {
      widget.data.scatterTouchData.touchCallback(touchResponse);
    }

    if (touchResponse.touchInput is FlPanStart ||
        touchResponse.touchInput is FlPanMoveUpdate ||
        touchResponse.touchInput is FlLongPressStart ||
        touchResponse.touchInput is FlLongPressMoveUpdate) {
      setState(() {
        touchedSpots.clear();
        touchedSpots.add(touchResponse.touchedSpotIndex);
      });
    } else {
      setState(() {
        touchedSpots.clear();
      });
    }
  }

  @override
  void forEachTween(visitor) {
    _scatterChartDataTween = visitor(
      _scatterChartDataTween,
      _getDate(),
      (dynamic value) => ScatterChartDataTween(begin: value),
    );
  }
}