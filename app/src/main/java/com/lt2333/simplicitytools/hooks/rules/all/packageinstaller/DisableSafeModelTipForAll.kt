package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hookAfterMethod
import com.lt2333.simplicitytools.utils.setBooleanField

object DisableSafeModelTipForAll: HookRegister() {
    override fun init() = hasEnable("Disable_Safe_Model_Tip") {
        findMethod("com.miui.packageInstaller.model.ApkInfo") {
            name == "getSystemApp"
        }.hookReturnConstant(true)
        findMethod("com.miui.packageInstaller.InstallProgressActivity") {
            name == "g0"
        }.hookReturnConstant(false)
        findMethod("com.miui.packageInstaller.InstallProgressActivity") {
            name == "Q1"
        }.hookBefore {
            it.result = null
        }
        findAllMethods("com.miui.packageInstaller.InstallProgressActivity") {
            true
        }.hookAfter { param ->
            param.thisObject.javaClass.findField {
                type == Boolean::class.java
            }.setBoolean(param.thisObject, false)
        }
        "com.miui.packageInstaller.ui.listcomponets.g0".hookAfterMethod("a") {
            it.thisObject.setBooleanField("l",false)
        }
    }
}