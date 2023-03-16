package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object MiuiStylusPageKeyListener : HookRegister() {
    override fun init() = hasEnable("ignore_stylus_key_gesture") {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            findMethod("com.miui.server.input.stylus.MiuiStylusPageKeyListener") {
                name == "isPageKeyEnable"
            }.hookReturnConstant(false)
        } else {
            findMethod("com.miui.server.stylus.MiuiStylusPageKeyListener") {
                name == "isPageKeyEnable"
            }.hookReturnConstant(false)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            findMethod("com.miui.server.input.stylus.MiuiStylusPageKeyListener") {
                name == "needInterceptBeforeDispatching"
            }.hookReturnConstant(false)
        } else {
            findMethod("com.miui.server.stylus.MiuiStylusPageKeyListener") {
                name == "needInterceptBeforeDispatching"
            }.hookReturnConstant(false)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            findMethod("com.miui.server.input.stylus.MiuiStylusPageKeyListener") {
                name == "shouldInterceptKey"
            }.hookReturnConstant(false)
        } else {
            findMethod("com.miui.server.stylus.MiuiStylusPageKeyListener") {
                name == "shouldInterceptKey"
            }.hookReturnConstant(false)
        }
    }
}