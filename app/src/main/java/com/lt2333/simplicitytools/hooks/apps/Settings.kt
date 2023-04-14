package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.settings.EnableSpeedModeForAll
import com.lt2333.simplicitytools.hooks.rules.all.settings.QuickInstallPermission
import com.lt2333.simplicitytools.hooks.rules.all.settings.SettingsDontThroughTheList
import com.lt2333.simplicitytools.hooks.rules.all.settings.ShowNotificationImportanceForAll
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Settings : AppRegister() {
    override val packageName: String = "com.android.settings"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        hasEnable("Settings_Dont_Through_The_List") {
            SettingsDontThroughTheList().handleLoadPackage(lpparam)
        }
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    ShowNotificationImportanceForAll, //显示通知重要程度
                    EnableSpeedModeForAll, // 开启极致模式选项
                    QuickInstallPermission, // 安装未知应用权限免翻应用列表
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    ShowNotificationImportanceForAll, //显示通知重要程度
                    EnableSpeedModeForAll, // 开启极致模式选项
                    QuickInstallPermission, // 安装未知应用权限免翻应用列表
                )
            }
        }
    }
}