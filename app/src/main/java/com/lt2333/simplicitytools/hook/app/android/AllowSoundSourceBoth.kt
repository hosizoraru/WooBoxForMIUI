package com.lt2333.simplicitytools.hook.app.android

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.util.XSPUtils
import com.lt2333.simplicitytools.util.xposed.base.HookRegister

object AllowSoundSourceBoth : HookRegister() {
    override fun init() {
        findMethod("android.os.SystemProperties") {
            name == "getInt" && returnType == Int::class.java
        }.hookBefore {
            when (it.args[0] as String) {
                "ro.vendor.audio.playbackcapture.screen" -> if (it.args[0] == "ro.vendor.audio.playbackcapture.screen) it.result = true
            }
        }
    }
}