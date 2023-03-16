package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableOcrFormForAll : HookRegister() {
    override fun init() = hasEnable("enable_ocr_form") {
        val cls = "com.miui.gallery.util.RecognizeFormUtil"
        cls.hookBeforeMethod(
            getDefaultClassLoader(), "isAvailable"
        ) {
            it.result = true
        }
    }
}