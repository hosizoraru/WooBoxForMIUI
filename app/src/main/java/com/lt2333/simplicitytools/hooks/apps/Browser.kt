package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.browser.DebugMode
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Browser : AppRegister() {
    override val packageName: String = "com.android.browser"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {

        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    DebugMode, // 开发者选项
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    DebugMode, // 开发者选项
                )
            }
        }
    }
}