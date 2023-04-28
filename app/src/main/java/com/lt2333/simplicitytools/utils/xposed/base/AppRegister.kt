package com.lt2333.simplicitytools.utils.xposed.base

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.Log.logexIfThrow
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage

abstract class AppRegister: IXposedHookLoadPackage, IXposedHookInitPackageResources {

    abstract val packageName: String

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {}

    protected fun autoInitHooks(lpparam: XC_LoadPackage.LoadPackageParam, vararg hook: HookRegister) {
        hook.also {
            Log.ix("WooBox: Try to Hook [$packageName]")
        }.forEach {
            runCatching {
                if (it.isInit) return@forEach
                it.setLoadPackageParam(lpparam)
                it.init()
                it.isInit = true
            }.logexIfThrow("Failed to Hook [$packageName]")
        }
    }

    override fun handleInitPackageResources(resparam: XC_InitPackageResources.InitPackageResourcesParam) {}

    protected fun autoInitResourcesHooks(resparam: XC_InitPackageResources.InitPackageResourcesParam, vararg hook: ResourcesHookRegister) {
        hook.also {
            Log.ix("WooBox: Try to Hook [$packageName]")
        }.forEach {
            runCatching {
                if (it.isInit) return@forEach
                it.setInitPackageResourcesParam(resparam)
                it.init()
                it.isInit = true
                Log.ix("Inited hook: ${it.javaClass.simpleName}")
            }.logexIfThrow("Failed to Hook [$packageName]")
        }
    }
}