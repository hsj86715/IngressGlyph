import 'dart:async';
import 'dart:ui' as dui;
import 'package:flutter/material.dart';
import 'package:ingress_assistant/custom/glyph_painter.dart';
import 'package:ingress_assistant/data/glyph_beans.dart';

///基本的静态序列绘制控件
class GlyphSequenceWidget extends StatelessWidget {
  final Size size;
  final List<int> sequences;
  final String timeCost;
  final bool drawSeq;
  final Color seqPathColor;

  GlyphSequenceWidget(this.size, this.sequences,
      {this.timeCost = '',
      this.drawSeq = true,
      this.seqPathColor = Colors.blueGrey});

  @override
  Widget build(BuildContext context) {
    return CustomPaint(
      size: size,
      foregroundPainter: GlyphStaticPathPainter(
          sequences, Theme.of(context).brightness == Brightness.dark,
          drawSeq: drawSeq, seqPathColor: seqPathColor, seqCost: timeCost),
    );
  }
}

///按照步骤先后动态的绘制序列的控件
class GlyphDemonstrateWidget extends StatefulWidget {
  final Size _size;
  List<int> _sequences;
  final VoidCallback onCompleted;

  GlyphDemonstrateWidget(this._size, this._sequences, {this.onCompleted});

  void resetSequences(List<int> sequences) {
    this._sequences = sequences;
  }

  @override
  State<StatefulWidget> createState() {
    return _GlyphDemonstrateWidgetState();
  }
}

class _GlyphDemonstrateWidgetState extends State<GlyphDemonstrateWidget>
    with TickerProviderStateMixin {
  AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    _refreshAnimation();
  }

  void _refreshAnimation() {
    _animationController = AnimationController(
        vsync: this,
        duration: Duration(milliseconds: 150 * widget._sequences.length));
    _animationController.addStatusListener((state) {
      if (state == AnimationStatus.completed && widget.onCompleted != null) {
        widget.onCompleted();
      }
    });
    _animationController.forward();
  }

  @override
  void didUpdateWidget(GlyphDemonstrateWidget oldWidget) {
    print("GlyphDemonstrateWidget didUpdateWidget");
    super.didUpdateWidget(oldWidget);
    _refreshAnimation();
  }

  @override
  void dispose() {
    if (_animationController != null) {
      _animationController.dispose();
    }
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
        animation: CurvedAnimation(
            parent: _animationController, curve: Curves.bounceInOut),
        builder: (BuildContext context, Widget child) {
          return CustomPaint(
              size: widget._size,
              foregroundPainter: GlyphDynamicPathPainter(
                  widget._sequences,
                  _animationController.value,
                  Theme.of(context).brightness == Brightness.dark));
        });
  }
}

class _GlyphPractiseWidget extends StatefulWidget {
  final Size _size;
  final List<int> _sequences;
  final VoidCallback practiseStart;
  final VoidCallback practiseEnd;

  _GlyphPractiseWidget(this._size, this._sequences,
      {this.practiseStart, this.practiseEnd, Key key})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _GlyphPractiseWidgetState();
  }
}

class _GlyphPractiseWidgetState extends State<_GlyphPractiseWidget> {
  List<Offset> _fingerPath = List();
  bool _showCorrectSeq = false;
  bool isCorrect = false;

  @override
  void initState() {
    super.initState();
    _showCorrectSeq = false;
  }

  @override
  void didUpdateWidget(_GlyphPractiseWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    _showCorrectSeq = false;
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onPanStart: (DragStartDetails e) {
          Offset start = e.localPosition;
//          print("用户手指按下：$start");
          if (start.dx > 0 &&
              start.dx < widget._size.width &&
              start.dy > 0 &&
              start.dy < widget._size.height) {
            _fingerPath.add(e.localPosition);
            _showCorrectSeq = false;
          } else {
            _fingerPath.clear();
            _showCorrectSeq = true;
          }
          if (widget.practiseStart != null) {
            widget.practiseStart();
          }
          setState(() {});
        },
        onPanUpdate: (DragUpdateDetails e) {
          Offset update = e.localPosition;
//          print("用户手指滑动：$update");
          if (update.dx > 0 &&
              update.dx < widget._size.width &&
              update.dy > 0 &&
              update.dy < widget._size.height) {
            _fingerPath.add(e.localPosition);
            _showCorrectSeq = false;
          } else {
            _fingerPath.clear();
            _showCorrectSeq = true;
          }
          setState(() {});
        },
        onPanEnd: (DragEndDetails e) {
//          print("用户手指抬起：$e");
          _fingerPath.clear();
          _showCorrectSeq = true;
          if (widget.practiseEnd != null) {
            widget.practiseEnd();
          }
          setState(() {});
        },
        child: Container(
            width: widget._size.width,
            height: widget._size.height,
            decoration: BoxDecoration(color: Colors.transparent),
            child: CustomPaint(
                size: widget._size,
                foregroundPainter: GlyphPractisePainter(
                    widget._sequences,
                    _fingerPath,
                    Theme.of(context).brightness == Brightness.dark,
                    showCorrectSeq: _showCorrectSeq,
                    paintComplete: (bool hasErr, drawLength) {
                  print(hasErr);
                  isCorrect =
                      !hasErr && (drawLength == widget._sequences.length);
                }))));
  }
}

typedef PractiseCallback = void Function(bool correct, int practised);

class HackListPractiseWidget extends StatefulWidget {
  final HackSequence hackList;
  final Size size;
  final VoidCallback glyphPractiseStart;
  final PractiseCallback glyphPractiseEnd;
  final VoidCallback hackListPractiseStart;
  final VoidCallback hackListPractiseEnd;

  HackListPractiseWidget(this.hackList, this.size,
      {Key key,
      this.hackListPractiseStart,
      this.glyphPractiseStart,
      this.glyphPractiseEnd,
      this.hackListPractiseEnd})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return HackListPractiseWidgetState();
  }
}

enum Step { CountDown, ShowTime, Practise, Stopped }

class HackListPractiseWidgetState extends State<HackListPractiseWidget> {
  final GlobalKey<_GlyphPractiseWidgetState> _practiseKey =
      GlobalKey<_GlyphPractiseWidgetState>();
  final int _maxCountDown = 3;

  Timer _countDownTimer;
  Step _currStep = Step.CountDown;
  int _currCount = 0;
  int _currShowIdx = 0;
  int _currPractiseIdx = 0;

  void resetStep() {
    _currStep = Step.CountDown;
  }

  @override
  void initState() {
    super.initState();
    _startCountDown();
  }

  void _startCountDown() {
    if (_countDownTimer != null && _countDownTimer.isActive) {
      _countDownTimer.cancel();
    }
    _countDownTimer = Timer.periodic(Duration(seconds: 1), (timer) {
      _currCount++;
      if (_currCount >= _maxCountDown) {
        _currStep = Step.ShowTime;
        timer.cancel();
      }
      setState(() {});
    });
  }

  @override
  void didUpdateWidget(HackListPractiseWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    print('HackListPractiseWidget didUpdateWidget, currStep=$_currStep');
    if (_currStep == Step.CountDown) {
      _currCount = 0;
      _currShowIdx = 0;
      _currPractiseIdx = 0;
      _startCountDown();
    }
  }

  @override
  Widget build(BuildContext context) {
    if (_currStep == Step.CountDown) {
      return _buildCountDown();
    } else if (_currStep == Step.ShowTime) {
      return _buildShowTime();
    } else if (_currStep == Step.Practise) {
      return _buildPractise();
    } else {
      return _buildStopped();
    }
  }

  Widget _buildCountDown() {
    return Container(
      alignment: Alignment.center,
      width: widget.size.width,
      height: widget.size.height,
      child: Text(
        '${_maxCountDown - _currCount}',
        style: TextStyle(color: Colors.lime[800], fontSize: 144, shadows: [
          dui.Shadow(
              color: Colors.grey, offset: dui.Offset(2, 2), blurRadius: 3)
        ]),
      ),
    );
  }

  Widget _buildShowTime() {
    return GlyphDemonstrateWidget(
      widget.size,
      widget.hackList.sequences[_currShowIdx].path,
      onCompleted: _onDemonstrateCompleted,
    );
  }

  void _onDemonstrateCompleted() {
    print('_onDemonstrateCompleted, currShowIdx=$_currShowIdx');
    _currShowIdx++; //展示下一个
    if (_currShowIdx >= widget.hackList.sequences.length) {
      //执行下一步
      _currStep = Step.Practise;
    }
    setState(() {});
  }

  Widget _buildPractise() {
    return _GlyphPractiseWidget(
      widget.size,
      widget.hackList.sequences[_currPractiseIdx].path,
      practiseStart: _onGlyphPractiseStart,
      practiseEnd: _onGlyphPractiseEnd,
      key: _practiseKey,
    );
  }

  void _onGlyphPractiseStart() {
    print("_onGlyphPractiseStart");
    if (_currPractiseIdx == 0 && widget.hackListPractiseStart != null) {
      widget.hackListPractiseStart();
    }
    if (widget.glyphPractiseStart != null) {
      widget.glyphPractiseStart();
    }
  }

  void _onGlyphPractiseEnd() {
    print("_onGlyphPractiseEnd: _currPractiseIdx=$_currPractiseIdx");
    if (widget.glyphPractiseEnd != null) {
      widget.glyphPractiseEnd(
          _practiseKey.currentState.isCorrect, _currPractiseIdx);
    }
    _currPractiseIdx++;
    if (_currPractiseIdx >= widget.hackList.sequences.length) {
      if (widget.hackListPractiseEnd != null) {
        widget.hackListPractiseEnd();
      }
      _currStep = Step.Stopped;
    } else {
      //只有不是最后的情况才需要重绘
      setState(() {});
    }
  }

  Widget _buildStopped() {
    return GlyphSequenceWidget(widget.size, []);
  }
}
