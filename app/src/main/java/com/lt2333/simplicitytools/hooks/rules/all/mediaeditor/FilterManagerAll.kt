package com.lt2333.simplicitytools.hooks.rules.all.mediaeditor

import com.github.kyuubiran.ezxhelper.utils.findField
import com.github.kyuubiran.ezxhelper.utils.putObject
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister


object FilterManagerAll : HookRegister() {
    override fun init() = hasEnable("filter_manager"){
        // old version
        "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
            getDefaultClassLoader(), "getFilterCategory"
        ) {
            val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
            it.thisObject.putObject(field, "wayne")
        }
        // 1.0.3.2.1
        "b6.b".hookBeforeMethod(
            getDefaultClassLoader(), "g"
        ) {
            val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
            it.thisObject.putObject(field, "wayne")
        }
    }
}