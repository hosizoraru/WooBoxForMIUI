package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.github.kyuubiran.ezxhelper.utils.*
import de.robv.android.xposed.XC_MethodHook

object MaxFreeFormmForAll : HookRegister() {
    override fun init() = hasEnable("max_free_form") {
        // CanTaskEnterMiniSmallWindow
        findAllMethods("com.miui.home.launcher.RecentsAndFSGestureUtils") {
            name == "canTaskEnterMiniSmallWindow"
        }.hookReturnConstant(true)
        // CanTaskEnterSmallWindow
        findAllMethods("com.miui.home.launcher.RecentsAndFSGestureUtils") {
            name == "canTaskEnterSmallWindow"
        }.hookReturnConstant(true)
        // StartSmallWindow
        var hook1: List<XC_MethodHook.Unhook>? = null
        var hook2: List<XC_MethodHook.Unhook>? = null
        findAllMethods("com.miui.home.recents.views.RecentsTopWindowCrop") {
            name == "startSmallWindow"
        }.hookBefore {
            hook1 = findAllMethods("android.util.MiuiMultiWindowUtils") {
                name == "startSmallFreeform" && paramCount == 4
            }.hookBefore {
                it.args[3] = false
                hook2 = findAllMethods("miui.app.MiuiFreeFormManager") {
                    name == "getAllFreeFormStackInfosOnDisplay"
                }.hookBefore { param ->
                    param.result = null
                }
            }
            findAllMethods("android.util.MiuiMultiWindowUtils") {
                name == "startSmallFreeform"
            }.hookAfter {
                hook2?.unhookAll()
            }
        }
        findAllMethods("com.miui.home.recents.views.RecentsTopWindowCrop") {
            name == "startSmallWindow"
        }.hookAfter {
            hook1?.unhookAll()
        }
    }
}