package com.lt2333.simplicitytools.hooks.rules.t.android

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object DisableFlagSecureForT : HookRegister() {
    override fun init() = hasEnable("disable_flag_secure") {
        findMethod("com.android.server.wm.WindowState") {
            name == "isSecureLocked"
        }.hookReturnConstant(false)
        
        findMethod("com.android.server.wm.WindowManagerService") {
            name == "isSecureLocked"
        }.hookReturnConstant(false)
        
        findMethod("com.android.server.wm.WindowSurfaceController") {
                name == "setSecure"
        }.hookBefore {
            it.args[0] = false
        }
        hookAllConstructorBefore("com.android.server.wm.WindowSurfaceController") {
            var flags = it.args[2] as Int
            val secureFlag = 128
            flags = flags and secureFlag.inv()
            it.args[2] = flags
        }
    }
    
}
