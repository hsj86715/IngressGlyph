import 'dart:ui';

import 'package:flutter/material.dart';
import 'dart:math';

///基础绘制，画出六边形及必要的空心圆点
class _GlyphBasePainter extends CustomPainter {
  final List<Point> _pathPoints = List(); //用于绘制序列路径的空心圆点的位置
  double _pointRadius;

  ///画空心小圆点
  void _drawEmptyPoint(Offset pointCenter, Paint paint, Canvas canvas,
      {bool defPaint = true}) {
//    print(pointCenter);
    if (defPaint) {
      paint.strokeWidth = 1;
      paint.color = Colors.cyanAccent[100];
    }
    canvas.drawCircle(pointCenter, _pointRadius, paint);
    _pathPoints.add(Point(pointCenter.dx, pointCenter.dy));
  }

  ///绘制边界细线
  void _drawEdgePath(Paint paint, Canvas canvas, Path path) {
    paint.strokeWidth = 1.5;
    paint.color = Colors.cyanAccent[400];
    paint.strokeCap = StrokeCap.round;
    canvas.drawPath(path, paint);
  }

  ///绘制Glyph序列
  void _drawSequencePath(Paint paint, Canvas canvas) {
    //todo 由子类实现，这里什么也不干
  }

  @override
  void paint(Canvas canvas, Size size) {
    Paint paint = Paint();
    paint.color = Colors.black12;
    paint.style = PaintingStyle.stroke;
    paint.strokeWidth = 1;
    paint.isAntiAlias = true;

    Offset center = size.center(Offset(0, 0));
    //widget允许的最大半径
    final maxRadius = min(center.dx, center.dy);
    _pointRadius = maxRadius / 40; //位置圆点的半径
    double radius = maxRadius - _pointRadius * 2;
    //中心点
    _drawEmptyPoint(center, paint, canvas);

    Path path = Path();
    double px, py;
    var degree = 330;
    while (degree > 0) {
      if (degree % 90 != 0) {
        //斜线中间点
        px = cos(degree / 180 * pi) * radius / 2;
        py = sin(degree / 180 * pi) * radius / 2;
        _drawEmptyPoint(Offset(center.dx + px, center.dy + py), paint, canvas);
      }
      //边上的点
      px = cos(degree / 180 * pi) * radius;
      py = sin(degree / 180 * pi) * radius;
      _drawEmptyPoint(Offset(center.dx + px, center.dy + py), paint, canvas);

      //连线
      if (degree == 330) {
        path.moveTo(px + center.dx + cos(degree / 180 * pi) * _pointRadius,
            center.dy - py - sin(degree / 180 * pi) * _pointRadius);
      } else {
        path.lineTo(px + center.dx + cos(degree / 180 * pi) * _pointRadius,
            center.dy - py - sin(degree / 180 * pi) * _pointRadius);
      }
      degree -= 60;
    }

    path.close();
    _drawEdgePath(paint, canvas, path);
    _drawSequencePath(paint, canvas);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return oldDelegate != this;
  }
}

///绘制静态的序列
class GlyphStaticPathPainter extends _GlyphBasePainter {
  final List<Point> _errorPoints = List(); //手动绘制的不在有效路径上的点
  final List<Point> _sequencePoints = List(); //正确的序列路径上的点
  final List<int> _sequences;
  final String seqCost; // 绘制图形花费的时间
  final bool drawSeq; //是否绘制出正确的序列
  final Color seqPathColor;

  GlyphStaticPathPainter(this._sequences,
      {this.seqCost = '',
      this.drawSeq = true,
      this.seqPathColor = Colors.blueGrey});

  @override
  void _drawEmptyPoint(Offset pointCenter, Paint paint, Canvas canvas,
      {bool defPaint = true}) {
    if (_errorPoints.isNotEmpty) {
      if (_errorPoints.contains(Point(pointCenter.dx, pointCenter.dy))) {
        //错误的点的颜色
        paint.color = Colors.redAccent[400];
        defPaint = false;
      }
    }
    if (_sequencePoints.isNotEmpty) {
      if (_sequencePoints.contains(Point(pointCenter.dx, pointCenter.dy))) {
        //错误的点的颜色
        paint.color = Colors.greenAccent[400];
        defPaint = false;
      }
    }
    super._drawEmptyPoint(pointCenter, paint, canvas, defPaint: defPaint);
  }

  ///绘制序列路径
  @override
  void _drawSequencePath(Paint paint, Canvas canvas) {
    if (!drawSeq) {
      return;
    }
    if (_sequences.length < 2) {
      print(
          "Path sequence: $_sequences is invalide, at lest must have 2 points!");
      return;
    }
    Path path = new Path();
    Point p = _pathPoints[_sequences[0] - 1];
    path.moveTo(p.x, p.y);
    for (var i = 1; i < _sequences.length; i++) {
      p = _pathPoints[_sequences[i] - 1];
      path.lineTo(p.x, p.y);
    }
    paint.strokeWidth = 1.25;
    paint.color = seqPathColor;
    paint.strokeCap = StrokeCap.round;
    canvas.drawPath(path, paint);

    if (seqCost.isNotEmpty) {
      TextPainter tp = TextPainter(
          text: TextSpan(
              text: seqCost,
              style: TextStyle(fontSize: 10, color: Colors.black)),
          textDirection: TextDirection.ltr);
      tp.layout(minWidth: 10, maxWidth: 80);
      tp.paint(canvas, Offset(0, 0));
    }
  }
}

///绘制动态的序列(由Animate不断回调实现动态，这里的画法依然为静态)
class GlyphDynamicPathPainter extends GlyphStaticPathPainter {
  var _percent = 0.0;

  GlyphDynamicPathPainter(List<int> sequences, this._percent)
      : super(sequences) {
//    print("Sequences: $sequences, Percent: $_percent");
  }

  @override
  void _drawSequencePath(Paint paint, Canvas canvas) {
    if (_sequences.length < 2) {
      print(
          "Path sequence: $_sequences is invalide, at lest must have 2 points!");
      return;
    }
    if (_percent >= 1.0) {
      super._drawSequencePath(paint, canvas);
    } else {
      //先连接所有节点
      Path path = new Path();
      Point p = _pathPoints[_sequences[0] - 1];
      path.moveTo(p.x, p.y);
      for (var i = 1; i < _sequences.length; i++) {
        p = _pathPoints[_sequences[i] - 1];
        path.lineTo(p.x, p.y);
      }
      //截取路径的部分
      PathMetrics metrics = path.computeMetrics();
      PathMetric pathMetric = metrics.elementAt(0);
      double currLength = pathMetric.length * _percent;
//      print("Total length: ${pathMetric.length}, current length: $currLength");
      Path subPath = pathMetric.extractPath(0, currLength);
      //绘制部分路径
      paint.strokeWidth = 1.25;
      paint.color = Colors.blueGrey[300];
      paint.strokeCap = StrokeCap.round;
      canvas.drawPath(subPath, paint);
    }
  }
}

typedef PaintComplete = void Function(bool hasErr, int drawLength);

class GlyphPractisePainter extends GlyphStaticPathPainter {
  final List<Offset> fingerPath;

  //记录手指划过找到的最邻近的可能的点
  final List<Point> _closestPoints = List();
  final bool showCorrectSeq;
  final PaintComplete paintComplete;

  GlyphPractisePainter(List<int> sequences, this.fingerPath,
      {this.showCorrectSeq = false, this.paintComplete})
      : super(sequences) {
//    print(fingerPath);
  }

  @override
  void _drawSequencePath(Paint paint, Canvas canvas) {
    //绘制正确的完整路径
    if (showCorrectSeq) {
      super._drawSequencePath(paint, canvas);
    }
    if (fingerPath == null || fingerPath.length < 1) {
      _closestPoints.clear();
      _sequencePoints.clear();
      return;
    }
    if (_sequencePoints.length == 0) {
      _sequences.forEach((seq) {
        _sequencePoints.add(_pathPoints[seq - 1]);
      });
    }

    //连接所有手指划过的点
    Path path = new Path();
    Offset offset = fingerPath[0];
    path.moveTo(offset.dx, offset.dy);
    fingerPath.forEach((offset) {
      path.lineTo(offset.dx, offset.dy);
      //判定绘制的点是否正确
      Point closest = _getClosestPoint(offset);
      if (closest != null && !_closestPoints.contains(closest)) {
        _closestPoints.add(closest);
        if (!_sequencePoints.contains(closest)) {
          _errorPoints.add(closest);
        }
        //重绘手指经过的点
        _drawEmptyPoint(Offset(closest.x, closest.y), paint, canvas,
            defPaint: false);
      }
    });
    //绘制手指划过的路径
    paint.strokeWidth = 2.5;
    paint.color = Colors.lime[800];
    paint.strokeCap = StrokeCap.round;
    paint.maskFilter = MaskFilter.blur(BlurStyle.solid, 2.5);
    canvas.drawPath(path, paint);
    if (paintComplete != null) {
      paintComplete(_errorPoints.isNotEmpty, _closestPoints.length);
    }
  }

//  @override
//  void _drawEmptyPoint(Offset pointCenter, Paint paint, Canvas canvas,
//      {bool defPaint = true}) {
//    if (_closestPoints.contains(Point(pointCenter.dx, pointCenter.dy))) {
//      if (_sequencePoints.contains(Point(pointCenter.dx, pointCenter.dy))) {
////        //正确的点的颜色
//        paint.color = Colors.greenAccent[400];
//      } else {
//        //错误的点的颜色
//        paint.color = Colors.redAccent[400];
//        _hasErr = true;
//      }
//      paint.strokeWidth = 2;
//      super._drawEmptyPoint(pointCenter, paint, canvas, defPaint: false);
//    } else {
//      super._drawEmptyPoint(pointCenter, paint, canvas);
//    }
//  }

  ///获取临近的图像上的点
  Point _getClosestPoint(Offset offset) {
    Point closest;
    _pathPoints.forEach((point) {
      if (sqrt(pow(offset.dx - point.x, 2) + pow(offset.dy - point.y, 2)) <=
          25) {
        closest = point;
      }
    });
    return closest;
  }
}
