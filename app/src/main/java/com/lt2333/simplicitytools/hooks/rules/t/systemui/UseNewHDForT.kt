package com.lt2333.simplicitytools.hooks.rules.t.systemui

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object UseNewHDForT : HookRegister() {
    override fun init() = hasEnable("systemui_use_new_hd") {
        runCatching {
            findMethod("com.android.systemui.statusbar.policy.HDController") {
                name == "isVisible"
            }.hookReturnConstant(true)
        }
    }
}