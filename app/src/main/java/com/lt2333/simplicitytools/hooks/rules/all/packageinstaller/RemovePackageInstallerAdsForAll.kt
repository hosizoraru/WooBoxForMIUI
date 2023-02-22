package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*

object RemovePackageInstallerAdsForAll : HookRegister() {

    override fun init() = hasEnable("packageinstaller_remove_ads") {
        findMethod("com.android.packageinstaller.compat.MiuiSettingsCompat") {
            name == "isPersonalizedAdEnabled"
        }.hookAfter {
            it.result = true
        }
        var letter = 'a'
        for (i in 0..25) {
            val classIfExists = "com.miui.packageInstaller.ui.listcomponets.${letter}0".findClassOrNull()
            try {
                classIfExists?.let { clazz ->
                    findMethod(clazz) {
                        name == "a"
                    }.hookAfter {
                        it.thisObject.setBooleanField("l", false)
                    }
                }
            } catch (t: Throwable) {
                letter++
            }
        }
    }
}