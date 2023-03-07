package com.lt2333.simplicitytools.hooks.rules.all.personalassistant

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.*

object BlurWhenGotoMinusOne : HookRegister() {
    override fun init() = hasEnable("personalassistant_minus_one_blur") {
        val deviceAdapter = "com.miui.personalassistant.device.DeviceAdapter".findClass()
        val foldableDeviceAdapter = "com.miui.personalassistant.device.FoldableDeviceAdapter".findClass()
        deviceAdapter.hookBeforeAllMethods("create") {
            it.result = foldableDeviceAdapter.new(it.args[0])
        }
        foldableDeviceAdapter.hookBeforeMethod("onOpened") {
            it.thisObject.setObjectField("mScreenSize", 3)
        }
        foldableDeviceAdapter.replaceMethod("onScroll", Float::class.java) {
            val f = it.args[0] as Float
            val i = (f * 100.0f).toInt()
            val mCurrentBlurRadius: Int = it.thisObject.getIntField("mCurrentBlurRadius")
            if (mCurrentBlurRadius != i) {
                if (mCurrentBlurRadius <= 0 || i >= 0) {
                    it.thisObject.setObjectField("mCurrentBlurRadius", i)
                } else {
                    it.thisObject.setObjectField("mCurrentBlurRadius", 0)
                }
                it.thisObject.callMethod("blurOverlayWindow", mCurrentBlurRadius)
            }
        }
    }
}