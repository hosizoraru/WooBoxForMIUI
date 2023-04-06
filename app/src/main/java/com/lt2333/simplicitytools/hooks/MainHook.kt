package com.lt2333.simplicitytools.hooks

import com.lt2333.simplicitytools.BuildConfig
import com.lt2333.simplicitytools.hooks.apps.*
import com.lt2333.simplicitytools.hooks.rules.all.corepatch.CorePatchMainHook
import com.lt2333.simplicitytools.hooks.rules.all.wini.hooks.FrameworkHooks
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.EasyXposedInit
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : EasyXposedInit() {
    private var prefs = XSharedPreferences(BuildConfig.APPLICATION_ID, "config")

    override val registeredApp: List<AppRegister> = listOf(
        Android, // Android
        SystemUI, // 系统界面
        PowerKeeper, // 电量与性能
        MiuiHome, // 桌面
        SecurityCenter, // 手机管家
        Gallery, // 相册
        MediaEditor, // 相册编辑
        Updater, // 系统更新
        Settings, // 设置
        ThemeManager, // 主题壁纸
        ScreenShot, // 截屏
        Cast, // 投屏
        RearDisplay, // 背屏
        MiShare, // 小米互传
        DeskClock, // 时钟
        PackageInstaller, // 应用包管理组件
        MiSettings,// 小米设置
        Taplus,// 传送门
        PersonalAssistant,// 智能助理
        Lbe, // 权限管理服务
        Externalstorage, // 外部存储服务
        VoiceAssist, // 小爱同学
        GuardProvider, // Miui安全组件
        Scanner, // 小爱视觉
        Aireco, // 小爱建议
        Market, // 应用商店
        Browser, // 浏览器
        TouchNotes, // 享做笔记
    )

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (prefs.getBoolean("main_switch", true)) {
            super.handleLoadPackage(lpparam)
        }
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        super.initZygote(startupParam)
        CorePatchMainHook().initZygote(startupParam)
        hasEnable("blur_when_show_shortcut_menu") {
            if (startupParam != null) {
                FrameworkHooks().initZygote(startupParam)
            }
        }
    }

    override fun handleInitPackageResources(resparam: InitPackageResourcesParam?) {
        if (prefs.getBoolean("main_switch", true)) {
            super.handleInitPackageResources(resparam)
        }
    }

}