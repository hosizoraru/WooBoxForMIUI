package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.securitycenter.*
import com.lt2333.simplicitytools.hooks.rules.all.wini.hooks.WiniMainHook
import com.lt2333.simplicitytools.hooks.rules.s.securitycenter.ShowBatteryTemperatureForS
import com.lt2333.simplicitytools.hooks.rules.t.securitycenter.ShowBatteryTemperatureForT
import com.lt2333.simplicitytools.utils.chsbuffer.hooks
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object SecurityCenter : AppRegister() {
    override val packageName: String = "com.miui.securitycenter"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        WiniMainHook().handleLoadPackage(lpparam)
        when (lpparam.packageName) {
            "com.miui.securitycenter" -> hooks(
                lpparam,
                SomeHost, //miuiqol
            )
        }
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                RemoveMacroBlacklist().handleLoadPackage(lpparam)
                autoInitHooks(
                    lpparam,
                    SkipWaitingTimeForAll, //跳过 5/10秒等待时间
                    RemoveMacroBlacklistForAll1, //去除自动连招黑名单
                    ShowBatteryTemperatureForT, //显示电池温度
                    RemoveOpenAppConfirmationPopupForAll, //去除打开应用弹窗
                    RemoveConversationBubbleSettingsRestrictionForAll, //去除气泡通知应用限制
                )
            }

            Build.VERSION_CODES.S -> {
                RemoveMacroBlacklist().handleLoadPackage(lpparam)
                autoInitHooks(
                    lpparam,
                    SkipWaitingTimeForAll, //跳过 5/10秒等待时间
                    RemoveMacroBlacklistForAll1, //去除自动连招黑名单
                    ShowBatteryTemperatureForS, //显示电池温度
                    RemoveOpenAppConfirmationPopupForAll, //去除打开应用弹窗
                    RemoveConversationBubbleSettingsRestrictionForAll, //去除气泡通知应用限制
                )
            }
        }
    }
}