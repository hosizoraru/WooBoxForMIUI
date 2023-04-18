package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import java.lang.reflect.Method

object MiuiStylusPageKeyListener : HookRegister() {
    override fun init() = hasEnable("ignore_stylus_key_gesture") {
        val methods = mutableListOf<Method>()
        val className = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            "com.miui.server.input.stylus.MiuiStylusPageKeyListener"
        } else {
            "com.miui.server.stylus.MiuiStylusPageKeyListener"
        }
        val methodNames = setOf(
            "isPageKeyEnable", "needInterceptBeforeDispatching", "shouldInterceptKey"
        )
        methodNames.forEach { methodName ->
            kotlin.runCatching {
                findMethod(className) {
                    name == methodName
                }
            }.getOrNull()?.let { method ->
                methods.add(method)
            }
        }
        methods.hookReturnConstant(false)
    }
}