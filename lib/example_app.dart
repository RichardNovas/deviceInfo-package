
import 'dart:async';

import 'package:flutter/services.dart';

class ExampleApp {
  static const MethodChannel _channel = MethodChannel('example_app');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
