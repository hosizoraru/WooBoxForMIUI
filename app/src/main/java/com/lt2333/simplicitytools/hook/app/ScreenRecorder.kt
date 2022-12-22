package com.lt2333.simplicitytools.hook.app

import com.lt2333.simplicitytools.hook.app.screenrecorder.AllowSoundSourceBoth
import com.lt2333.simplicitytools.util.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object ScreenRecorder: AppRegister() {
    override val packageName: String = "com.miui.screenrecorder"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        autoInitHooks(lpparam,
            AllowSoundSourceBoth, //允许屏幕录制同时录制系统和麦克风声音
        )
    }
}