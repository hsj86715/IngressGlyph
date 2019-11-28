//import 'dart:io';
//import 'package:firebase_analytics/firebase_analytics.dart';
//import 'package:firebase_analytics/observer.dart';
//import 'package:firebase_admob/firebase_admob.dart';
//export 'package:firebase_admob/firebase_admob.dart';
//
//enum AdUnit { Banner, Interstitial, Rewarded }
/////https://blog.csdn.net/yumi0629/article/details/83007315
//const bool isProduction = const bool.fromEnvironment("dart.vm.product");
//
//class FireBase {
//  static FireBase _instance;
//  FirebaseAnalytics _analytics;
//  FirebaseAnalyticsObserver _observer;
//  String _adModAPPId;
//
//  FireBase._() {
//    _analytics = FirebaseAnalytics();
//    _observer = FirebaseAnalyticsObserver(analytics: _analytics);
//    if (Platform.isIOS) {
//      _adModAPPId = "ca-app-pub-2605060010137521~4876022586";
//    } else if (Platform.isAndroid) {
//      _adModAPPId = "ca-app-pub-2605060010137521~7528581292";
//    }
//  }
//
//  static FireBase _fireBase() {
//    if (_instance == null) {
//      _instance = FireBase._();
//    }
//    return _instance;
//  }
//
//  factory FireBase() => _fireBase();
//
//  FirebaseAnalyticsObserver get observer => _observer;
//
//  FirebaseAnalytics get analytics => _analytics;
//
//  String get adModAPPId => _adModAPPId;
//
//  String adModUnitId(AdUnit type, {bool debug = !isProduction}) {
//    switch (type) {
//      case AdUnit.Banner:
//        return debug
//            ? BannerAd.testAdUnitId
//            : (Platform.isAndroid
//                ? "ca-app-pub-2605060010137521/8109293223"
//                : (Platform.isIOS
//                    ? "ca-app-pub-2605060010137521/4118077322"
//                    : ""));
//      case AdUnit.Interstitial:
//        return debug
//            ? InterstitialAd.testAdUnitId
//            : (Platform.isAndroid
//                ? "ca-app-pub-2605060010137521/3978955456"
//                : (Platform.isIOS
//                    ? "ca-app-pub-2605060010137521/7698708613"
//                    : ""));
//      case AdUnit.Rewarded:
//        return debug
//            ? RewardedVideoAd.testAdUnitId
//            : (Platform.isAndroid
//                ? "ca-app-pub-2605060010137521/6238626591"
//                : (Platform.isIOS
//                    ? "ca-app-pub-2605060010137521/9603156532"
//                    : ""));
//      default:
//        return "";
//    }
//  }
//}
