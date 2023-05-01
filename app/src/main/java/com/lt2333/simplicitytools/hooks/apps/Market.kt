package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.market.AsWhat
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Market : AppRegister() {
    override val packageName: String = "com.xiaomi.market"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    AsWhat,
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    AsWhat,
                )
            }
        }
    }
}