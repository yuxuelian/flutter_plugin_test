import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginTest {
  static const MethodChannel _channel = const MethodChannel('flutter_plugin_test');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<int> get batteryLevel async {
    final int level = await _channel.invokeMethod('getBatteryLevel');
    return level;
  }

  static Future<void> immersive(bool isLight) async {
    await _channel.invokeMethod('immersive', {'isLight': isLight});
  }
}
