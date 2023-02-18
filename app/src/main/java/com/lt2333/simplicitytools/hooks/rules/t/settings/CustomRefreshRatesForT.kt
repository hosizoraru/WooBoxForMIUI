package com.lt2333.simplicitytools.hooks.rules.t.settings

import com.github.kyuubiran.ezxhelper.utils.*
import android.content.Intent
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object CustomRefreshRatesForT : HookRegister(){
    override fun init() = hasEnable("custom_refresh_rate") {

        findMethod("com.xiaomi.misettings.display.RefreshRate.NewRefreshRateFragment"){
            name == "b" &&
                    paramCount == 1 &&
                    parameterTypes[0] == Boolean::class.java
        }.hookReturnConstant(true)
    }
}
