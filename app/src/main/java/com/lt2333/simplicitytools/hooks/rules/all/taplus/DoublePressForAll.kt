package com.lt2333.simplicitytools.hooks.rules.all.taplus

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object DoublePressForAll : HookRegister() {

    override fun init() = hasEnable("double_press") {
        findMethod("com.miui.contentextension.utils.ContentCatcherUtil") {
            name == "isCatcherSupportDoublePress" &&
                    parameterTypes[0] == Context::class.java
        }.hookReturnConstant(true)
    }
}