package com.lt2333.simplicitytools.hooks.rules.all.settings

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableSpeedModeForAll : HookRegister() {
    override fun init() = hasEnable("speed_mode") {
        findMethod("com.android.settings.development.SpeedModeToolsPreferenceController"){
            name == "getAvailabilityStatus"
        }.hookAfter {
            it.result = 0
        }
    }
}