package com.lt2333.simplicitytools.hooks.rules.s.systemui

import android.view.View
import android.widget.LinearLayout
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObject
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object RemoveLockScreenCameraForS : HookRegister() {

    override fun init() {
        //屏蔽右下角组件显示
        findMethod("com.android.systemui.statusbar.phone.KeyguardBottomAreaView") {
            name == "onFinishInflate"
        }.hookAfter {
            hasEnable("remove_lock_screen_camera") {
                (it.thisObject.getObject("mRightAffordanceViewLayout") as LinearLayout).visibility =
                    View.GONE
            }
        }

        //屏蔽滑动撞墙动画
        findMethod("com.android.keyguard.KeyguardMoveRightController") {
            name == "onTouchMove" && parameterCount == 2
        }.hookBefore {
            hasEnable("remove_lock_screen_camera") {
                it.result = false
            }
        }
        findMethod("com.android.keyguard.KeyguardMoveRightController") {
            name == "reset"
        }.hookBefore {
            hasEnable("remove_lock_screen_camera") {
                it.result = null
            }
        }
    }

}

