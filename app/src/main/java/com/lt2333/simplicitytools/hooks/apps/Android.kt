package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.android.AllowUntrustedTouchesForAll
import com.lt2333.simplicitytools.hooks.rules.all.android.DeleteOnPostNotificationForAll
import com.lt2333.simplicitytools.hooks.rules.all.android.DisableFlagSecureForAll
import com.lt2333.simplicitytools.hooks.rules.all.corepatch.CorePatchMainHook
import com.lt2333.simplicitytools.hooks.rules.all.BypassSignCheckMainHook
import com.lt2333.simplicitytools.hooks.rules.s.android.*
import com.lt2333.simplicitytools.hooks.rules.t.android.*
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Android : AppRegister() {
    override val packageName: String = "android"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        //核心破解
        CorePatchMainHook().handleLoadPackage(lpparam)
        BypassSignCheckMainHook().handleLoadPackage(lpparam)
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    DisableFlagSecureForAll, //允许截图
                    DeleteOnPostNotificationForAll, //上层显示
                    RemoveSmallWindowRestrictionsForT, //解除小窗限制
                    MaxWallpaperScaleForT, //壁纸缩放比例
                    SystemPropertiesHookForT, //SystemPropertiesHook
                    AllowUntrustedTouchesForAll, //允许不受信任的触摸
                    GetMinimumSignatureSchemeVersionForTargetSdkForT, //BypassSignCheck
                    KillDomainVerificationForT, //KillDomainVerification
                    SideHideFreeformForS,//解锁贴边小窗
                )
            }
            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    DisableFlagSecureForAll, //允许截图
                    DeleteOnPostNotificationForAll, //上层显示
                    RemoveSmallWindowRestrictionsForS, //解除小窗限制
                    MaxWallpaperScaleForS, //壁纸缩放比例
                    SystemPropertiesHookForS, //SystemPropertiesHook
                    AllowUntrustedTouchesForAll, //允许不受信任的触摸
                    GetMinimumSignatureSchemeVersionForTargetSdkForT, //BypassSignCheck
                    KillDomainVerificationForS, //KillDomainVerification
                    SideHideFreeformForS,//解锁贴边小窗
                )
            }
        }
    }
}