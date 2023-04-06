package com.lt2333.simplicitytools.hooks.rules.all.mipad

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object TouchNotesLoginAndVip : HookRegister() {
    override fun init() {
        val qwq = "com.newskyer.paint.core.PanelUserManager"
        findMethod(qwq) {
            name == "isLogin"
        }.hookAfter {
            hasEnable("touch_notes_is_login") {
                it.result = true
            }
        }
        findMethod(qwq) {
            name == "isPro"
        }.hookAfter {
            hasEnable("touch_notes_is_pro") {
                it.result = true
            }
        }
    }
}