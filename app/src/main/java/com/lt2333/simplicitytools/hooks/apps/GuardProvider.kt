package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.guardprovider.AntiDefraudAppManager
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object GuardProvider : AppRegister() {
    override val packageName: String = "com.miui.guardprovider"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.miui.guardprovider" -> {
                AntiDefraudAppManager().handleLoadPackage(lpparam)
            }
        }
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,

                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,

                )
            }
        }
    }
}