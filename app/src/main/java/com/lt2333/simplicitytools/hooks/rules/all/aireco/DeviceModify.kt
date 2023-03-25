package com.lt2333.simplicitytools.hooks.rules.all.aireco

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers.setStaticObjectField

object DeviceModify : HookRegister() {
    override fun init() = hasEnable("device_modify") {
        findAllMethods("com.xiaomi.aireco.utils.DeviceUtils") {
            name == "getVoiceAssistUserAgent"
        }.hookBefore {
            setStaticObjectField(Build::class.java, "DEVICE", "nuwa")
            setStaticObjectField(Build::class.java, "MODEL", "2210132C")
            setStaticObjectField(Build::class.java, "MANUFACTURER", "Xiaomi")
        }
    }
}
