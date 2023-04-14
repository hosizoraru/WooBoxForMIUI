package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.findClass
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object UnlockGridsNoWord : HookRegister() {
    override fun init() {
        val deviceConfigClass = "com.miui.home.launcher.DeviceConfig".findClass()
        findAllMethods(deviceConfigClass) {
            name == "isThemeCoverCellConfig"
        }.hookReturnConstant(true)
    }
}