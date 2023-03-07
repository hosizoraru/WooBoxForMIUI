package com.lt2333.simplicitytools.hooks.rules.all.systemui

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.paramCount
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.setObjectField
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister


object NotificationSettingsNoWhiteListForAll : HookRegister() {
    override fun init() = hasEnable("notification_settings_no_whitelist") {
        findMethod("com.android.systemui.statusbar.notification.NotificationSettingsManager") {
            name == "canShowBadge" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canFloat" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canFloat" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java &&
                    parameterTypes[2] == String::class.java
            name == "canShowOnKeyguard" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canShowOnKeyguard" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java &&
                    parameterTypes[2] == String::class.java
            name == "canSound" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canSound" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java &&
                    parameterTypes[2] == String::class.java
            name == "canVibrate" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canVibrate" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java &&
                    parameterTypes[2] == String::class.java
            name == "canLights" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java
            name == "canLights" &&
                    parameterTypes[0] == Context::class.java &&
                    parameterTypes[1] == String::class.java &&
                    parameterTypes[2] == String::class.java
        }.hookBefore {
            it.thisObject.setObjectField("USE_WHITE_LISTS", false)
        }
    }
}