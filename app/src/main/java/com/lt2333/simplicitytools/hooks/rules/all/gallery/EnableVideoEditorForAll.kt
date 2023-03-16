package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableVideoEditorForAll : HookRegister() {
    override fun init() = hasEnable("enable_video_editor") {
        findMethod("com.miui.mediaeditor.api.MediaEditorApiHelper") {
            name == "isVideoEditorAvailable"
        }.hookAfter {
            it.result = true
        }
        findMethod("com.miui.mediaeditor.api.MediaEditorApiHelper") {
            name == "isVlogAvailable"
        }.hookAfter {
            it.result = true
        }
    }
}