package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.XSharedPreferences
import de.robv.android.xposed.XposedBridge

object MiuiFixedOrientationController : HookRegister() {
    override fun init() {
        try {
            val shouldDisableFixedOrientationList =
                XSharedPreferences.getStringSet("should_disable_fixed_orientation_list", mutableSetOf())
            findAllMethods("com.android.server.wm.MiuiFixedOrientationController") {
                name == "shouldDisableFixedOrientation"
            }.hookBefore { param ->
                if (param.args[0] in shouldDisableFixedOrientationList) {
                    param.result = true
                }
            }
            XposedBridge.log("MaxMiPadInput: Hook shouldDisableFixedOrientation success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxMiPadInput: Hook shouldDisableFixedOrientation failed!")
            XposedBridge.log(e)
        }
    }
}
