package com.lt2333.simplicitytools.hooks.rules.t.android

import android.content.ContentResolver
import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge

object RemoveSmallWindowRestrictionsForT : HookRegister() {
    override fun init() = hasEnable("disable_flag_secure") {
        // 强制所有活动设为可以调整大小
        findMethod("com.android.server.wm.Task") {
            name == "isResizeable"
        }.hookBefore {
            it.result = true
        }

        findMethod("android.util.MiuiMultiWindowAdapter") {
            name == "getFreeformBlackList"
        }.hookAfter {
            it.result = (it.result as MutableList<*>).apply { clear() }
        }

        findMethod("android.util.MiuiMultiWindowAdapter") {
            name == "getFreeformBlackListFromCloud" && parameterTypes[0] == Context::class.java
        }.hookAfter {
            it.result = (it.result as MutableList<*>).apply { clear() }
        }

        findMethod("android.util.MiuiMultiWindowUtils") {
            name == "supportFreeform"
        }.hookAfter {
            it.result = true
        }

        findMethod("android.util.MiuiMultiWindowUtils") {
            name == "isForceResizeable"
        }.hookReturnConstant(true)

        findAllMethods("com.android.server.wm.WindowManagerService\$SettingsObserver") {
            name == "onChange"
        }.hookAfter { param ->
            val this0 = param.thisObject.javaClass.field("this\$0").get(param.thisObject)
            val mAtmService = this0.javaClass.field("mAtmService").get(this0)
            mAtmService.javaClass.field("mDevEnableNonResizableMultiWindow").setBoolean(mAtmService,true)
        }

        findAllMethods("com.android.server.wm.WindowManagerService\$SettingsObserver") {
            name == "updateDevEnableNonResizableMultiWindow"
        }.hookAfter { param ->
            val this0 = param.thisObject.javaClass.field("this\$0").get(param.thisObject)
            val mAtmService = this0.javaClass.field("mAtmService").get(this0)
            mAtmService.javaClass.field("mDevEnableNonResizableMultiWindow").setBoolean(mAtmService,true)
        }

        findAllMethods("com.android.server.wm.ActivityTaskManagerService") {
            name == "retrieveSettings"
        }.hookAfter { param ->
            param.thisObject.javaClass.field("mDevEnableNonResizableMultiWindow")
                .setBoolean(param.thisObject, true)
        }
    }

}