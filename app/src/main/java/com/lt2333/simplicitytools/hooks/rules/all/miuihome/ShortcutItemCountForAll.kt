package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.utils.callMethod
import com.lt2333.simplicitytools.utils.hasEnable

import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object ShortcutItemCountForAll : HookRegister() {

    override fun init() = hasEnable("shortcut_remove_restrictions") {

        findMethod("com.miui.home.launcher.shortcuts.AppShortcutMenu") { name == "getMaxCountInCurrentOrientation" }.hookAfter {
            it.result = 20
        }
        findMethod("com.miui.home.launcher.shortcuts.AppShortcutMenu") { name == "getMaxShortcutItemCount" }.hookAfter {
            it.result = 20
        }
        findMethod("com.miui.home.launcher.shortcuts.AppShortcutMenu") { name == "getMaxVisualHeight" }.hookAfter {
            it.result = it.thisObject.callMethod("getItemHeight")
        }
    }
}