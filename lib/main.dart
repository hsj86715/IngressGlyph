import 'dart:async';
import 'package:flutter/material.dart';
import 'package:ingress_assistant/configs/routes_define.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';
//import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() {
  // Set `enableInDevMode` to true to see reports while in debug mode
  // This is only to be used for confirming that reports are being
  // submitted as expected. It is not intended to be used for everyday
  // development.
//  Crashlytics.instance.enableInDevMode = true;

  // Pass all uncaught errors from the framework to Crashlytics.
//  FlutterError.onError = Crashlytics.instance.recordFlutterError;
//  //The AdMob plugin must be initialized with an AdMob App ID.
//  FirebaseAdMob.instance.initialize(appId: FireBase().adModAPPId);
//  runZoned<Future<void>>(() async {
    runApp(MyApp());
//  }, onError: Crashlytics.instance.recordError);
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: [
        S.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate
      ],
//      showPerformanceOverlay: true,
      debugShowCheckedModeBanner: false,
      supportedLocales: S.delegate.supportedLocales,
//      localeResolutionCallback:
//          S.delegate.resolution(fallback: const Locale('en', ''),withCountry: false),
//      localeListResolutionCallback:
//          S.delegate.listResolution(fallback: const Locale('en', ''),withCountry: false),
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      onGenerateRoute: RouterTool().router.generator,
    );
  }
}
