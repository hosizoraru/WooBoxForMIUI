package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.s.miuihome.AlwaysDisplayTimeForS
import com.lt2333.simplicitytools.hooks.rules.s.miuihome.DoubleTapToSleepForS
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object MiuiHome : AppRegister() {
    override val packageName: String = "com.miui.home"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    AlwaysDisplayTimeForS, //时钟显示时钟
                    DoubleTapToSleepForS, //双击锁屏
                )
            }
            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    AlwaysDisplayTimeForS, //时钟显示时钟
                    DoubleTapToSleepForS, //双击锁屏
                )
            }
        }
    }
}