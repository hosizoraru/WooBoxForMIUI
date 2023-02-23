package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*

object IconCornerForAll : HookRegister() {

    override fun init() = hasEnable("icon_corner") {

        findMethod("com.miui.home.launcher.bigicon.BigIconUtil") {
            name == "getCroppedFromCorner" && parameterCount == 4
        }.hookBefore {
            it.args[0] = 2
            it.args[1] = 2
        }

        findMethod(
            "com.miui.home.launcher.maml.MaMlHostView"
        ) {
            name == "getCornerRadius"
        }.hookBefore {
            it.result = it.thisObject.getObjectField("mEnforcedCornerRadius") as Float
        }

        findMethod("com.miui.home.launcher.maml.MaMlHostView") {
            name == "computeRoundedCornerRadius" && parameterCount == 1
        }.hookBefore {
            it.result = it.thisObject.getObjectField("mEnforcedCornerRadius") as Float
        }

        findMethod("com.miui.home.launcher.LauncherAppWidgetHostView") {
            name == "computeRoundedCornerRadius" && parameterCount == 1
        }.hookBefore {
            it.result = it.thisObject.getObjectField("mEnforcedCornerRadius") as Float
        }
    }

}