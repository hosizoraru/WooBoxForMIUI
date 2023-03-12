package com.lt2333.simplicitytools.hooks.rules.all.taplus

import android.graphics.Bitmap
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object SuperImageForAll : HookRegister() {

    override fun init() = hasEnable("super_image") {
        findMethod("com.miui.contentextension.utils.SuperImageUtils") {
            name == "isSupportSuperImage"
        }.hookReturnConstant(true)
        findMethod("com.miui.contentextension.utils.SuperImageUtils") {
            name == "isBitmapSupportSuperImage" &&
                    parameterTypes[0] == Bitmap::class.java
        }.hookReturnConstant(true)
    }
}