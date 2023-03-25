package com.lt2333.simplicitytools.hooks.rules.all.scanner

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableExcel : HookRegister() {
    override fun init() = hasEnable("enable_excel") {
        val qwq = "com.xiaomi.scanner.settings.FeatureManager"
        findMethod("com.xiaomi.scanner.util.SPUtils") {
            name == "getFormModule"
        }.hookBefore {
            it.result = true
        }
        findMethod(qwq) {
            name == "isSupportForm"
        }.hookBefore {
            it.result = true
        }
        findMethod(qwq) {
            name == "isAddFormRecognitionFunction"
        }.hookBefore {
            it.result = true
        }
    }
}