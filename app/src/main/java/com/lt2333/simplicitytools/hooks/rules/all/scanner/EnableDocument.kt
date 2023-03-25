package com.lt2333.simplicitytools.hooks.rules.all.scanner

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableDocument : HookRegister() {
    override fun init() = hasEnable("enable_document") {
        findMethod("com.xiaomi.scanner.settings.FeatureManager") {
            name == "isAddDocumentModule"
        }.hookBefore {
            it.result = true
        }
    }
}