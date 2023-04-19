package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findAllConstructors
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookReplace
import com.github.kyuubiran.ezxhelper.utils.invokeMethod
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.github.kyuubiran.ezxhelper.utils.paramCount
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.XSharedPreferences

object MiuiStylusDeviceListener : HookRegister() {
    override fun init() = hasEnable("remove_stylus_bluetooth_restriction") {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val driverVersion =
                XSharedPreferences.getString("remove_stylus_bluetooth_restriction_driver_version", "2").toInt()
            findAllConstructors("com.miui.server.input.stylus.MiuiStylusDeviceListener") { true }.hookAfter {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(0, 20, 0x10 or driverVersion) {
                    name == "setTouchMode" && paramCount == 3
                }
            }
            findAllMethods("com.miui.server.input.stylus.MiuiStylusDeviceListener") { true }.hookReplace {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(0, 20, 0x10 or driverVersion) {
                    name == "setTouchMode" && paramCount == 3
                }
            }
        } else {
            findAllConstructors("com.miui.server.stylus.MiuiStylusDeviceListener") { true }.hookAfter {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(0, 20, 1) {
                    name == "setTouchMode" && paramCount == 3
                }
            }
            findAllMethods("com.miui.server.stylus.MiuiStylusDeviceListener") { true }.hookReplace {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(0, 20, 1) {
                    name == "setTouchMode" && paramCount == 3
                }
            }
        }
    }
}
