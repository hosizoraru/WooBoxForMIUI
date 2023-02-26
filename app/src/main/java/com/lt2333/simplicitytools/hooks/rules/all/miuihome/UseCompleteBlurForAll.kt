package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.callStaticMethod
import com.lt2333.simplicitytools.utils.findClass
import com.lt2333.simplicitytools.utils.hookBeforeMethod
import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getObjectField
import android.app.Activity
import android.view.MotionEvent
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore

object UseCompleteBlurForAll : HookRegister() {
    override fun init() = hasEnable("miuihome_task_view_card_size_binding") {
        val blurUtilsClass = "com.miui.home.launcher.common.BlurUtils".findClass()
        val navStubViewClass = "com.miui.home.recents.NavStubView".findClass()

        findMethod(blurUtilsClass) {
            name == "getBlurType"
        }.hookBefore {
            it.result = 2
        }
    }
}