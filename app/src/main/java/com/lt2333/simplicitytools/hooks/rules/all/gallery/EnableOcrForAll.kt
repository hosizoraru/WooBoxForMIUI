package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableOcrForAll : HookRegister() {
    override fun init() = hasEnable("enable_ocr") {
//        val cls = "com.miui.gallery.ui.photoPage.ocr.OCRHelper"
//        cls.hookBeforeMethod(
//            getDefaultClassLoader(), "isSupportLocalOCR"
//        ) {
//            it.result = true
//        }
//        cls.hookBeforeMethod(
//            getDefaultClassLoader(), "isSupportOCR"
//        ) {
//            it.result = true
//        }
        findMethod("com.miui.gallery.ui.photoPage.ocr.OCRHelper") {
            name == "isSupportLocalOCR"
        }.hookBefore {
            it.result = true
        }
        findMethod("com.miui.gallery.ui.photoPage.ocr.OCRHelper") {
            name == "isSupportOCR"
        }.hookBefore {
            it.result = true
        }
    }
}