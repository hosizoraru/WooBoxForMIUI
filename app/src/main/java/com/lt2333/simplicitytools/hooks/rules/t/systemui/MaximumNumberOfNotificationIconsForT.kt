package com.lt2333.simplicitytools.hooks.rules.t.systemui

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReplace
import com.github.kyuubiran.ezxhelper.utils.invokeMethod
import com.github.kyuubiran.ezxhelper.utils.putObject
import com.lt2333.simplicitytools.utils.XSPUtils
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object MaximumNumberOfNotificationIconsForT : HookRegister() {

    override fun init() {
        val icons = XSPUtils.getInt("maximum_number_of_notification_icons", 3)
        val dots = XSPUtils.getInt("maximum_number_of_notification_dots", 3)
        val licons = XSPUtils.getInt("maximum_number_of_lockscreen_notification_icons",3)
        findMethod("com.android.systemui.statusbar.phone.NotificationIconContainer") {
            name == "miuiShowNotificationIcons" && parameterCount == 1
        }.hookReplace {
            if (it.args[0] as Boolean) {
                it.thisObject.putObject("MAX_DOTS", dots)
                it.thisObject.putObject("MAX_STATIC_ICONS", icons)
                it.thisObject.putObject("MAX_MAX_ICONS_ON_LOCKSCREEN", licons)
            } else {
                it.thisObject.putObject("MAX_DOTS", 0)
                it.thisObject.putObject("MAX_STATIC_ICONS", 0)
                it.thisObject.putObject("MAX_MAX_ICONS_ON_LOCKSCREEN", 0)
            }
            it.thisObject.invokeMethod("updateState")
        }
    }

}