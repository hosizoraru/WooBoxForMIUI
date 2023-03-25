package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.market.Mi13Pro
import com.lt2333.simplicitytools.hooks.rules.all.market.Mi13Ultra
import com.lt2333.simplicitytools.hooks.rules.all.market.MiPad5Pro124
import com.lt2333.simplicitytools.hooks.rules.all.market.MiPad6Pro
import com.lt2333.simplicitytools.hooks.rules.all.market.MixFold2
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Market : AppRegister() {
    override val packageName: String = "com.xiaomi.market"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    Mi13Pro,
                    Mi13Ultra,
                    MiPad5Pro124,
                    MiPad6Pro,
                    MixFold2,
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    Mi13Pro,
                    Mi13Ultra,
                    MiPad5Pro124,
                    MiPad6Pro,
                    MixFold2,
                )
            }
        }
    }
}