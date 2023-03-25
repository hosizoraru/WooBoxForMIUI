package com.lt2333.simplicitytools.hooks.rules.all.scanner

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableOcr : HookRegister() {
    override fun init() = hasEnable("enable_ocr2") {
        findMethod("com.xiaomi.scanner.settings.FeatureManager") {
            name == "isAddTextExtractionFunction"
        }.hookBefore {
            it.result = true
        }
    }
}