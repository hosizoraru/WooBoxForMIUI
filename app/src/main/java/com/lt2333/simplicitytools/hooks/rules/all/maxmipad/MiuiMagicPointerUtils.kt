package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object MiuiMagicPointerUtils : HookRegister() {
    override fun init() = hasEnable("no_magic_pointer") {
        findMethod("android.magicpointer.util.MiuiMagicPointerUtils") {
            name == "isEnable"
        }.hookReturnConstant(false)
    }
}
