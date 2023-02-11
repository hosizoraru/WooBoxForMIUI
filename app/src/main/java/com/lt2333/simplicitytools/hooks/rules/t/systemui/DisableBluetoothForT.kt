package com.lt2333.simplicitytools.hooks.rules.t.systemui

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object DisableBluetoothForT : HookRegister() {

    override fun init() {
        findMethod("com.android.settingslib.bluetooth.LocalBluetoothAdapter") {
            name == "isSupportBluetoothRestrict" && parameterTypes[0] == Context::class.java
        }.hookBefore {
            hasEnable("Disable_Bluetooth") {
                it.result = false
            }
        }
    }
}