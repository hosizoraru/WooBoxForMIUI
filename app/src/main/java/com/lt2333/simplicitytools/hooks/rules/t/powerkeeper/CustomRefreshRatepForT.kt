package com.lt2333.simplicitytools.hooks.rules.t.powerkeeper

import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.*
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object CustomRefreshRatepForT : HookRegister(){
    override fun init() = hasEnable("custom_refresh_rate") {

//        findMethod("com.miui.powerkeeper.statemachine.DisplayFrameSetting"){
//            name == "parseCustomModeSwitchFromDb" &&
//                    paramCount == 1 &&
//                    parameterTypes[0] == String::class.java
//        }.hookReturnConstant(true)
        try {
            "com.miui.powerkeeper.statemachine.DisplayFrameSetting".hookBeforeMethod("parseCustomModeSwitchFromDb",String::class.java) {
                it.thisObject.setObjectField("mIsCustomFpsSwitch", true)
                it.thisObject.setObjectField("fucSwitch", true)
            }
            XposedBridge.log("Voyager-Test: Hook CustomRefreshRatepForT success!")
        } catch (e: Throwable) {
            XposedBridge.log("Voyager-Test: Hook CustomRefreshRatepForT failed!")
            XposedBridge.log(e)
        }
    }
}