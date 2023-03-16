package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableOcrAll : HookRegister() {
    override fun init() = hasEnable("enable_ocr") {
        val cls = "com.miui.gallery.ui.photoPage.ocr.OCRHelper"
        cls.hookBeforeMethod(
            getDefaultClassLoader(), "isSupportLocalOCR"
        ) {
            it.result = true
        }
        cls.hookBeforeMethod(
            getDefaultClassLoader(), "isSupportOCR"
        ) {
            it.result = true
        }
    }
}