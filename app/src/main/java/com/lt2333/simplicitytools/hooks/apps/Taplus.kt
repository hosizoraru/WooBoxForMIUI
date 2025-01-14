package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.taplus.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Taplus : AppRegister() {
    override val packageName: String = "com.miui.contentextension"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        hasEnable("intent_browser") {
            IntentBrowserMainHook().handleLoadPackage(lpparam)
        }
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    HorizontalContentextensionForAll, // 横屏传送门
                    SuperImageForAll, // 超分辨率图片
                    DoublePressForAll, // 双指长按
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    HorizontalContentextensionForAll, // 横屏传送门
                    SuperImageForAll, // 超分辨率图片
                    DoublePressForAll, // 双指长按
                )
            }
        }
    }
}