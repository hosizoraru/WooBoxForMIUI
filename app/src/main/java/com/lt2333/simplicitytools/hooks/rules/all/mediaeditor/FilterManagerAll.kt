package com.lt2333.simplicitytools.hooks.rules.all.mediaeditor

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.field
import com.github.kyuubiran.ezxhelper.utils.findField
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.github.kyuubiran.ezxhelper.utils.putObject
import com.lt2333.simplicitytools.hooks.apps.MediaEditor
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.YuKongA.hookBeforeMethod
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister


object FilterManagerAll : HookRegister() {
    override fun init() = hasEnable("filter_manager"){
        var qwq = ""
        var qaq = ""
        when {
            MediaEditor.versionCode < 4197441 -> {
                qwq = "com.miui.gallery.editor.photo.core.imports.filter.FilterManager"
                qaq = "getFilterCategory"
            }
            MediaEditor.versionCode in 4197441 until 4326754 -> {
                qwq = "b6.b"
                qaq = "g"
            }
            // MediaEditor.versionCode >= 4326754L
            MediaEditor.versionCode in 4326754 until 4327491 -> {
                qwq = "com.miui.gallery.editor.photo.core.imports.filter.FilterManager"
                qaq = "g"
            }
            MediaEditor.versionCode > 4327491 -> {
                qwq = "com.miui.gallery.editor.photo.core.imports.filter.FilterManager"
                qaq = "onCreate"
            }
        }
        qwq.hookBeforeMethod(
            getDefaultClassLoader(), qaq
        ) {
            val field = findField("android.os.Build") { type == String::class.java && name == "DEVICE" }
            it.thisObject.putObject(field, "wayne")
//            loadClass("android.os.Build").field("DEVICE", true, String::class.java)
//                .set(null, "wayne")
        }


//        findMethod(qwq) {
//            name == qaq
//        }.hookBefore {
////            param.thisObject.javaClass.field("DEVICE",true).setBoolean(param.thisObject, true)
//            loadClass("android.os.Build").field("DEVICE", true, String::class.java)
//                .set(null, "wayne")
//            Log.ix("Cemiuiler: HookBeforeMethod Hook success!")
//        }

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