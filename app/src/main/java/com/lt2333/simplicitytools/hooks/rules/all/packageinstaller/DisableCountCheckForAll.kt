package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object DisableCountCheckForAll : HookRegister() {

    override fun init() = hasEnable("pkg_installer_count_checking") {
        findMethod("com.miui.packageInstaller.model.RiskControlRules") {
            name == "getCurrentLevel"
        }.hookBefore { param ->
            XposedBridge.log("Hooked getCurrentLevel, param result = ${param.result}")
            param.result = 0
        }
    }
}