package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object MiuiMagicPointerUtils : HookRegister() {
    override fun init() {
        try {
            findMethod("android.magicpointer.util.MiuiMagicPointerUtils") {
                name == "isEnable"
            }.hookReturnConstant(false)
            XposedBridge.log("MaxMiPadInput: Hook MiuiMagicPointerUtils.isEnable success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxMiPadInput: Hook MiuiMagicPointerUtils.isEnable failed!")
            XposedBridge.log(e)
        }
    }
}
