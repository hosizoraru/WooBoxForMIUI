package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object SetPadMode : HookRegister() {
    override fun init() = hasEnable("restore_esc") {
        findMethod("com.android.server.input.config.InputCommonConfig") {
            name == "setPadMode"
        }.hookBefore { param ->
            // XposedBridge.log("MaxMiPadInputTest: setPadMode called!")
            param.args[0] = false
        }
    }
}
