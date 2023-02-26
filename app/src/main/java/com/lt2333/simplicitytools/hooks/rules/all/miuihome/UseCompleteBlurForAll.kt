package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import android.app.Activity
import android.view.MotionEvent
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.*

object UseCompleteBlurForAll : HookRegister() {
    override fun init() = hasEnable("miuihome_task_view_card_size_binding") {
        val blurUtilsClass = "com.miui.home.launcher.common.BlurUtils".findClass()
        val navStubViewClass = "com.miui.home.recents.NavStubView".findClass()

        findMethod(blurUtilsClass) {
            name == "getBlurType"
        }.hookBefore {
            it.result = 2
        }
        hasEnable("miuihome_complete_blur_fix") {
            navStubViewClass.hookBeforeMethod("appTouchResolution", MotionEvent::class.java) {
                val mLauncher = it.thisObject.getObjectField("mLauncher") as Activity?
                blurUtilsClass.callStaticMethod("fastBlurDirectly", 1.0f, mLauncher?.window)
            }
        }
    }
}