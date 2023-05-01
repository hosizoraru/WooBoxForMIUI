package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.deskclock.HourGlassEnableForAll
import com.lt2333.simplicitytools.utils.Yife.LoadPackageParam.getAppVersionCode
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object DeskClock : AppRegister() {
    override val packageName: String = "com.android.deskclock"
    var versionCode: Int = -1

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        versionCode = lpparam.getAppVersionCode()
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    HourGlassEnableForAll, // 计时沙漏动画
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    HourGlassEnableForAll, // 计时沙漏动画
                )
            }
        }
    }
}