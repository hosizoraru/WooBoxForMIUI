package com.lt2333.simplicitytools.hooks.rules.all.browser

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object DebugMode : HookRegister() {
    override fun init() = hasEnable("Enable_Debug_Mode") {

        try {
            // old version
            findAllMethods("f.b.a.if") {
                name == "getDebugMode"
            }.hookBefore {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex("Voyager-Test: Hook old version Browser Fail!")
        }

        try {
            // 17.5.100320
            findAllMethods("com.android.browser.f1") {
                name == "getDebugMode"
            }.hookBefore {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex("Voyager-Test: Hook version 17.5.100320 Browser Fail!")
        }


    }
}