import 'dart:math';

import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:ingress_assistant/data/glyph_beans.dart';
import 'package:ingress_assistant/custom/indicator.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/tools.dart';

const List<int> colorShades = [300, 400, 500, 600, 700, 800, 900];
Random random = Random(colorShades.length);

///统计数据中count字段标识的含义
enum CountType {
  NUMBER, //纯粹数字
  TIME //时间毫秒值
}

String _getLeftNumTile(int maxNum, double value) {
  if (maxNum <= 8) {
    return value.toInt().toString();
  } else if (maxNum < 100) {
    int steps = maxNum ~/ 8;
    if (steps != 0 && value % steps == 0) {
      return value.toInt().toString();
    } else {
      return '';
    }
  } else if (maxNum < 800) {
    if (value % 100 == 0) {
      return value.toInt().toString();
    } else {
      return '';
    }
  } else if (maxNum < 8000) {
    if (value % 1000 == 0) {
      return '${value.toInt() % 1000}k';
    } else {
      return '';
    }
  } else if (maxNum < 80000) {
    if (value % 10000 == 0) {
      return '${value.toInt() % 10000}0k';
    } else {
      return '';
    }
  } else if (maxNum < 800000) {
    if (value % 100000 == 0) {
      return '${value.toInt() % 100000}00k';
    } else {
      return '';
    }
  } else {
    if (maxNum % 1000000 == 0) {
      return '${value.toInt() % 1000000}m';
    } else {
      return '';
    }
  }
}

bool _checkToShowHorizontalGrid(int maxNum, double value) {
  if (maxNum <= 8) {
    return true;
  } else if (maxNum < 100) {
    int steps = maxNum ~/ 8;
    if (steps != 0 && value % steps == 0) {
      return true;
    }
  } else if (maxNum < 800) {
    if (value % 100 == 0) {
      return true;
    }
  } else if (maxNum < 8000) {
    if (value % 1000 == 0) {
      return true;
    }
  } else if (maxNum < 80000) {
    if (value % 10000 == 0) {
      return true;
    }
  } else if (maxNum < 800000) {
    if (value % 100000 == 0) {
      return true;
    }
  } else {
    if (maxNum % 1000000 == 0) {
      return true;
    }
  }
  return false;
}

FlLine _getDrawingGridLine(double value) {
  if (value == 0) {
    return const FlLine(color: Colors.black, strokeWidth: 1);
  } else {
    return const FlLine(color: Colors.grey, strokeWidth: 0.5);
  }
}

const TextStyle _tileTextStyle =
    TextStyle(color: Colors.black87, fontWeight: FontWeight.bold, fontSize: 14);

class PieStaticsWidget extends StatefulWidget {
  final List<CountResult> staticsData;
  final CountType countType;

  PieStaticsWidget(this.staticsData, {this.countType = CountType.NUMBER});

  @override
  State<StatefulWidget> createState() {
    return _PieStaticsWidgetState();
  }
}

class _PieStaticsWidgetState extends State<PieStaticsWidget> {
  int _touchIdx;
  final List<int> _shadeIdxs = List(); //记录各个色块的颜色深度
  List<MaterialColor> _colorList = List.of(Colors.primaries); //拷贝系统颜色便于打乱顺序

  List<PieChartSectionData> _staticsSections(List<CountResult> results) {
    return List.generate(results.length, (i) {
      final isTouched = i == _touchIdx;
      final double fontSize = isTouched ? 18 : 14;
      final double radius = isTouched ? 100 : 80;
      return PieChartSectionData(
          color: _colorList[i % _colorList.length][colorShades[_shadeIdxs[i]]],
          value: results[i].count.toDouble(),
          title: results[i].count.toString(),
          radius: radius,
          titleStyle: TextStyle(
              fontSize: fontSize,
              fontWeight: FontWeight.bold,
              color: Colors.white));
    });
  }

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < widget.staticsData.length; i++) {
      _shadeIdxs.add(random.nextInt(colorShades.length));
    }
    _colorList.shuffle(random);
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: <Widget>[
        PieChart(PieChartData(
            pieTouchData: PieTouchData(touchCallback: (pieTouchResponse) {
              setState(() {
                if (pieTouchResponse.touchInput is FlLongPressEnd ||
                    pieTouchResponse.touchInput is FlPanEnd) {
                  _touchIdx = -1;
                } else {
                  _touchIdx = pieTouchResponse.touchedSectionIndex;
                }
              });
            }),
            borderData: FlBorderData(show: false),
            sectionsSpace: 0,
            centerSpaceRadius: 40,
            sections: _staticsSections(widget.staticsData))),
        SizedBox(width: 2),
        Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: List.generate(widget.staticsData.length, (i) {
              final isTouched = i == _touchIdx;
              return Padding(
                  padding: EdgeInsets.symmetric(vertical: 4),
                  child: Indicator(
                      textColor: isTouched ? Colors.black : Colors.black54,
                      color: _colorList[i % _colorList.length]
                          [colorShades[_shadeIdxs[i]]],
                      text: widget.staticsData[i].name,
                      isSquare: true));
            }))
      ],
    );
  }
}

class BarStaticsWidget extends StatefulWidget {
  final List<CountResult> staticsData;
  final CountType countType;

  BarStaticsWidget(this.staticsData, {this.countType = CountType.NUMBER});

  @override
  State<StatefulWidget> createState() {
    return _BarStaticsWidgetState();
  }
}

class _BarStaticsWidgetState extends State<BarStaticsWidget> {
  final double _barWidth = 12;
  int _touchIdx;
  List<MaterialColor> _colorList = List.of(Colors.primaries); //拷贝系统颜色便于打乱顺序
  int maxNum = 0;

  List<BarChartGroupData> _staticsBarData() {
    return List.generate(widget.staticsData.length, (i) {
      final bool isTouched = i == _touchIdx;
      return BarChartGroupData(x: i, barsSpace: 4, barRods: [
        BarChartRodData(
            y: widget.staticsData[i].count /
                (widget.countType == CountType.TIME ? 1000 : 1), //毫秒值换算成秒
            color: _colorList[i % _colorList.length][800],
            width: _barWidth,
            isRound: isTouched),
      ]);
    });
  }

  @override
  void initState() {
    super.initState();
    _colorList.shuffle(random);
    widget.staticsData.forEach((result) {
      if (result.count > maxNum) {
        maxNum = result.count;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return BarChart(BarChartData(
        alignment: BarChartAlignment.spaceAround,
        barTouchData: BarTouchData(
            touchTooltipData: BarTouchTooltipData(
                tooltipBgColor: Colors.blueGrey,
                getTooltipItem: (group, groupIdx, rod, rodIdx) {
                  CountResult result = widget.staticsData[groupIdx];
                  return BarTooltipItem(
                      widget.countType == CountType.TIME
                          ? S.of(context).statics_time_cost(
                              result.name, '${result.count / 1000}')
                          : S.of(context).statics_num_value(
                              result.name, '${result.count}'),
                      TextStyle(color: _colorList[groupIdx][900]));
                }),
            touchCallback: (response) {
              setState(() {
                if (response.spot != null &&
                    response.touchInput is! FlPanEnd &&
                    response.touchInput is! FlLongPressEnd) {
                  _touchIdx = response.spot.touchedBarGroupIndex;
                } else {
                  _touchIdx = -1;
                }
              });
            }),
        titlesData: FlTitlesData(
            show: true,
            bottomTitles: SideTitles(
                showTitles: true,
                textStyle: _tileTextStyle,
                margin: 4,
                getTitles: (value) {
                  String tile = widget.staticsData[value.toInt()].name;
                  if (tile.length > 5) {
                    return tile.substring(0, 5);
                  } else {
                    return tile;
                  }
                }),
            leftTitles: SideTitles(
                showTitles: true,
                textStyle: _tileTextStyle,
                reservedSize: 36, //Y轴与左边的距离
                getTitles: (value) {
                  if (widget.countType == CountType.NUMBER) {
                    return _getLeftNumTile(maxNum, value);
                  } else {
                    return '${value}s';
                  }
                })),
        borderData: FlBorderData(show: false),
        barGroups: _staticsBarData(),
        gridData: FlGridData(
            show: true,
            drawHorizontalGrid: true,
            getDrawingHorizontalGridLine: _getDrawingGridLine,
            checkToShowHorizontalGrid: (value) {
              if (widget.countType == CountType.TIME) {
                return true;
              } else {
                return _checkToShowHorizontalGrid(maxNum, value);
              }
            })));
  }
}

class GroupBarStaticsWidget extends StatefulWidget {
  final List<PractiseCountResult> staticsData;

  GroupBarStaticsWidget(this.staticsData);

  @override
  State<StatefulWidget> createState() {
    return _GroupBarStaticsWidgetState();
  }
}

class _GroupBarStaticsWidgetState extends State<GroupBarStaticsWidget> {
  final double _barWidth = 12;
  List<MaterialColor> _colorList = List.of(Colors.primaries); //拷贝系统颜色便于打乱顺序
  int _touchIdx;
  int maxNum = 0;

  @override
  void initState() {
    super.initState();
    _colorList.shuffle(random);
    widget.staticsData.forEach((result) {
      if (result.count > maxNum) {
        maxNum = result.count;
      }
    });
  }

  List<BarChartGroupData> _staticsBarData() {
    return List.generate(widget.staticsData.length, (i) {
      final bool isTouched = i == _touchIdx;
      return BarChartGroupData(x: i, barsSpace: 4, barRods: [
        BarChartRodData(
            y: widget.staticsData[i].count.toDouble(),
            color: _colorList[i % _colorList.length][800],
            width: _barWidth,
            isRound: isTouched),
        BarChartRodData(
            y: widget.staticsData[i].correct.toDouble(),
            color: _colorList[i % _colorList.length][400],
            width: _barWidth,
            isRound: isTouched),
      ]);
    });
  }

  @override
  Widget build(BuildContext context) {
    return BarChart(BarChartData(
        alignment: BarChartAlignment.spaceAround,
        barTouchData: BarTouchData(
            touchTooltipData: BarTouchTooltipData(
                tooltipBgColor: Colors.grey,
                getTooltipItem: (BarChartGroupData group, int groupIndex,
                    BarChartRodData rod, int rodIndex) {
                  PractiseCountResult result = widget.staticsData[groupIndex];
                  return BarTooltipItem(
                      S.of(context).statics_practise_correct(
                          result.name, '${result.count}', '${result.correct}'),
                      TextStyle(color: _colorList[groupIndex][900]));
                }),
            touchCallback: (response) {
              setState(() {
                if (response.spot != null &&
                    response.touchInput is! FlLongPressEnd &&
                    response.touchInput is! FlPanEnd) {
                  _touchIdx = response.spot.touchedBarGroupIndex;
                } else {
                  _touchIdx = -1;
                }
              });
            }),
        titlesData: FlTitlesData(
            show: true,
            bottomTitles: SideTitles(
                showTitles: true,
                textStyle: _tileTextStyle,
                reservedSize: 30,
                getTitles: (value) {
                  return widget.staticsData[value.toInt()].name;
                }),
            leftTitles: SideTitles(
                showTitles: true,
                textStyle: _tileTextStyle,
                reservedSize: 36, //Y轴与左边的距离
                getTitles: (value) {
                  return _getLeftNumTile(maxNum, value);
                })),
        borderData: FlBorderData(show: false),
        barGroups: _staticsBarData(),
        gridData: FlGridData(
            show: true,
            getDrawingHorizontalGridLine: _getDrawingGridLine,
            checkToShowHorizontalGrid: (value) {
              return _checkToShowHorizontalGrid(maxNum, value);
            })));
  }
}

class LineStaticsWidget extends StatefulWidget {
  final List<PractiseResult> staticsData;
  final CountType countType;

  LineStaticsWidget(this.staticsData, {this.countType = CountType.TIME});

  @override
  State<StatefulWidget> createState() {
    return _LineStaticsWidgetState();
  }
}

class _LineStaticsWidgetState extends State<LineStaticsWidget> {
  int maxNum = 0;
  int average;
  @override
  void initState() {
    super.initState();
    int sum = 0;
    widget.staticsData.forEach((result) {
      if (result.count > maxNum) {
        maxNum = result.count;
      }
      sum += result.count;
    });
    average = sum ~/ widget.staticsData.length;
  }

  List<LineChartBarData> _staticsLineData() {
    List<FlSpot> spots = List.generate(widget.staticsData.length, (idx) {
      return FlSpot(
          idx.toDouble(),
          widget.staticsData[idx].count /
              (widget.countType == CountType.TIME ? 1000 : 1));
    });
    return [
      LineChartBarData(
          spots: spots,
          isCurved: false,
          barWidth: 1.5,
          colors: [Colors.black26],
          dotData: FlDotData(
              show: true,
              dotColor: Colors.black54,
              dotSize: 4,
              checkToShowDot: (spot) {
                return spot.x != 0 && spot.x != widget.staticsData.length - 1;
              }))
    ];
  }

  @override
  Widget build(BuildContext context) {
    return LineChart(LineChartData(
        borderData: FlBorderData(
            show: true,
            border: Border(bottom: BorderSide(color: Colors.black))),
        lineTouchData: LineTouchData(
            getTouchedSpotIndicator: (barData, spotIdxes) {
              return spotIdxes.map((spotIdx) {
                final FlSpot spot = barData.spots[spotIdx];
                final PractiseResult result = widget.staticsData[spotIdx];
                if (spot.x == 0 || spot.x == widget.staticsData.length - 1) {
                  return null;
                } else {
                  return TouchedSpotIndicatorData(
                      FlLine(
                          color: result.correct
                              ? Colors.green[200]
                              : Colors.red[200],
                          strokeWidth: 2),
                      FlDotData(
                          dotSize: 4,
                          dotColor:
                              result.correct ? Colors.green : Colors.red));
                }
              }).toList();
            },
            touchTooltipData: LineTouchTooltipData(
                tooltipBgColor: Colors.blueGrey,
                getTooltipItems: (touchedBarSpots) {
                  return touchedBarSpots.map((barSpot) {
                    final PractiseResult result =
                        widget.staticsData[barSpot.barIndex];
                    if (barSpot.x == 0 ||
                        barSpot.x == widget.staticsData.length - 1) {
                      return null;
                    } else {
                      return LineTooltipItem(
                          widget.countType == CountType.TIME
                              ? S.of(context).statics_time_cost_correct(
                                  result.name,
                                  '${result.count / 1000}',
                                  '${result.correct}')
                              : S.of(context).statics_num_value(
                                  result.name, '${result.count}'),
                          TextStyle(
                              color:
                                  result.correct ? Colors.green : Colors.red));
                    }
                  }).toList();
                })),
        extraLinesData: ExtraLinesData(showVerticalLines: true, verticalLines: [
          VerticalLine(
              y: widget.countType == CountType.NUMBER
                  ? average.toDouble()
                  : average / 1000,
              color: Colors.lime.withOpacity(0.6),
              strokeWidth: 1.5)
        ]),
        lineBarsData: _staticsLineData(),
        gridData: FlGridData(
            show: true,
            drawHorizontalGrid: true,
            drawVerticalGrid: true,
            getDrawingHorizontalGridLine: _getDrawingGridLine,
            getDrawingVerticalGridLine: _getDrawingGridLine),
        titlesData: FlTitlesData(
            show: false,
            leftTitles: SideTitles(
                showTitles: true,
                getTitles: (value) {
                  if (widget.countType == CountType.TIME) {
                    return '${value}s';
                  } else {
                    return _getLeftNumTile(maxNum, value);
                  }
                },
                textStyle: _tileTextStyle),
            bottomTitles: SideTitles(
                showTitles: true,
                getTitles: (value) {
                  return widget.staticsData[value.toInt()].name;
                },
                textStyle: _tileTextStyle))));
  }
}
