import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:example_app/example_app.dart';

void main() {
  const MethodChannel channel = MethodChannel('example_app');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ExampleApp.platformVersion, '42');
  });
}
