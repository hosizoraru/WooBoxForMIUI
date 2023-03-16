package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable

object BaseMiuiMultiFingerGesture : HookRegister() {
    override fun init() = hasEnable("set_gesture_need_finger_num_to_4") {
        findMethod("com.miui.server.input.gesture.multifingergesture.gesture.BaseMiuiMultiFingerGesture") {
            name == "getFunctionNeedFingerNum"
        }.hookReturnConstant(4)
    }
}