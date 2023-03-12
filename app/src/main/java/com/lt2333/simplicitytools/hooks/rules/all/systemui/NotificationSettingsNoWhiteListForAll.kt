package com.lt2333.simplicitytools.hooks.rules.all.systemui

import com.lt2333.simplicitytools.utils.chsbuffer.Hook
import com.lt2333.simplicitytools.utils.hasEnable
import de.robv.android.xposed.XposedHelpers


object NotificationSettingsNoWhiteListForAll : Hook() {

    override fun init(classLoader: ClassLoader) = hasEnable("notification_settings_no_whitelist") {
        XposedHelpers.setStaticBooleanField(
            XposedHelpers.findClass(
                "com.android.systemui.statusbar.notification.NotificationSettingsManager",
                classLoader
            ), "USE_WHITE_LISTS", false
        )
    }
}