import 'package:flutter/material.dart';
import 'package:ingress_assistant/data/data_module.dart';
import 'package:ingress_assistant/configs/routes_define.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:package_info/package_info.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class SplashScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _SplashScreenState();
  }
}

class _SplashScreenState extends State<SplashScreen>
    with TickerProviderStateMixin {
  AnimationController _animationController;
//  bool _cloudJump = false;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
        vsync: this, upperBound: 6, duration: Duration(milliseconds: 500));
    _animationController.addStatusListener((status) {
      if (status == AnimationStatus.completed ||
          status == AnimationStatus.dismissed) {
//        if (_cloudJump) {
//          RouterTool().navigationTo(RouterTool.HOME, context, clearStack: true);
//        } else {
        _animationController.repeat();
//        }
      }
    });
    _animationController.forward();

    DataModule dataModule = DataModule();
    dataModule.initBaseData().whenComplete(() {
      print("Database Init completed");
//      FireBase().analytics.logAppOpen();
//      _cloudJump = true;
      RouterTool().navigationTo(RouterTool.HOME, context, clearStack: true);
    });
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    MediaQueryData mediaQueryData = MediaQuery.of(context);
    return Scaffold(
        body: Stack(children: <Widget>[
      Container(
          decoration: BoxDecoration(
              gradient: LinearGradient(
                  colors: [Colors.green[300], Colors.blue[300]],
                  begin: Alignment.centerRight,
                  end: Alignment.centerLeft))),
      Column(children: <Widget>[
        Expanded(
            child: Container(
          padding: EdgeInsets.only(top: mediaQueryData.padding.top, right: 16),
          alignment: Alignment.topRight,
          child: FutureBuilder(
              future: PackageInfo.fromPlatform(),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.done &&
                    snapshot.hasData) {
                  return Text("v${snapshot.data.version}",
                      style:
                          TextStyle(color: Colors.blueGrey[400], fontSize: 16));
                } else {
                  return Text("v1.0.0",
                      style:
                          TextStyle(color: Colors.blueGrey[400], fontSize: 16));
                }
              }),
        )),
        Row(children: <Widget>[
          Expanded(
              child: Container(
                  alignment: Alignment.centerRight,
                  child: Image.asset("images/enlightened_alt.png",
                      width: 120, height: 160))),
          Expanded(
              child: Container(
                  alignment: Alignment.centerLeft,
                  child: Image.asset("images/resistance_alt.png",
                      width: 120, height: 160)))
        ]),
        Expanded(
            child: Container(
                padding: EdgeInsets.all(16),
                alignment: Alignment.topCenter,
                child: AnimatedBuilder(
                    animation: CurvedAnimation(
                        parent: _animationController, curve: Curves.decelerate),
                    builder: (context, widget) {
                      var aniValue = _animationController.value;
                      var str = S.of(context).splash_loading;
                      if (aniValue >= 0 && aniValue < 1) {
                        str = S.of(context).splash_loading;
                      } else if (aniValue >= 1 && aniValue < 2) {
                        str = ".${S.of(context).splash_loading}.";
                      } else if (aniValue >= 2 && aniValue < 3) {
                        str = "..${S.of(context).splash_loading}..";
                      } else if (aniValue >= 3 && aniValue < 4) {
                        str = "...${S.of(context).splash_loading}...";
                      } else if (aniValue >= 4 && aniValue < 5) {
                        str = "....${S.of(context).splash_loading}....";
                      } else if (aniValue >= 5 && aniValue < 6) {
                        str = ".....${S.of(context).splash_loading}.....";
                      } else if (aniValue >= 6) {
                        str = "......${S.of(context).splash_loading}......";
                      }
                      return Text(str,
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 24,
                              shadows: [
                                Shadow(
                                    color: Colors.grey,
                                    offset: Offset(1, 2),
                                    blurRadius: 1.5)
                              ]));
                    })))
      ])
    ]));
  }
}
