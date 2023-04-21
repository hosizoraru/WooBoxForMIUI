package com.lt2333.simplicitytools.hooks.rules.all.screenrecorder

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object SoundWhere : HookRegister() {
    override fun init() = hasEnable("sound_where") {
        // 模拟匹配查找双音源
        findAllMethods("android.os.SystemProperties") {
            name == "getBoolean" &&
                    parameterTypes[0] == String::class.java &&
                    parameterTypes[1] == Boolean::class.javaPrimitiveType
        }.hookBefore { param ->
            if (param.args[0] == "ro.vendor.audio.playbackcapture.screen") {
                param.result = true
            }
        }
    }
}