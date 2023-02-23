package com.lt2333.simplicitytools.hooks.rules.all.packageinstaller

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*
import com.github.kyuubiran.ezxhelper.utils.Log
import android.content.pm.ApplicationInfo
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore

object AllowUpdateSystemAppForAll : HookRegister() {
    override fun init() = hasEnable("packageinstaller_allow_update_system_app") {

        var letter = 'a'
        for (i in 0..25) {
            try {
                val classIfExists = "j2.${letter}".findClassOrNull()
                classIfExists?.let {
                    findMethod(it) {
                        parameterCount == 1 && parameterTypes[0] == ApplicationInfo::class.java && returnType == Boolean::class.java
                    }.hookBefore { hookParam ->
                        hookParam.result = false
                    }
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