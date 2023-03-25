package com.lt2333.simplicitytools.hooks.rules.all.market

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object MiPad6Pro : HookRegister() {
    override fun init() = hasEnable("Market_MiPad6Pro"){
        hookAllConstructorBefore("com.xiaomi.market.MarketApp") {
            XposedHelpers.setStaticObjectField(Build::class.java,"DEVICE", "liuqin")
            XposedHelpers.setStaticObjectField(Build::class.java,"MODEL", "23046RP50C")
            XposedHelpers.setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
            Log.ix("Voyager-Test: Hook Market Success!")
        }
    }
}