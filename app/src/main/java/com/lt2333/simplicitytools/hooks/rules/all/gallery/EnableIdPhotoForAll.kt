package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableIdPhotoForAll : HookRegister() {
    override fun init() = hasEnable("enable_id_photo") {
        findMethod("com.miui.mediaeditor.api.MediaEditorApiHelper") {
            name == "isIDPhotoAvailable"
        }.hookAfter {
            it.result = true
        }
    }
}