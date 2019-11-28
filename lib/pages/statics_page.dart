import 'package:flutter/material.dart';
import 'package:ingress_assistant/data/data_module.dart';
import 'package:ingress_assistant/custom/statics_widget.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class StaticsPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _StaticsPageState();
  }

  StaticsPage() {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Statics Page", screenClassOverride: "StaticsPage");
  }
}

class _StaticsPageState extends State<StaticsPage> {
  Widget _placeHolder(String text) {
    MediaQueryData queryData = MediaQuery.of(context);
    return Container(
        alignment: Alignment.center,
        width: queryData.size.width,
        height: 300,
        child:
            Text(text, style: TextStyle(fontSize: 14, color: Colors.black54)));
  }

  @override
  Widget build(BuildContext context) {
    TextStyle textStyle = TextStyle(
        fontSize: 20, fontWeight: FontWeight.bold, color: Colors.black);
    return ListView(children: <Widget>[
      Center(
          child: Text(S.of(context).statics_glyph_popular, style: textStyle)),
      FutureBuilder(
          future: DataModule().staticsPopularGlyphs(limit: 10),
          builder: (context, snapshot) {
            print('Glyph Learn: $snapshot');
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.data == null ||
                  (snapshot.data as List<CountResult>).isEmpty) {
                return _placeHolder(S.of(context).no_data);
              } else {
                return PieStaticsWidget(snapshot.data);
              }
            } else {
              return _placeHolder("${S.of(context).splash_loading}...");
            }
          }),
      Divider(height: 1, color: Colors.black87),
      SizedBox(height: 8),
      Center(
          child: Text(S.of(context).statics_sequence_level, style: textStyle)),
      FutureBuilder(
          future: DataModule().staticsSequencePractise(),
          builder: (context, snapshot) {
            print('Hack Level: $snapshot');
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.data == null ||
                  (snapshot.data as List<PractiseCountResult>).isEmpty) {
                return _placeHolder(S.of(context).no_data);
              } else {
                return GroupBarStaticsWidget(snapshot.data);
              }
            } else {
              return _placeHolder("${S.of(context).splash_loading}...");
            }
          }),
      Divider(height: 1, color: Colors.black87),
      SizedBox(height: 8),
      Center(child: Text(S.of(context).statics_wrong_most, style: textStyle)),
      FutureBuilder(
          future: DataModule().staticsGlyphWrongMost(),
          builder: (context, snapshot) {
            print('Glyph wrong most: $snapshot');
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.data == null ||
                  (snapshot.data as List<CountResult>).isEmpty) {
                return _placeHolder(S.of(context).no_data);
              } else {
                return PieStaticsWidget(snapshot.data);
              }
            } else {
              return _placeHolder("${S.of(context).splash_loading}...");
            }
          }),
      Divider(height: 1, color: Colors.black87),
      SizedBox(height: 8),
      Center(child: Text(S.of(context).statics_slowest_top, style: textStyle)),
      FutureBuilder(
          future: DataModule().staticsGlyphSlowest(),
          builder: (context, snapshot) {
            print('Glyph slowest: $snapshot');
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.data == null ||
                  (snapshot.data as List<CountResult>).isEmpty) {
                return _placeHolder(S.of(context).no_data);
              } else {
                return BarStaticsWidget(snapshot.data,
                    countType: CountType.TIME);
              }
            } else {
              return _placeHolder("${S.of(context).splash_loading}...");
            }
          })
    ]);
  }
}
