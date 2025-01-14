package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.mediaeditor.FilterManagerAll
import com.lt2333.simplicitytools.hooks.rules.s.mediaeditor.UnlockUnlimitedCroppingForS
import com.lt2333.simplicitytools.hooks.rules.t.mediaeditor.UnlockUnlimitedCroppingForT
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage
import com.lt2333.simplicitytools.utils.Yife.LoadPackageParam.getAppVersionCode

object MediaEditor : AppRegister() {
    override val packageName: String = "com.miui.mediaeditor"
    var versionCode: Int = -1
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        versionCode = lpparam.getAppVersionCode()
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    UnlockUnlimitedCroppingForT, //解锁裁切最小值
                    FilterManagerAll, // 解锁大师滤镜
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    UnlockUnlimitedCroppingForS, //解锁裁切最小值
                    FilterManagerAll, // 解锁大师滤镜
                )
            }
        }
    }
}