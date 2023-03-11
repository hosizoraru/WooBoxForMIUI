package com.lt2333.simplicitytools.hooks.rules.t.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object MonoChromeIconForT : HookRegister() {
    override fun init() = hasEnable("mono_chrome_icon") {
        findMethod("com.miui.home.launcher.graphics.MonochromeUtils") {
            name == "isSupportMonochrome"
        }.hookAfter {
            it.result = true
        }
    }
}