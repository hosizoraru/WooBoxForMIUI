package com.lt2333.simplicitytools.hooks.rules.all.android

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant

object MaxFreeFormaForAll : HookRegister() {
    override fun init() = hasEnable("max_free_form") {
        // GetMaxMiuiFreeFormStackCount
        findMethod("com.android.server.wm.MiuiFreeFormStackDisplayStrategy") {
            name == "getMaxMiuiFreeFormStackCount"
        }.hookReturnConstant(256)
        // GetMaxMiuiFreeFormStackCountForFlashBack
        findMethod("com.android.server.wm.MiuiFreeFormStackDisplayStrategy") {
            name == "getMaxMiuiFreeFormStackCountForFlashBack"
        }.hookReturnConstant(256)
        // ShouldStopStartFreeform
        findMethod("com.android.server.wm.MiuiFreeFormManagerService") {
            name == "shouldStopStartFreeform"
        }.hookReturnConstant(false)
    }
}