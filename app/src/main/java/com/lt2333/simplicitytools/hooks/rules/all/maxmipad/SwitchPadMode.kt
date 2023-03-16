package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object SwitchPadMode : HookRegister() {
    override fun init() = hasEnable("restore_esc"){
        findMethod("com.android.server.input.InputManagerServiceStubImpl") {
            name == "switchPadMode"
        }.hookBefore { param ->
            param.args[0] = false
        }
    }
}
