package com.lt2333.simplicitytools.hooks.rules.all.maxmipad

import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import android.os.Build
import android.view.MotionEvent
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable

object GestureOperationHelper : HookRegister() {
    override fun init() = hasEnable("set_gesture_need_finger_num_to_4") {
        val clazzGestureOperationHelper =
            loadClass("com.miui.home.recents.GestureOperationHelper")
        findMethod(clazzGestureOperationHelper) {
            name == "isThreePointerSwipeLeftOrRightInScreen" &&
                    paramCount == 2 &&
                    parameterTypes[0] == MotionEvent::class.java &&
                    parameterTypes[1] == Int::class.java
        }.hookBefore { param ->
            val motionEvent = param.args[0] as MotionEvent
            val swipeFlag = param.args[1] as Int
            val flagSwipeLeft = clazzGestureOperationHelper.field("SWIPE_DIRECTION_LEFT", true)
                .getInt(null)
            val flagSwipeRight = clazzGestureOperationHelper.field("SWIPE_DIRECTION_RIGHT", true)
                .getInt(null)
            val flagsSwipeLeftAndRight = setOf(flagSwipeLeft, flagSwipeRight)
            val z = if (motionEvent.device == null) {
                true
            } else {
                motionEvent.device.sources and 4098 == 4098
            }
            param.result =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && z && (swipeFlag in flagsSwipeLeftAndRight) && motionEvent.pointerCount == 4
        }
    }
}