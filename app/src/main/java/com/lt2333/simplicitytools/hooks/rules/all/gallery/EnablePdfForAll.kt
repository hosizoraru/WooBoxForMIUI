package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnablePdfForAll : HookRegister() {
    override fun init() = hasEnable("enable_pdf") {
        "com.miui.gallery.request.PicToPdfHelper".hookBeforeMethod(
            getDefaultClassLoader(),"isPicToPdfSupport"
        ) {
            it.result = true
        }
    }
}