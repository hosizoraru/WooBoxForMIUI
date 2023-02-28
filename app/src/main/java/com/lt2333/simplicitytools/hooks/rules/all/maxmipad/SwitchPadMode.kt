package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object SwitchPadMode : HookRegister() {
    override fun init() {
        try {
            findMethod("com.android.server.input.InputManagerServiceStubImpl") {
                name == "switchPadMode"
            }.hookBefore { param ->
                param.args[0] = false
            }
            XposedBridge.log("MaxMiPadInput: Hook switchPadMode success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxMiPadInput: Hook switchPadMode failed!")
            XposedBridge.log(e)
        }
    }
}
