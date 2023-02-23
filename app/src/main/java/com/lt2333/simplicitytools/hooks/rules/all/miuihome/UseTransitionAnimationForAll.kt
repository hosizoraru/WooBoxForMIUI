package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object UseTransitionAnimationForAll : HookRegister() {

    override fun init() = hasEnable("Use_Transition_Animation") {

        findMethod("com.miui.home.launcher.LauncherWidgetView") {
            name == "isUseTransitionAnimation"
        }.hookAfter {
            it.result = true
        }
    }

}