package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object UnlockHDREnhanceForAll : HookRegister() {

    override fun init() = hasEnable("Unlock_HDR_Enhance") {
        // 超动态显示
        findMethod("com.miui.gallery.domain.DeviceFeature") {
            name == "isSupportHDREnhance"
        }.hookAfter {
            it.result = true
        }
    }

}