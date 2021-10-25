package com.example.example_app

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.MODEL
import android.provider.Settings
import android.os.Build.VERSION_CODES



/** ExampleAppPlugin */
class ExampleAppPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "example_app")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {


    if (call.method == "getPlatformVersion") {
      val getInfo = getDeviceInfo()

      result.success(getInfo)
    } else {
      result.notImplemented()
    }
  }

  private fun getDeviceInfo(): String {

    val info: String
    val androidVersion = android.os.Build.VERSION.RELEASE
    val deviceModel = android.os.Build.MODEL
    // val id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    info = "Android" + androidVersion + "\n\nDevice model : " + deviceModel
    return info
  }
  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
