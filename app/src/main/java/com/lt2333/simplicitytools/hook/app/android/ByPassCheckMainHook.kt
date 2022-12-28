package com.lt2333.simplicitytools.hook.app.android

import com.lt2333.simplicitytools.hook.app.android.ByPassCheckBaseHook
import com.lt2333.simplicitytools.hook.app.android.GetMinimumSignatureSchemeVersionForTargetSdk
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.Log.logexIfThrow
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

private const val TAG = "BypassCheck"
private val PACKAGE_NAME_HOOKED = setOf(
    "android",
)

class ByPassCheckMainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName in PACKAGE_NAME_HOOKED) {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            when (lpparam.packageName) {
                "android" -> {
                    initHooks(GetMinimumSignatureSchemeVersionForTargetSdk)
                }
            }
        }
    }

    private fun initHooks(vararg hook: ByPassCheckBaseHook) {
        hook.forEach {
            runCatching {
                if (it.isInit) return@forEach
                it.init()
                it.isInit = true
                Log.i("Inited hook: ${it.javaClass.simpleName}")
            }.logexIfThrow("Failed init hook: ${it.javaClass.simpleName}")
        }
    }
}