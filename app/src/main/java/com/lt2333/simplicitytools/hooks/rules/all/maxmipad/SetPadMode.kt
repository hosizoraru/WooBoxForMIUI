package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object SetPadMode : HookRegister() {
    override fun init() {
        try {
            findMethod("com.android.server.input.config.InputCommonConfig") {
                name == "setPadMode"
            }.hookBefore { param ->
                // XposedBridge.log("MaxMiPadInputTest: setPadMode called!")
                param.args[0] = false
            }
            XposedBridge.log("MaxMiPadInput: Hook setPadMode success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxMiPadInput: Hook setPadMode failed!")
            XposedBridge.log(e)
        }
    }
}
