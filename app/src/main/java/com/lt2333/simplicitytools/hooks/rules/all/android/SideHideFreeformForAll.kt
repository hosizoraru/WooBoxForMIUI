package com.lt2333.simplicitytools.hooks.rules.all.android

import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable

object SideHideFreeformForAll : HookRegister() {
    override fun init() = hasEnable("side_hide_freeform") {
        findMethod("android.util.MiuiMultiWindowUtils") {
            name == "multiFreeFormSupported"
        }.hookBefore {
            val ex = Throwable()
            val stackTrace = ex.stackTrace
            var mResult = true
            for (i in stackTrace) {
                if (i.className == "com.android.server.wm.MiuiFreeFormGestureController\$FreeFormReceiver") {
                    mResult = false
                    break
                }
            }
            it.result = mResult
        }
    }
}