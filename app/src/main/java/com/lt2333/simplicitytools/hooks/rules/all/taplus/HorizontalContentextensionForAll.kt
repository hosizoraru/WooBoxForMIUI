package com.lt2333.simplicitytools.hooks.rules.all.taplus

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object HorizontalContentextensionForAll : HookRegister() {

    override fun init() = hasEnable("horizontal_contentextension") {
        findMethod("com.miui.contentextension.services.TextContentExtensionService") {
            name == "isScreenPortrait"
        }.hookAfter {
            it.result = true
        }
    }
}