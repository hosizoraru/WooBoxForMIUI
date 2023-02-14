package com.lt2333.simplicitytools.hooks.rules.t.android

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object DisableFlagSecureForT : HookRegister() {
    override fun init() = hasEnable("disable_flag_secure") {
        findMethod("com.android.server.wm.WindowState") {
            name == "isSecureLocked"
        }.hookReturnConstant(false)
    }
    
}
