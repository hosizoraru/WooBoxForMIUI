package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnablePhotoMovieForAll : HookRegister() {
    override fun init() = hasEnable("enable_photo_movie") {
        findMethod("com.miui.mediaeditor.api.MediaEditorApiHelper") {
            name == "isPhotoMovieAvailable"
        }.hookAfter {
            it.result = true
        }
    }
}