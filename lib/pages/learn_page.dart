import 'package:flutter/material.dart';
import 'package:ingress_assistant/data/data_module.dart';
import 'package:ingress_assistant/custom/glyph_widget.dart';
import 'package:ingress_assistant/custom/statics_widget.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';
import 'package:ingress_assistant/utils/tools.dart';

class LearnPage extends StatefulWidget {
  LearnPage({Key key}) : super(key: key) {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Learn Page", screenClassOverride: "LearnPage");
  }

  @override
  State<StatefulWidget> createState() {
    return _LearnBaePageState();
  }
}

class _LearnBaePageState extends State<LearnPage> {
  final Size _itemSize = Size(64, 64);
  List<GlyphInfo> _baseGlyphs;
  GlyphInfo _selected;

  Widget _buildBaseLearnHead() {
    List<Widget> columnWidgets = [
      //name
      Text(S.of(context).learn_glyph_name,
          textAlign: TextAlign.left,
          style: TextStyle(color: Colors.black, fontSize: 14)),
      Text(_selected?.name ?? "",
          style: TextStyle(
              color: Colors.red[400],
              fontSize: 18,
              fontWeight: FontWeight.bold)),
      SizedBox(height: 8),
      //Alisa
      Text(S.of(context).learn_glyph_alisa,
          style: TextStyle(color: Colors.black, fontSize: 14))
    ];
    if (_selected == null) {
      columnWidgets.add(Text(S.of(context).learn_glyph_alisa_empty,
          style: TextStyle(color: Colors.black87, fontSize: 14)));
    } else {
      if (_selected.aliasFull() == null) {
        columnWidgets.add(Text(S.of(context).learn_glyph_alisa_empty,
            style: TextStyle(color: Colors.black87, fontSize: 14)));
      } else {
        columnWidgets.add(Text(_selected.aliasFull(),
            style: TextStyle(color: Colors.red[200], fontSize: 14)));
      }
      columnWidgets.add(SizedBox(height: 8));
      columnWidgets.add(Container(
          width: 200,
          height: 100,
          child: FutureBuilder(
              future: DataModule().staticGlyphHistory(_selected.id),
              builder: (context, snapshot) {
                print("Get Glyph History:$snapshot");
                if (snapshot.connectionState == ConnectionState.done &&
                    snapshot.hasData) {
                  if ((snapshot.data as List<PractiseResult>).isNotEmpty) {
                    return LineStaticsWidget(snapshot.data);
                  }
                }
                return Text("");
              })));
    }
    return Row(crossAxisAlignment: CrossAxisAlignment.start, children: <Widget>[
      GlyphDemonstrateWidget(Size(200, 200), _selected?.path ?? [1]),
      Expanded(
          child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: columnWidgets))
    ]);
  }

  ///创建单个绘图对象
  Widget _buildBaseGridItem(GlyphInfo glyphInfo) {
    return GestureDetector(
      onTap: () {
        print("Item tapped: $glyphInfo");

//        FireBase().analytics.logViewItem(
//            itemId: glyphInfo.id.toString(),
//            itemName: glyphInfo.name,
//            itemCategory: "Base");

        _selected = glyphInfo;
        //更新学习次数
        _selected.learnCount = _selected.learnCount + 1;
        DataModule().updateGlyph(_selected);
        setState(() {});
      },
      behavior: HitTestBehavior.opaque,
      child: Column(
        children: <Widget>[
          GlyphSequenceWidget(_itemSize, glyphInfo.path),
          SizedBox(height: 2),
          Text(glyphInfo.name, style: TextStyle(fontSize: 12))
        ],
      ),
    );
  }

  @override
  void didUpdateWidget(LearnPage oldWidget) {
    super.didUpdateWidget(oldWidget);
    _selected = null;
  }

  @override
  Widget build(BuildContext context) {
    if (_baseGlyphs == null) {
      return FutureBuilder(
          future: DataModule().getAllGlyphs(),
          builder: (context, snapshot) {
            print("Get BaseGlyph:$snapshot");
            if (snapshot.connectionState == ConnectionState.done &&
                snapshot.hasData) {
              _baseGlyphs = snapshot.data;
              return _buildDataDoneWidget(_baseGlyphs.length, 5);
            } else {
              return _buildDataWaitingWidget();
            }
          });
    } else {
      return _buildDataDoneWidget(_baseGlyphs.length, 5);
    }
  }

  Widget _buildDataWaitingWidget() {
    return Center(child: Text(S.of(context).splash_loading));
  }

  Widget _buildDataDoneWidget(int itemCount, int columns) {
    MediaQueryData queryData = MediaQuery.of(context);
    return Container(
      width: queryData.size.width,
      height: queryData.size.height,
      padding: EdgeInsets.only(top: 8),
      child: Column(children: <Widget>[
        _buildBaseLearnHead(),
        Padding(
            padding: EdgeInsets.only(top: 8),
            child: Divider(color: Colors.grey, height: 1)),
        Expanded(
            child: Scrollbar(
                child: GridView.builder(
                    padding: EdgeInsets.only(top: 4),
                    itemCount: itemCount,
                    gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: columns,
                        mainAxisSpacing: 2,
                        crossAxisSpacing: 2,
                        childAspectRatio: 0.9),
                    itemBuilder: (context, index) {
                      return _buildBaseGridItem(_baseGlyphs[index]);
                    })))
      ]),
    );
  }
}
