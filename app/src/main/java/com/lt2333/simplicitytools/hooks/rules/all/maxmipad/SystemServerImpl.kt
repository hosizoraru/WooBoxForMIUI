package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object SystemServerImpl : HookRegister() {
    override fun init() = hasEnable("no_magic_pointer") {
        findMethod("com.android.server.SystemServerImpl") {
            name == "addMagicPointerManagerService"
        }.hookReturnConstant(null)
    }
}
