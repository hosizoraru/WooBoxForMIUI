package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object AlwaysBlurWallpaper : HookRegister() {
    override fun init() = hasEnable("home_blur_wallpaper") {
        val value = getInt("home_blur_radius", 100)
        findMethod("com.miui.home.launcher.common.BlurUtils") {
            name == "fastBlur" && parameterCount == 4
        }.hookBefore {
            it.args[0] = value.toFloat() / 100
            it.args[2] = true
        }
    }
}