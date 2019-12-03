import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

typedef DrawerMenuTaped = void Function(int index, MenuInfo menuInfo);

class MenuInfo {
  final String title;
  final String icon;

  MenuInfo({this.title, this.icon});

  @override
  String toString() {
    return 'MenuInfo{title: $title, icon: $icon}';
  }
}

class HomeDrawer extends StatelessWidget {
  final DrawerMenuTaped _tapCallback;

  HomeDrawer(this._tapCallback) {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Home Drawer", screenClassOverride: "HomeDrawer");
  }

  List<MenuInfo> _fillMenus(BuildContext context) {
    return [
      MenuInfo(
          title: S.of(context).nav_menu_learn,
          icon: "images/ic_menu_learn.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_remember,
          icon: "images/ic_menu_remember.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_practise,
          icon: "images/ic_menu_practise.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_statics,
          icon: "images/ic_menu_statis.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_cheat, icon: "images/ic_menu_tips.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_share,
          icon: "images/ic_menu_share.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_feedback,
          icon: "images/ic_menu_feedback.svg"),
      MenuInfo(
          title: S.of(context).nav_menu_about, icon: "images/ic_menu_about.svg")
    ];
  }

  @override
  Widget build(BuildContext context) {
    List<MenuInfo> menus = _fillMenus(context);
    return Drawer(
        child: Column(
      children: <Widget>[
        UserAccountsDrawerHeader(
            accountName: Text(S.of(context).app_name),
            accountEmail: Text(S.of(context).nav_header_subtitle),
            currentAccountPicture: CircleAvatar(
                foregroundColor: Theme.of(context).iconTheme.color,
                radius: 24,
                backgroundImage: AssetImage("images/zidian.png")),
            otherAccountsPictures: <Widget>[
              FlutterLogo(size: 36, colors: Colors.lime)
            ]),
        MediaQuery.removePadding(
            context: context,
            removeTop: true,
            child: Expanded(
              child: ListView.separated(
                  itemCount: menus.length,
                  itemBuilder: (context, index) {
                    return ListTile(
                      contentPadding:
                          EdgeInsets.only(left: 16, top: 4, bottom: 4),
                      title: Text(menus[index].title,
                          style: TextStyle(
                              color: Theme.of(context).textTheme.title.color,
                              fontSize: 18)),
                      leading: SvgPicture.asset(menus[index].icon,
                          width: 28,
                          height: 28,
                          color: Theme.of(context).iconTheme.color),
                      onTap: () {
                        Navigator.pop(context); // close the drawer
                        print("Navigation Menu Selected: $index");
                        if (_tapCallback != null) {
                          _tapCallback(index, menus[index]);
//                          FireBase().analytics.logEvent(
//                              name: "Type_Drawer_Item",
//                              parameters: {menus[index].title: menus[index].toString()});
                        }
                      },
                    );
                  },
                  separatorBuilder: (context, index) {
                    if (index == 4 || index == 6) {
                      return Divider(height: 1);
                    } else {
                      return Divider(height: 0, color: Colors.transparent);
                    }
                  }),
            ))
      ],
    ));
  }
}
