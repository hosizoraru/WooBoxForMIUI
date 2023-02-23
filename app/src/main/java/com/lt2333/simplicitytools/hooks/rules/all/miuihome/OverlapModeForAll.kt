package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object OverlapModeForAll : HookRegister() {

    override fun init() = hasEnable("Overlap_Mode") {
        // Fold2 样式 负一屏
        findMethod("com.miui.home.launcher.overlay.assistant.AssistantDeviceAdapter") {
            name == "inOverlapMode"
        }.hookReturnConstant(true)
    }

}