package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*

object RemovePackageInstallerAdsForAll : HookRegister() {

    override fun init() = hasEnable("packageinstaller_remove_ads") {
        findMethod("com.android.packageinstaller.compat.MiuiSettingsCompat") {
            name == "isPersonalizedAdEnabled" && returnType == Boolean::class.java
        }.hookAfter {
            it.result = false
        }
        findMethod("com.android.packageinstaller.compat.MiuiSettingsCompat") {
            name == "isInstallRiskEnabled" &&
                    paramCount == 1 &&
                    parameterTypes[0] == Context::class.java
        }.hookAfter {
            it.result = false
        }
        findMethod("m2.b") {
            name == "s"
        }.hookReturnConstant(false)
        findMethod("m2.b") {
            name == "q"
        }.hookReturnConstant(false)
        findMethod("m2.b") {
            name == "f"
        }.hookReturnConstant(false)
        findMethod("m2.b") {
            name == "t"
        }.hookReturnConstant(false)
        findMethod("m2.b") {
            name == "r"
        }.hookReturnConstant(false)
        var letter = 'a'
        for (i in 0..25) {
            try {
                val classIfExists = "com.miui.packageInstaller.ui.listcomponets.${letter}0".findClassOrNull()
                classIfExists?.let {
                    findMethod(it) {
                        name == "a"
                    }.hookAfter { hookParam ->
                        hookParam.thisObject.setBooleanField("l", false)
                    }
                }
            } catch (t: Throwable) {
                letter++
            }
        }
    }
}