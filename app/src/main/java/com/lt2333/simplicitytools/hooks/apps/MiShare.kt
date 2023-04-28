package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.mishare.NoAutoTurnOffForAll
import com.lt2333.simplicitytools.utils.Yife.LoadPackageParam.getAppVersionCode
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object MiShare : AppRegister() {
    override val packageName: String = "com.miui.mishare.connectivity"
    var versionCode: Int = -1

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        versionCode = lpparam.getAppVersionCode()
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    NoAutoTurnOffForAll, // 小米妙享不会自动关闭
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    NoAutoTurnOffForAll, // 小米妙享不会自动关闭
                )
            }
        }
    }
}