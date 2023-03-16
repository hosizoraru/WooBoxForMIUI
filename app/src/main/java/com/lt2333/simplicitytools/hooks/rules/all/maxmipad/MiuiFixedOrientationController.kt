package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.XSharedPreferences
import com.lt2333.simplicitytools.utils.hasEnable

object MiuiFixedOrientationController : HookRegister() {
    override fun init() = hasEnable("disable_fixed_orientation") {
        val shouldDisableFixedOrientationList =
            XSharedPreferences.getStringSet("should_disable_fixed_orientation_list", mutableSetOf())
        findAllMethods("com.android.server.wm.MiuiFixedOrientationController") {
            name == "shouldDisableFixedOrientation"
        }.hookBefore { param ->
            if (param.args[0] in shouldDisableFixedOrientationList) {
                param.result = true
            }
        }
    }
}
