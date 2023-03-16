package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object MiuiStylusDeviceListener : HookRegister() {
    override fun init() = hasEnable("remove_stylus_bluetooth_restriction") {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            findAllConstructors("com.miui.server.input.stylus.MiuiStylusDeviceListener") { true }.hookAfter {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(
                    "setTouchMode",
                    args(0, 20, 1),
                    argTypes(Int::class.java, Int::class.java, Int::class.java)
                )
            }
            findAllMethods("com.miui.server.input.stylus.MiuiStylusDeviceListener") { true }.hookReplace {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(
                    "setTouchMode",
                    args(0, 20, 1),
                    argTypes(Int::class.java, Int::class.java, Int::class.java)
                )
            }
        } else {
            findAllConstructors("com.miui.server.stylus.MiuiStylusDeviceListener") { true }.hookAfter {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(
                    "setTouchMode",
                    args(0, 20, 1),
                    argTypes(Int::class.java, Int::class.java, Int::class.java)
                )
            }
            findAllMethods("com.miui.server.stylus.MiuiStylusDeviceListener") { true }.hookReplace {
                val ITouchFeature = loadClass("miui.util.ITouchFeature")
                val mTouchFeature = findMethod(ITouchFeature) {
                    name == "getInstance"
                }.invoke(null)
                mTouchFeature?.invokeMethod(
                    "setTouchMode",
                    args(0, 20, 1),
                    argTypes(Int::class.java, Int::class.java, Int::class.java)
                )
            }
        }
    }
}
