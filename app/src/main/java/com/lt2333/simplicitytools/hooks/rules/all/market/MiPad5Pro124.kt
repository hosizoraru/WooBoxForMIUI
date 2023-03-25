package com.lt2333.simplicitytools.hooks.rules.all.market

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object MiPad5Pro124 : HookRegister() {
    override fun init() = hasEnable("Market_MiPad5Pro124"){
        hookAllConstructorBefore("com.xiaomi.market.MarketApp") {
            XposedHelpers.setStaticObjectField(Build::class.java,"DEVICE", "dagu")
            XposedHelpers.setStaticObjectField(Build::class.java,"MODEL", "22081281AC")
            XposedHelpers.setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
            Log.ix("Voyager-Test: Hook Market Success!")
        }
    }
}