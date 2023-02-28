package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object SystemServerImpl : HookRegister() {
    override fun init() {
        try {
            findMethod("com.android.server.SystemServerImpl") {
                name == "addMagicPointerManagerService"
            }.hookReturnConstant(null)
            XposedBridge.log("MaxMiPadInput: Hook addMagicPointerManagerService success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxMiPadInput: Hook addMagicPointerManagerService failed!")
            XposedBridge.log(e)
        }
    }
}
