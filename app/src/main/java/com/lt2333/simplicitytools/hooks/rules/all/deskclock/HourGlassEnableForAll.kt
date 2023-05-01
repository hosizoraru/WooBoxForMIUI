package com.lt2333.simplicitytools.hooks.rules.all.deskclock

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.hooks.apps.DeskClock
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object HourGlassEnableForAll : HookRegister() {

    override fun init() = hasEnable("Hour_Glass_Enable") {
        findMethod("com.android.deskclock.util.Util") {
            name == "isHourGlassEnable"
        }.hookAfter {
            it.result = true
        }
        if (DeskClock.versionCode > 130206400) {
            Log.i("Voyager-Message:您可能在使用不支持的时钟版本而且依旧打开了这个hook，如果您不是修改版拉高版本号的话请关闭这个hook！")
        }
    }
}