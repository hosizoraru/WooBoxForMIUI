package com.lt2333.simplicitytools.hooks.rules.all.cast

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.paramCount
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object ForceSupportSendAppForAll : HookRegister() {

    override fun init() = hasEnable("force_support_send_app") {
        findMethod("com.xiaomi.mirror.synergy.MiuiSynergySdk") {
            name == "isSupportSendApp"
        }.hookAfter {
            it.result = true
        }
        findMethod("com.miui.circulate.api.protocol.miuiplus.MiuiPlusServiceController") {
            name == "isSupportSendApp"
        }.hookAfter {
            it.result = true
        }
        findMethod("com.miui.circulate.world.permission.method.PermissionCheck\\\$BaseCheck") {
            name == "check"
        }.hookAfter {
            it.result = true
        }
        findMethod("com.xiaomi.mirror.RemoteDeviceInfo") {
            name == "isSupportSendApp"
        }.hookAfter {
            it.result = true
        }
        findMethod("com.xiaomi.mirror.synergy.MiuiSynergySdk") {
            name == "queryRemoteDevices" && parameterCount == 3
        }.hookBefore {
            it.result = null
            it.args[0] = null
            it.args[1] = null
        }
    }
}