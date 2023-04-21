package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.screenrecorder.BitRateLevel
import com.lt2333.simplicitytools.hooks.rules.all.screenrecorder.FrameLevel
import com.lt2333.simplicitytools.hooks.rules.all.screenrecorder.SoundWhere
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object ScreenRecorder : AppRegister() {
    override val packageName: String = "com.miui.screenrecorder"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    FrameLevel, // 帧率
                    BitRateLevel, // 码率
                    SoundWhere, // 声音来源
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    FrameLevel, // 帧率
                    BitRateLevel, // 码率
                    SoundWhere, // 声音来源
                )
            }
        }
    }
}