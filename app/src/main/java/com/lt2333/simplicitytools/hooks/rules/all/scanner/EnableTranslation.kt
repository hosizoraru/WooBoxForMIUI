package com.lt2333.simplicitytools.hooks.rules.all.scanner

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableTranslation : HookRegister() {
    override fun init() = hasEnable("enable_translation") {
        val qwq = "com.xiaomi.scanner.settings.FeatureManager"
        findMethod(qwq) {
            name == "isAddTranslation"
        }.hookBefore {
            it.result = true
        }
        findMethod(qwq) {
            name == "isTranslationModuleAvailable"
        }.hookBefore {
            it.result = true
        }
    }
}