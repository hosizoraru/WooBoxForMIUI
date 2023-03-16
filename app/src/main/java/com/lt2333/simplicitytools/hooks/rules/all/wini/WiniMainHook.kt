package com.lt2333.simplicitytools.hooks.rules.all.wini

import android.content.Context
import cn.houkyo.wini.utils.HookUtils
import com.google.gson.Gson
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage
import com.lt2333.simplicitytools.BuildConfig
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.hooks.rules.all.wini.BlurWhenShowShortcutMenu
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.setBooleanField
import com.lt2333.simplicitytools.utils.wini.*

class WiniMainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        val otherHooks = OtherHooks(lpparam.classLoader)
        when (lpparam.packageName) {
            // 系统桌面
            "com.miui.home" -> {
                val config = getConfig()
                val miuiHomeHooks = BlurWhenShowShortcutMenu(lpparam.classLoader, config)
                if (config.BlurWhenShowShortcutMenu.enableShortcutBackgroundBlur) {
                    miuiHomeHooks.addBlurEffectToShortcutLayer()
                }

                otherHooks.deviceLevelHook()
                miuiHomeHooks.addBlurEffectToFolderIcon()
                miuiHomeHooks.addBlurEffectToAlphaIcon()
                miuiHomeHooks.hideBlurIconWhenEnterRecents()
            }
            BuildConfig.APPLICATION_ID -> {
                getConfig(true)
                otherHooks.enableModule()
            }
            else -> {
                return
            }
        }
        XposedBridge.log("Voyager-Test-MainHook:快捷菜单当前的模糊值为${R.string.shortcutMenuBackgroundAlpha}")
    }
    private fun getConfig(showLog: Boolean = false): ConfigModel {
        val xSharedPreferences =
            XSharedPreferences(BuildConfig.APPLICATION_ID, Storage.DATA_FILENAME)
        xSharedPreferences.makeWorldReadable()
        val configJsonString = xSharedPreferences.getString(Storage.CONFIG_JSON, "")
        if (configJsonString != null && configJsonString != "") {
            if (showLog) {
                HookUtils.log(configJsonString)
            }
            return Storage.getConfig(configJsonString)
        }
        return ConfigModel()
    }
}