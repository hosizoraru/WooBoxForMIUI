package com.lt2333.simplicitytools.hooks.rules.all.scanner

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableDocPpt : HookRegister() {
    override fun init() = hasEnable("enable_doc_ppt") {
        findMethod("com.xiaomi.scanner.settings.FeatureManager") {
            name == "isPPTModuleAvailable"
        }.hookBefore {
            it.result = true
        }
    }
}