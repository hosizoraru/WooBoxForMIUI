package com.lt2333.simplicitytools.hooks.rules.t.misettings

import android.os.Bundle
import com.github.kyuubiran.ezxhelper.utils.*
import com.github.kyuubiran.ezxhelper.*
import com.lt2333.simplicitytools.utils.*
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object CustomRefreshRatesForT : HookRegister(){
    override fun init() = hasEnable("custom_refresh_rate") {

//        findMethod("com.xiaomi.misettings.display.RefreshRate.NewRefreshRateFragment"){
//            name == "b" &&
//                    paramCount == 1 &&
//                    parameterTypes[0] == Boolean::class.java
//        }.hookReturnConstant(true)


        try {
            "com.xiaomi.misettings.display.RefreshRate.NewRefreshRateFragment".hookBeforeMethod("b",Boolean::class.java) {
                it.args[0] = true
            }
            XposedBridge.log("Voyager-Test: CustomRefreshRatesForT misettings.display.RefreshRate.NewRefreshRateFragment success!")
        } catch (e: Throwable) {
            XposedBridge.log("Voyager-Test: CustomRefreshRatesForT misettings.display.RefreshRate.NewRefreshRateFragment failed!")
            XposedBridge.log(e)
        }

        try {
            "com.xiaomi.misettings.display.RefreshRate.RefreshRateActivity".hookBeforeMethod("onCreate",Bundle::class.java) {
                it.thisObject.setObjectField("a", true)
            }
            XposedBridge.log("Voyager-Test: CustomRefreshRatesForT misettings.display.RefreshRate.RefreshRateActivity success!")
        } catch (e: Throwable) {
            XposedBridge.log("Voyager-Test: CustomRefreshRatesForT misettings.display.RefreshRate.RefreshRateActivity failed!")
            XposedBridge.log(e)
        }

    }
}
