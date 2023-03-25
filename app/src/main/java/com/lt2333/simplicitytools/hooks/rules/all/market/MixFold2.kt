package com.lt2333.simplicitytools.hooks.rules.all.market

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers.setStaticObjectField

object MixFold2: HookRegister() {
    override fun init() = hasEnable("Market_MixFold2"){
        hookAllConstructorBefore("com.xiaomi.market.MarketApp") {
            setStaticObjectField(Build::class.java,"DEVICE", "zizhan")
            setStaticObjectField(Build::class.java,"MODEL", "22061218C")
            setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
            Log.ix("Voyager-Test: Hook Market Success!")
        }
    }
}