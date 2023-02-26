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

object UseCompleteBlurFixForAll : HookRegister() {
    override fun init() = hasEnable("miuihome_complete_blur_fix") {
        val blurUtilsClass = "com.miui.home.launcher.common.BlurUtils".findClass()
        val navStubViewClass = "com.miui.home.recents.NavStubView".findClass()
        navStubViewClass.hookBeforeMethod("appTouchResolution", MotionEvent::class.java) {
            val mLauncher = it.thisObject.getObjectField("mLauncher") as Activity?
            blurUtilsClass.callStaticMethod("fastBlurDirectly", 1.0f, mLauncher?.window)
        }
    }
}