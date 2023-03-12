package com.lt2333.simplicitytools.hooks.rules.all.guardprovider

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.replaceMethod
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.luckypray.dexkit.DexKitBridge
import java.lang.reflect.Method

class AntiDefraudAppManager : IXposedHookLoadPackage {
    @Throws(NoSuchMethodException::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) = hasEnable("Anti_Defraud_App_Manager") {
        System.loadLibrary("dexkit")
        DexKitBridge.create(lpparam.appInfo.sourceDir)?.use { bridge ->
            val map = mapOf(
                "AntiDefraudAppManager" to setOf("AntiDefraudAppManager", "https://flash.sec.miui.com/detect/app"),
            )

            val resultMap = bridge.batchFindMethodsUsingStrings {
                queryMap(map)
            }

            val antiDefraudAppManager = resultMap["AntiDefraudAppManager"]!!
            assert(antiDefraudAppManager.size == 1)
            val antiDefraudAppManagerDescriptor = antiDefraudAppManager.first()
            val antiDefraudAppManagerMethod: Method = antiDefraudAppManagerDescriptor.getMethodInstance(lpparam.classLoader)
            antiDefraudAppManagerMethod.replaceMethod {
                return@replaceMethod null
            }
        }
    }
}