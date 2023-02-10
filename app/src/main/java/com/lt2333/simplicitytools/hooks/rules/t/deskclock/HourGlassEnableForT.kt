package com.lt2333.simplicitytools.hooks.rules.t.deskclock

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object HourGlassEnableForT : HookRegister() {

    override fun init() = hasEnable("Hour_Glass_Enable") {
        findMethod("com.android.deskclock.util.Util") {
            name == "isHourGlassEnable"
        }.hookAfter {
            it.result = true
        }
    }
}