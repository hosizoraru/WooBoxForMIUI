package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.gallery.EnableOcrAll
import com.lt2333.simplicitytools.hooks.rules.all.gallery.EnableOcrFormForAll
import com.lt2333.simplicitytools.hooks.rules.all.gallery.UnlockHDREnhanceForAll
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Gallery : AppRegister() {
    override val packageName: String = "com.miui.gallery"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    UnlockHDREnhanceForAll, // 超动态显示
                    EnableOcrAll, // 解锁提取文字
                    EnableOcrFormForAll, // 解锁提取表格
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    UnlockHDREnhanceForAll, // 超动态显示
                    EnableOcrAll, // 解锁提取文字
                    EnableOcrFormForAll, // 解锁提取表格
                )
            }
        }
    }
}