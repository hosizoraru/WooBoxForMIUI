package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object AnimDurationRatioForAll : HookRegister() {
    override fun init() = hasEnable("miuihome_anim_ratio_binding") {
        val value1 = getInt("miuihome_anim_ratio", 100).toFloat() / 100f
        val value2 = getInt("miuihome_anim_ratio_recent", 100).toFloat() / 100f

        findMethod("com.miui.home.recents.util.RectFSpringAnim") {
            name == "getModifyResponse"
        }.hookBefore {
            it.result = it.args[0] as Float * value1
        }

        findMethod("com.miui.home.launcher.common.DeviceLevelUtils") {
            name == "getDeviceLevelTransitionAnimRatio"
        }.hookBefore {
            it.result = value2
        }
    }
}