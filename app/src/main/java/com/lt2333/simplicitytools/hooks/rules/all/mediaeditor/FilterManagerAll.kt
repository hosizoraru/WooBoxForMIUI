package com.lt2333.simplicitytools.hooks.rules.all.mediaeditor

import com.github.kyuubiran.ezxhelper.utils.findField
import com.github.kyuubiran.ezxhelper.utils.putObject
import com.lt2333.simplicitytools.hooks.apps.MediaEditor
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister


object FilterManagerAll : HookRegister() {
    override fun init() = hasEnable("filter_manager"){
        when {
            MediaEditor.versionCode < 4197441 -> {
                "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
                    getDefaultClassLoader(), "getFilterCategory"
                ) {
                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
                    it.thisObject.putObject(field, "wayne")
                }
            }
            MediaEditor.versionCode in 4197441 until 4326754 -> {
                "b6.b".hookBeforeMethod(
                    getDefaultClassLoader(), "g"
                ) {
                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
                    it.thisObject.putObject(field, "wayne")
                }
            }
            // MediaEditor.versionCode >= 4326754L
            MediaEditor.versionCode >= 4326754 -> {
                "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
                    getDefaultClassLoader(), "g"
                ) {
                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
                    it.thisObject.putObject(field, "wayne")
                }
            }
        }

//        when (MediaEditor.versionName) {
//            "1.0.3.2.1" -> {
//                "b6.b".hookBeforeMethod(
//                    getDefaultClassLoader(), "g"
//                ) {
//                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                    it.thisObject.putObject(field, "wayne")
//                }
//            }
//            "1.2.1.11.2" -> {
//                "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
//                    getDefaultClassLoader(), "g"
//                ) {
//                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                    it.thisObject.putObject(field, "wayne")
//                }
//            }
//            else -> {
//                "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
//                    getDefaultClassLoader(), "getFilterCategory"
//                ) {
//                    val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                    it.thisObject.putObject(field, "wayne")
//                }
//            }
//        }

//        try {
//            // old version
//            "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
//                getDefaultClassLoader(), "getFilterCategory"
//            ) {
//                val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                it.thisObject.putObject(field, "wayne")
//            }
//        } catch (e: Throwable) {
//            Log.ex("Voyager-Test: Hook old version MediaEditor Fail!")
//        }
//
//        try {
//            // 1.0.3.2.1
//            "b6.b".hookBeforeMethod(
//                getDefaultClassLoader(), "g"
//            ) {
//                val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                it.thisObject.putObject(field, "wayne")
//            }
//        } catch (e: Throwable) {
//            Log.ex("Voyager-Test: Hook version 1.0.3.2.1 MediaEditor Fail!")
//        }
//
//        try {
//            // 1.2.1.11.2
//            "com.miui.gallery.editor.photo.core.imports.filter.FilterManager".hookBeforeMethod(
//                getDefaultClassLoader(), "g"
//            ) {
//                val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
//                it.thisObject.putObject(field, "wayne")
//            }
//        } catch (e: Throwable) {
//            Log.ex("Voyager-Test: Hook version 1.2.1.11.2 MediaEditor Fail!")
//        }
    }
}