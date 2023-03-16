package com.lt2333.simplicitytools.hooks.rules.all.gallery

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object EnableRemover2ForAll : HookRegister() {
    override fun init() = hasEnable("enable_remover2") {
        findMethod("com.miui.gallery.editor.photo.app.remover2.sdk.Remover2CheckHelper") {
            name == "isRemover2Support"
        }.hookAfter {
            it.result = true
        }
    }
}