package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.mishare.NoAutoTurnOffForAll
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object MiShare : AppRegister() {
    override val packageName: String = "com.miui.mishare.connectivity"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    NoAutoTurnOffForAll, // 禁止自动关闭小米互传
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    NoAutoTurnOffForAll, // 禁止自动关闭小米互传
                )
            }
        }
    }
}