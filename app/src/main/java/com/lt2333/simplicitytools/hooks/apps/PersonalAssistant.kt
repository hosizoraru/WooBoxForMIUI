package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.personalassistant.*
import com.lt2333.simplicitytools.hooks.rules.all.wini.hooks.WiniMainHook
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object PersonalAssistant : AppRegister(){
    override val packageName: String = "com.miui.personalassistant"
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        WiniMainHook().handleLoadPackage(lpparam)
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    BlurWhenGotoMinusOne, // 负一屏模糊
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    BlurWhenGotoMinusOne, // 负一屏模糊
                )
            }
        }
    }
}
