import 'package:ingress_assistant/data/data_module.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:flutter_share/flutter_share.dart';
import 'package:ingress_assistant/pages/home_page_drawer.dart';
import 'package:ingress_assistant/pages/learn_page.dart';
import 'package:ingress_assistant/pages/remember_page.dart';
import 'package:ingress_assistant/pages/practise_page.dart';
import 'package:ingress_assistant/pages/statics_page.dart';
import 'package:ingress_assistant/pages/cheat_page.dart';
import 'package:ingress_assistant/configs/routes_define.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/tools.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _navMenuTappedIdx = 0;
  Map<int, String> _pageActionMenuIdx = {0: L234};
  String _subTitle;

//  InterstitialAd _interstitialAd = InterstitialAd(
//      adUnitId: FireBase().adModUnitId(AdUnit.Interstitial),
//      listener: (event) {
//        FireBase().analytics.logEvent(name: "Tap_AD");
//      });

  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();
  final GlobalKey<RememberPageState> _rememberKey =
      GlobalKey<RememberPageState>();

  void _onDrawerMenuTapped(int index, MenuInfo menuInfo) {
    print(menuInfo);
    if (index < 5) {
      _subTitle = menuInfo.title;
      if (_navMenuTappedIdx != index) {
        _navMenuTappedIdx = index; //页面标记
        //初始化页面菜单选中项
        if (!_pageActionMenuIdx.containsKey(_navMenuTappedIdx)) {
          _pageActionMenuIdx[_navMenuTappedIdx] = L234;
        }
        setState(() {});
//        _interstitialAd
//          ..load()
//          ..show(anchorType: AnchorType.bottom);
      }
    } else {
      if (index == 7) {
        RouterTool().navigationTo(RouterTool.ABOUT, context);
      } else if (index == 6) {
        _gotoGooglePlayPage();
      } else if (index == 5) {
        FlutterShare.share(
            title: S.of(context).nav_menu_share,
            text: S.of(context).nav_share_text);
      }
    }
  }

  void _gotoGooglePlayPage() {
    const url =
        "https://play.google.com/store/apps/details?id=cn.com.farmcode.ingress.sequence";
    canLaunch(url).then((success) {
      if (success) {
        launch(url).then((suc) {}, onError: (error) {
          _scaffoldKey.currentState.showSnackBar(
              SnackBar(content: Text(S.of(context).toast_feedback_no_app)));
        });
      }
    }, onError: (error) {
      print("Could not launch $url,Error: $error");
      _scaffoldKey.currentState.showSnackBar(
          SnackBar(content: Text(S.of(context).toast_feedback_no_app)));
    });
  }

  List<PopupMenuEntry<String>> _buildActionMenus() {
    List<PopupMenuEntry<String>> menus = [
      PopupMenuItem<String>(value: L234, child: Text(L234)),
      PopupMenuItem<String>(value: L56, child: Text(L56)),
      PopupMenuItem<String>(value: L7, child: Text(L7)),
      PopupMenuItem<String>(value: L8, child: Text(L8))
    ];
    if (_navMenuTappedIdx == 1) {
      menus.add(PopupMenuItem<String>(
          value: ALL, child: Text(S.of(context).option_menu_hack_all)));
    } else if (_navMenuTappedIdx == 2) {
      menus.insert(0, PopupMenuItem<String>(value: L01, child: Text(L01)));
    }
    return menus;
  }

  AppBar _buildAppBar() {
    List<Widget> actions;
    if (_navMenuTappedIdx == 1 || _navMenuTappedIdx == 2) {
      actions = [
        PopupMenuButton<String>(
          itemBuilder: (context) {
            return _buildActionMenus();
          },
          onSelected: (String menuValue) {
            print("Option Menu item: $menuValue seclcted");
//            FireBase()
//                .analytics
//                .logViewItemList(itemCategory: "$_subTitle&Option$menuValue");
            if (_navMenuTappedIdx == 1) {
              _pageActionMenuIdx[_navMenuTappedIdx] = menuValue;
              _rememberKey.currentState.handleActionLevel(menuValue);
            } else {
              if (_pageActionMenuIdx[_navMenuTappedIdx] != menuValue) {
                _pageActionMenuIdx[_navMenuTappedIdx] = menuValue;
                setState(() {});
              }
            }
          },
        )
      ];
    }
    if (_navMenuTappedIdx == 1) {
      actions.insert(
          0,
          IconButton(
              icon: Icon(Icons.search,
                  size: 24, color: Colors.white),
              onPressed: () {
                _rememberKey.currentState.handleActionSearch();
              }));
    }
    return AppBar(
      elevation: 5,
      leading: IconButton(
          icon: Icon(Icons.menu, color: Colors.white),
          onPressed: () {
            _scaffoldKey.currentState.openDrawer();
          }),
      title: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.end,
        children: <Widget>[
          Text(S.of(context).app_name,
              style: Theme.of(context)
                  .textTheme
                  .title
                  .copyWith(color: Colors.white)),
          SizedBox(width: 4),
          Text(_subTitle == null ? S.of(context).nav_menu_learn : _subTitle,
              style: Theme.of(context)
                  .textTheme
                  .subtitle
                  .copyWith(color: Colors.white54))
        ],
      ),
      actions: actions,
    );
  }

  Widget _switchBody() {
    print("_switchBody: $_navMenuTappedIdx");
    switch (_navMenuTappedIdx) {
      case 1:
        return RememberPage(key: _rememberKey);
      case 2:
        return PractisePage(
            _pageActionMenuIdx[_navMenuTappedIdx], _scaffoldKey);
      case 3:
        return StaticsPage();
      case 4:
        return CheatPage();
      case 0:
      default:
        return LearnPage();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: _buildAppBar(),
      drawer: HomeDrawer(_onDrawerMenuTapped),
      body: _switchBody(),
    );
  }

  @override
  void dispose() {
    DataModule().dispose();
    super.dispose();
  }
}
