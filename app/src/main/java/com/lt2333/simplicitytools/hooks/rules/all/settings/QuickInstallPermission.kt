package com.lt2333.simplicitytools.hooks.rules.all.settings

import android.app.Activity
import android.os.Bundle
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.setObjectField
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object QuickInstallPermission : HookRegister() {
    override fun init() = hasEnable("system_settings_permission_unknown_origin_app") {
        findAllMethods("com.android.settings.SettingsActivity") {
            name == "redirectTabletActivity" && parameterTypes[0] == Bundle::class.java
        }.hookBefore { param->
            val intent = (param.thisObject as Activity).intent
            if ("android.settings.MANAGE_UNKNOWN_APP_SOURCES" == intent.action && intent.data != null && "package" == intent.data!!.scheme) {
                param.thisObject.setObjectField(
                    "initialFragmentName",
                    "com.android.settings.applications.appinfo.ExternalSourcesDetails"
                )
            }
        }
    }
}