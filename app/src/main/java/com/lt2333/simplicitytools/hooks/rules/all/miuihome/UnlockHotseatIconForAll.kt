package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.hookBeforeMethod

object UnlockHotseatIconForAll : HookRegister() {

    override fun init() = hasEnable("unlock_hotseat") {

        "com.miui.home.launcher.DeviceConfig".hookBeforeMethod("getHotseatMaxCount") {
            it.result = 99
        }
    }
}