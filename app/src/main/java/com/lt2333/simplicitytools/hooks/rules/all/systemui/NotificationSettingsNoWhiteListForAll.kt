package com.lt2333.simplicitytools.hooks.rules.all.systemui

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers


object NotificationSettingsNoWhiteListForAll : HookRegister() {
    override fun init() {

    }

    override fun init(classLoader: ClassLoader) = hasEnable("notification_settings_no_whitelist") {
        XposedHelpers.setStaticBooleanField(
            XposedHelpers.findClass(
                "com.android.systemui.statusbar.notification.NotificationSettingsManager",
                classLoader
            ), "USE_WHITE_LISTS", false
        )
    }
}