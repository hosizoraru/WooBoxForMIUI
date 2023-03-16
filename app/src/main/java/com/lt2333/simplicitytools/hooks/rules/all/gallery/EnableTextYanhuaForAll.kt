package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableTextYanhuaForAll : HookRegister() {
    override fun init() = hasEnable("enable_text_yanhua") {
        findMethod("com.miui.gallery.domain.SkyCheckHelper") {
            name == "isSupportTextYanhua"
        }.hookAfter {
            it.result = true
        }
    }
}