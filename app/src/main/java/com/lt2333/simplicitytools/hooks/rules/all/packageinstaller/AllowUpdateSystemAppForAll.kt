package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import android.app.Application

object AllowUpdateSystemAppForAll : HookRegister() {
    override fun init() = hasEnable("packageinstaller_allow_update_system_app") {
        val miuiSettingsCompatClass = "com.android.packageinstaller.compat.MiuiSettingsCompat".findClass()

        try {
            findAllMethods(miuiSettingsCompatClass) {
                name == "isPersonalizedAdEnabled"
            }.hookReturnConstant(false)
        } catch (t: Throwable) {
            Log.ex(t)
        }

        var letter = 'a'
        for (i in 0..25) {
            val classIfExists = ("j2.${letter}").findClassOrNull()
            try {
                classIfExists?.let {
                    findAllMethods(it) {
                        parameterCount == 1 && returnType == Boolean::class.java && parameterTypes[0] == Application::class.java
                    }.hookReturnConstant(false)
                }
            } catch (t: Throwable) {
                letter++
            }
        }

        try {
            "android.os.SystemProperties".hookBeforeMethod(
                "getBoolean", String::class.java, Boolean::class.java
            ) {
                if (it.args[0] == "persist.sys.allow_sys_app_update") it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
    }
}