package com.kaibo.flutter_plugin_test

import android.app.Activity
import android.os.Build
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPluginTestPlugin(private val mRegistrar: Registrar) : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_plugin_test")
            channel.setMethodCallHandler(FlutterPluginTestPlugin(registrar))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        val activity: Activity = mRegistrar.activity()
        when {
            call.method == "getPlatformVersion" -> {
                // 获取操作系统版本名
                result.success("Android ${Build.VERSION.RELEASE}")
            }
            call.method == "getBatteryLevel" -> {
                // 获取电池电量
                result.success(activity.getBatteryLevel())
            }
            call.method == "immersive" -> {
                // 设置沉浸式状态栏
                val isLight = call.argument("isLight") ?: false
                activity.immersive(isLight)
                result.success(null)
            }
            else -> result.notImplemented()
        }
    }
}
