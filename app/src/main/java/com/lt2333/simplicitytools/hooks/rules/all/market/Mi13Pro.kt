package com.lt2333.simplicitytools.hooks.rules.all.market

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers.setStaticObjectField

object Mi13Pro : HookRegister() {
    override fun init() = hasEnable("Market_Mi13Pro"){
        hookAllConstructorBefore("com.xiaomi.market.MarketApp") {
            setStaticObjectField(Build::class.java,"DEVICE", "nuwa")
            setStaticObjectField(Build::class.java,"MODEL", "2210132C")
            setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
            Log.ix("Voyager-Test: Hook Market Success!")
        }
    }
}