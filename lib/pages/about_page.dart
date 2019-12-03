import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:package_info/package_info.dart';
import 'package:url_launcher/url_launcher.dart';

class AboutPage extends StatelessWidget {
  final Map<String, String> osps = {
    "IconFont": "https://www.iconfont.cn",
    "sqflite": "https://github.com/tekartik/sqflite",
    "sqlcool": "https://github.com/synw/sqlcool",
    "fluro": "https://github.com/theyakka/fluro",
    "flutter_svg": "https://github.com/dnfield/flutter_svg",
    "package_info":
        "https://github.com/flutter/plugins/tree/master/packages/package_info",
    "url_launcher":
        "https://github.com/flutter/plugins/tree/master/packages/url_launcher/url_launcher",
    "flutter_share": "https://github.com/lubritto/flutter_share",
    "fl_chart": "https://github.com/imaNNeoFighT/fl_chart",
    "i18n": "https://plugins.jetbrains.com/plugin/10128-flutter-i18n/"
  };

  void _jumpUrl(String url) {
    canLaunch(url).then((success) {
      if (success) {
        launch(url).then((suc) {
          print("Launch $url, result: $suc");
        }, onError: (error) {
          print("Launch $url, fail; Error: $error");
        });
      }
    }, onError: (error) {
      print("Could not launch $url,Error: $error");
    });
  }

  List<TableRow> _buildOSPRows(context) {
    List<TableRow> rows = [
      TableRow(decoration: BoxDecoration(color: Colors.blue[50]), children: [
        Container(
            height: 30,
            alignment: Alignment.center,
            child: Text(S.of(context).about_tks_osp_name,
                style: TextStyle(
                    fontWeight: FontWeight.bold, color: Colors.black54))),
        Container(
            height: 30,
            alignment: Alignment.center,
            child: Text(S.of(context).about_tks_ops_website,
                style: TextStyle(
                    fontWeight: FontWeight.bold, color: Colors.black54)))
      ])
    ];
    osps.forEach((name, website) {
      rows.add(TableRow(children: [
        Container(
            padding: EdgeInsets.all(4),
            alignment: Alignment.centerLeft,
            child: Text(name)),
        Container(
            padding: EdgeInsets.all(4),
            alignment: Alignment.centerLeft,
            child: Text.rich(TextSpan(
                text: website,
                recognizer: TapGestureRecognizer()
                  ..onTap = () {
                    _jumpUrl(website);
                  },
                style: TextStyle(
                    color: Colors.blue, decoration: TextDecoration.underline))))
      ]));
    });
    return rows;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            elevation: 5,
            leading: IconButton(
                icon: Icon(Icons.arrow_back),
                onPressed: () {
                  Navigator.pop(context);
                }),
            title: Text(S.of(context).nav_menu_about,
                style: Theme.of(context)
                    .textTheme
                    .title
                    .copyWith(color: Colors.white))),
        body: Container(
            padding: EdgeInsets.all(16),
            child: Column(children: <Widget>[
              Text(S.of(context).app_name,
                  style: TextStyle(
                      fontSize: 24, color: Theme.of(context).accentColor)),
              SizedBox(height: 8),
              FutureBuilder(
                  future: PackageInfo.fromPlatform(),
                  builder: (context, snapshot) {
                    if (snapshot.connectionState == ConnectionState.done &&
                        snapshot.hasData) {
                      return Text("v${snapshot.data.version}");
                    } else {
                      return Text("v1.0.0");
                    }
                  }),
              SizedBox(height: 16),
              Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(S.of(context).about_app_subject),
                    Text(S.of(context).about_app_detail),
                    SizedBox(height: 4),
                    Text(S.of(context).about_tks_list),
                    Table(
                        columnWidths: const {
                          0: FlexColumnWidth(1),
                          1: FlexColumnWidth(3)
                        },
                        border: TableBorder.all(
                            color: Colors.grey,
                            width: 1,
                            style: BorderStyle.solid),
                        defaultVerticalAlignment:
                            TableCellVerticalAlignment.top,
                        children: _buildOSPRows(context))
                  ])
            ])));
  }
}
