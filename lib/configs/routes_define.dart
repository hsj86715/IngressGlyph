import 'package:flutter/material.dart';
import 'package:fluro/fluro.dart';
import 'package:ingress_assistant/pages/splash_page.dart';
import 'package:ingress_assistant/pages/home_page.dart';
import 'package:ingress_assistant/pages/about_page.dart';

class RouterTool {
  static const String ROOT = "/";
  static const String HOME = "/home";
  static const String ABOUT = "/about";

  static RouterTool _instance;
  Router _router;

  static RouterTool _routerTool() {
    if (_instance == null) {
      _instance = RouterTool._();
    }
    return _instance;
  }

  factory RouterTool() => _routerTool();

  RouterTool._() {
    _router = Router();
    _defineRouters();
  }

  void _defineRouters() {
    _router.define(ROOT, handler: Handler(handlerFunc: (context, parameters) {
      return SplashScreen();
    }));
    _router.define(HOME, handler: Handler(handlerFunc: (context, parameters) {
      return HomePage();
    }));
    _router.define(ABOUT, handler: Handler(handlerFunc: (context, parameters) {
      return AboutPage();
    }));
  }

  Router get router => _router;

  Future navigationTo(String pageName, BuildContext context,
      {bool replace = false,
      bool clearStack = false,
      TransitionType transition,
      Duration transitionDuration = const Duration(milliseconds: 250),
      RouteTransitionsBuilder transitionBuilder}) {
    return _router.navigateTo(context, pageName,
        replace: replace,
        clearStack: clearStack,
        transition: transition,
        transitionDuration: transitionDuration,
        transitionBuilder: transitionBuilder);
  }

  bool pop(BuildContext context) {
    return _router.pop(context);
  }
}
