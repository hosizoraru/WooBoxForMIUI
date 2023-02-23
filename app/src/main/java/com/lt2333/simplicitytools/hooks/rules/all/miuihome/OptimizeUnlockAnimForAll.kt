package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object OptimizeUnlockAnimForAll : HookRegister() {

    override fun init() = hasEnable("optimize_unlock_anim") {

        findMethod("com.miui.home.launcher.compat.UserPresentAnimationCompatV12Phone") {
            name == "getSpringAnimator" && parameterCount == 6
        }.hookBefore {
            it.args[4] = 0.5f
            it.args[5] = 0.5f
        }
    }

}