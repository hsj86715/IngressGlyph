import 'package:flutter/material.dart';
import 'package:ingress_assistant/data/data_module.dart';
import 'package:ingress_assistant/custom/glyph_widget.dart';
import 'package:ingress_assistant/custom/statics_widget.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/tools.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class PractisePage extends StatefulWidget {
  final String actionMenu;
  final GlobalKey<ScaffoldState> scaffoldKey;

  PractisePage(this.actionMenu, this.scaffoldKey, {Key key}) : super(key: key) {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Practise Page", screenClassOverride: "PractisePage");
  }

  @override
  State<StatefulWidget> createState() {
    return _PractisePageState();
  }
}

class _PractisePageState extends State<PractisePage> {
  final Size _itemSize = Size(80, 80);
  final Map<int, bool> _hackResult = Map(); //记录序列中练习的结果
  final Map<int, int> _hackTimeCost = Map(); //记录序列中练习所花费的时间
  final GlobalKey<HackListPractiseWidgetState> _practiseKey = GlobalKey();
  final TextEditingController _editingController = TextEditingController();
  int currentDataLength = 2;
  List<HackSequence> _hackList;
  int _hackListIdx = 0;
  bool _showShorthand = false;
  bool _editSentence = false;

  ///已经练习的下标从-1开始，方便比较将已经练习的和未练习的区分开来
  int _practisedIdx = -1;
  DateTime _glyphStart;
  DateTime _hackListStart;

  @override
  void didUpdateWidget(PractisePage oldWidget) {
    super.didUpdateWidget(oldWidget);
    _hackListIdx = 0;
    _practisedIdx = -1;
    _hackResult.clear();
  }

  @override
  Widget build(BuildContext context) {
    if (_hackList != null &&
        _hackList.isNotEmpty &&
        currentDataLength == level2Length(widget.actionMenu)) {
      return _buildDataDoneWidget();
    } else {
      return FutureBuilder(
          future: DataModule().getHackList(widget.actionMenu),
          builder: (context, snapshot) {
            print(
                "Get HackList, level: ${widget.actionMenu}, result: $snapshot");
            if (snapshot.connectionState == ConnectionState.done &&
                snapshot.hasData) {
              currentDataLength = level2Length(widget.actionMenu);
              _hackList = snapshot.data;
              //打乱顺序
//              if (level == RANDOM) {
//                _hackList.shuffle();
//              }
              return _buildDataDoneWidget();
            } else {
              return _buildDataWaitingWidget();
            }
          });
    }
  }

  ///单个Glyph练习开始
  void _glyphPractiseStart() {
    _glyphStart = DateTime.now();
  }

  ///单个Glyph练习结束
  void _glyphPractiseEnd(bool correct, int practised) {
    _practisedIdx = practised;
    _hackResult[practised] = correct;
    //计算单个Glyph练习耗时
    Duration duration = DateTime.now().difference(_glyphStart);
    print("单个Glyph耗时：${duration.toString()}");
    _hackTimeCost[practised] = duration.inMilliseconds;
    GlyphInfo currGlyph = _hackList[_hackListIdx].sequences[practised];
    currGlyph.practiseCount++;
    if (correct) {
      currGlyph.practiseCorrect++;
      if (currGlyph.practiseBest == null ||
          currGlyph.practiseBest > duration.inMilliseconds) {
        currGlyph.practiseBest = duration.inMilliseconds;
      }
    }
    //更新Glyph的练习数据
    DataModule().updateGlyph(currGlyph);
    DataModule()
        .insertGlyphCost(currGlyph.id, duration.inMilliseconds, correct);
    setState(() {});
  }

  ///整个HackList练习开始
  void _hackListPractiseStart() {
    _hackListStart = DateTime.now();
  }

  ///整个HackList练习结束
  void _hackListPractiseEnd() {
    //计算整个Sequence练习耗时
    Duration duration = DateTime.now().difference(_hackListStart);
    print("整个HackList练习耗时：${duration.toString()}");
    HackSequence currHack = _hackList[_hackListIdx];
    currHack.practiseCount++;
    bool correct = false;
    if (!_hackResult.containsValue(false)) {
      correct = true;
      currHack.practiseCorrect++;
      if (currHack.practiseBest == null ||
          duration.inMilliseconds < currHack.practiseBest) {
        currHack.practiseBest = duration.inMilliseconds;
      }
    }
    //更新Sequence的练习数据
    DataModule().updateSequence(currHack);
    DataModule()
        .insertSequenceCost(currHack.id, duration.inMilliseconds, correct);
  }

  ///切换上一个
  void _switchPrevious() {
    if (_hackListIdx > 0) {
      //切换上一个
      _hackListIdx--;
      _practisedIdx = -1;
      _editSentence = false;
      _showShorthand = false;

      setState(() {
        _practiseKey.currentState.resetStep();
      });

//                    FireBase().analytics.logViewItem(
//                        itemId: _hackList[_hackListIdx].id.toString(),
//                        itemName: _hackList[_hackListIdx].head.name,
//                        itemCategory: _hackList[_hackListIdx].level);
    } else {
      widget.scaffoldKey.currentState.showSnackBar(
          SnackBar(content: Text(S.of(context).toast_practise_list_first)));
//                    FireBase().analytics.logEvent(name: "Practise_First");
    }
  }

  ///切换下一个
  void _switchNext() {
    if (_hackListIdx < _hackList.length - 1) {
      //切换下一个
      _hackListIdx++;
      _practisedIdx = -1;
      _editSentence = false;
      _showShorthand = false;
      setState(() {
        _practiseKey.currentState.resetStep();
      });

//                    FireBase().analytics.logViewItem(
//                        itemId: _hackList[_hackListIdx].id.toString(),
//                        itemName: _hackList[_hackListIdx].head.name,
//                        itemCategory: _hackList[_hackListIdx].level);
    } else {
      widget.scaffoldKey.currentState.showSnackBar(
          SnackBar(content: Text(S.of(context).toast_practise_list_end)));
//                    FireBase().analytics.logEvent(name: "Practise_End");
    }
  }

  Widget _buildDataDoneWidget() {
    MediaQueryData queryData = MediaQuery.of(context);
    return Column(
      children: <Widget>[
        HackListPractiseWidget(
            _hackList[_hackListIdx], Size.square(queryData.size.width * 0.9),
            key: _practiseKey,
            glyphPractiseStart: _glyphPractiseStart,
            glyphPractiseEnd: _glyphPractiseEnd,
            hackListPractiseStart: _hackListPractiseStart,
            hackListPractiseEnd: _hackListPractiseEnd),
        SizedBox(height: 8),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            IconButton(
                icon: Icon(Icons.chevron_left,
                    color: _hackListIdx == 0 ? Colors.red : Colors.green),
                iconSize: 36,
                onPressed: _switchPrevious),
            Text(S.of(context).practise_switch_hackList,
                style: TextStyle(fontSize: 16)),
            IconButton(
                icon: Icon(Icons.chevron_right,
                    color: _hackListIdx == _hackList.length - 1
                        ? Colors.red
                        : Colors.green),
                iconSize: 36,
                onPressed: _switchNext)
          ],
        ),
        SizedBox(height: 16),
//        _buildShorthandTips(_hackList[_hackListIdx]),
//        SizedBox(height: 8),
        _buildResultHackList(_hackList[_hackListIdx]),
        Container(
            width: queryData.size.width,
            height: 100,
            padding: EdgeInsets.all(8),
            child: FutureBuilder(
                future: DataModule()
                    .staticSequenceHistory(_hackList[_hackListIdx].id),
                builder: (context, snapshot) {
                  print("Get Sequence History:$snapshot");
                  if (snapshot.connectionState == ConnectionState.done &&
                      snapshot.hasData) {
                    if ((snapshot.data as List<PractiseResult>).isNotEmpty) {
                      return LineStaticsWidget(snapshot.data);
                    }
                  }
                  return Text("");
                }))
      ],
    );
  }

  Widget _buildDataWaitingWidget() {
    return Center(child: Text(S.of(context).splash_loading));
  }

  Widget _buildGlyphInfoItem(GlyphInfo glyphInfo, int idx) {
//    print("idx:$idx,_practisedIdx:$_practisedIdx");
    bool drawContent = idx <= _practisedIdx; //已经练习过的绘制出正确路径
    if (_hackResult[idx] != null) {
      return Column(
        children: <Widget>[
          GlyphSequenceWidget(
            _itemSize,
            glyphInfo.path,
            drawSeq: drawContent,
            seqPathColor: _hackResult[idx]
                ? Colors.greenAccent[400]
                : Colors.redAccent[400],
            timeCost: _hackTimeCost[idx] == null
                ? ''
                : '${_hackTimeCost[idx] / 1000}s',
          ),
          SizedBox(height: 2),
          Text(glyphInfo.name,
              style: TextStyle(
                  fontSize: 14,
                  color: drawContent
                      ? (_hackResult[idx]
                          ? Colors.greenAccent[400]
                          : Colors.redAccent[400])
                      : Colors.transparent))
        ],
      );
    } else {
      return Column(
        children: <Widget>[
          GlyphSequenceWidget(_itemSize, glyphInfo.path, drawSeq: drawContent),
          SizedBox(height: 2),
          Text(glyphInfo.name,
              style: TextStyle(
                  fontSize: 14,
                  color: drawContent ? Colors.black : Colors.transparent))
        ],
      );
    }
  }

  Widget _buildResultHackList(HackSequence hackList) {
    List<Widget> hackItems = List();
    for (int i = 0; i < hackList.sequences.length; i++) {
      hackItems.add(_buildGlyphInfoItem(hackList.sequences[i], i));
    }
    return Padding(
        padding: EdgeInsets.only(top: 2, bottom: 2),
        child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: hackItems));
  }

  ///速记口诀
  Widget _buildShorthandTips(HackSequence hackSequence) {
    print("buildRememberSentence: $hackSequence");
    if (_showShorthand) {
      if (hackSequence.sentence != null &&
          hackSequence.sentence.isNotEmpty &&
          !_editSentence) {
        return Padding(
            padding: EdgeInsets.symmetric(horizontal: 8),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(hackSequence.sentence),
                SizedBox(width: 4),
                IconButton(
                    icon: Icon(Icons.edit),
                    onPressed: () {
                      _editSentence = true;
                      setState(() {});
                    })
              ],
            ));
      } else {
        return Padding(
            padding: EdgeInsets.symmetric(horizontal: 8),
            child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                      child: TextField(
                    controller: _editingController,
                    decoration: InputDecoration(
                        border: UnderlineInputBorder(),
                        filled: true,
                        labelText: S.of(context).practise_shorthand_label,
                        hintText: S.of(context).practise_shorthand_tips_hint)
                  )),
                  SizedBox(width: 4),
                  IconButton(
                      icon: Icon(Icons.done),
                      onPressed: () {
                        String inputTx = _editingController.text;
                        if (inputTx == null || inputTx.isEmpty) {
//                          _editingController.
                        } else {
                          _editSentence = false;
                          DataModule()
                              .updateSequenceSentence(
                                  hackSequence.id, _editingController.text)
                              .then((value) {
                            DataModule()
                                .getHackList(widget.actionMenu)
                                .then((value) {
                              _hackList = value;
                              setState(() {});
                            });
                          });
                        }
                      })
                ]));
      }
    } else {
      return IconButton(
          icon: Icon(Icons.remove_red_eye),
          onPressed: () {
            _showShorthand = true;
            setState(() {
              _editingController.text = hackSequence.sentence;
            });
          });
    }
  }
}
