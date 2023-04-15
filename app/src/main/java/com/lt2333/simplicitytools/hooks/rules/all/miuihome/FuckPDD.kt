package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object FuckPDD : HookRegister() {
    override fun init() = hasEnable("Fuck_PDD") {
        findAllMethods("com.miui.home.launcher.operationicon.OperationIconProvider") {
            name == "getIcon"
        }.hookReturnConstant(null)
        findAllMethods("com.miui.home.launcher.operationicon.OperationIconClickInterceptor") {
            name == "getLaunchDpIntent"
        }.hookReturnConstant(null)
    }
}