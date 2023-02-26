package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.miuihome.*
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object MiuiHome : AppRegister() {
    override val packageName: String = "com.miui.home"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    AlwaysShowStatusBarClockForAll, //时钟显示时钟
                    DoubleTapToSleepForAll, //双击锁屏
                    MaxFreeFormmForAll,//多小窗
                    DisableRecentViewWallpaperDarkeningForAll, //取消后台壁纸压暗效果
                    ModifyRecentViewRemoveCardAnimForAll, //横向排布后台划卡动画
                    ScrollIconNameForAll, //滚动显示应用标题
                    RemoveSmallWindowRestrictionForMiuiHomeForAll, //取消小窗限制
                    ShortcutAddSmallWindowForAll, //快捷菜单添加小窗
                    UseTransitionAnimationForAll, // 强制使用miui小部件动画
                    OverlapModeForAll, // Fold2 样式 负一屏
                    AllowMoveAllWidgetToMinusForAll, // 允许移动所有小部件到负一屏
                    AlwaysShowMiuiWidget, // 在安卓小部件页面显示 MIUI 小部件
                    OptimizeUnlockAnimForAll, // 优化解锁动画
                    IconCornerForAll, //  中等图标圆角跟随
                    UnlockHotseatIconForAll, // 解锁底栏图标数量限制
                    ShortcutItemCountForAll, // 解除Shortcuts数量限制
                    AnimDurationRatioForAll, // 自定义动画速度
                    TaskViewCardSizeForAll, // 自定义最近任务卡片大小
                    SetDeviceLevelForAll, // 解除设备限制
                    UseComPleteBlurForAll, // 完整模糊
                    UseComPleteBlurFixForAll, // 完整模糊补全
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    AlwaysShowStatusBarClockForAll, //时钟显示时钟
                    DoubleTapToSleepForAll, //双击锁屏
                    MaxFreeFormmForAll,//多小窗
                    DisableRecentViewWallpaperDarkeningForAll, //取消后台壁纸压暗效果
                    ModifyRecentViewRemoveCardAnimForAll, //横向排布后台划卡动画
                    ScrollIconNameForAll, //滚动显示应用标题
                    RemoveSmallWindowRestrictionForMiuiHomeForAll, //取消小窗限制
                    ShortcutAddSmallWindowForAll, //快捷菜单添加小窗
                    UseTransitionAnimationForAll, // 强制使用miui小部件动画
                    OverlapModeForAll, // Fold2 样式 负一屏
                    AllowMoveAllWidgetToMinusForAll, // 允许移动所有小部件到负一屏
                    AlwaysShowMiuiWidget, // 在安卓小部件页面显示 MIUI 小部件
                    OptimizeUnlockAnimForAll, // 优化解锁动画
                    IconCornerForAll, //  中等图标圆角跟随
                    UnlockHotseatIconForAll, // 解锁底栏图标数量限制
                    ShortcutItemCountForAll, // 解除Shortcuts数量限制
                    AnimDurationRatioForAll, // 自定义动画速度
                    TaskViewCardSizeForAll, // 自定义最近任务卡片大小
                    SetDeviceLevelForAll, // 解除设备限制
                    UseComPleteBlurForAll, // 完整模糊
                    UseComPleteBlurFixForAll, // 完整模糊补全
                )
            }
        }
    }
}