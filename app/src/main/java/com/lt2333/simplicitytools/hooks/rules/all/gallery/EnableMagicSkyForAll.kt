package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableMagicSkyForAll : HookRegister() {
    override fun init() = hasEnable("enable_magic_sky") {
        findMethod("com.miui.gallery.util.FilterSkyEntranceUtils") {
            name == "showSingleFilterSky"
        }.hookAfter {
            it.result = true
        }
    }
}