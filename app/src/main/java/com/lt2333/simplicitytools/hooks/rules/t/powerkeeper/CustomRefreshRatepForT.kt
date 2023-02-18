package com.lt2333.simplicitytools.hooks.rules.t.powerkeeper

import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object CustomRefreshRatepForT : HookRegister(){
    override fun init() = hasEnable("custom_refresh_rate") {

        findMethod("com.miui.powerkeeper.statemachine.DisplayFrameSetting"){
            name == "parseCustomModeSwitchFromDb" &&
                    paramCount == 1 &&
                    parameterTypes[0] == String::class.java
        }.hookReturnConstant(true)
    }
}