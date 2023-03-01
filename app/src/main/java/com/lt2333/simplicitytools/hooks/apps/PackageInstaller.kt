package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.packageinstaller.*
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object PackageInstaller : AppRegister() {

    override val packageName: String = "com.miui.packageinstaller"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    DisableCountCheckForAll,// 禁用频繁安装应用检查
                    RemovePackageInstallerAdsForAll,// 去广告
                    AllowUpdateSystemAppForAll,// 允许更新系统应用
                    ShowMoreApkInfoForAll,// 显示更多应用信息
                    DisableSafeModelTipForAll,// 禁用安全模式提示
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    DisableCountCheckForAll,// 禁用频繁安装应用检查
                    RemovePackageInstallerAdsForAll,// 去广告
                    AllowUpdateSystemAppForAll,// 允许更新系统应用
                    ShowMoreApkInfoForAll,// 显示更多应用信息
                    DisableSafeModelTipForAll,// 禁用安全模式提示
                )
            }
        }
    }
}