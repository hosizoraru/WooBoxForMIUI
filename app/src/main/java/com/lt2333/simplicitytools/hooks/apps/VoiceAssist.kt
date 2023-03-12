package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.taplus.IntentBrowserMainHook
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object VoiceAssist : AppRegister() {
    override val packageName: String = "com.miui.voiceassist"
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        hasEnable("intent_browser") {
            IntentBrowserMainHook().handleLoadPackage(lpparam)
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