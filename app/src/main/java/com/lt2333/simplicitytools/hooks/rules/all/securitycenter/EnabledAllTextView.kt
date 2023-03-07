package com.lt2333.simplicitytools.hooks.rules.all.securitycenter

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import com.lt2333.simplicitytools.utils.chsbuffer.*
import com.lt2333.simplicitytools.utils.hasEnable

object EnabledAllTextView : Hook() {
    override fun init(classLoader: ClassLoader) {
//        if (!xPrefs.getBoolean("enable_all_text_view", true))
//            return
        // this exposed lots of disabled settings such as set system app wlan restrict
        hasEnable("enable_all_text_view") {
            XposedHelpers.findAndHookMethod(
                "android.widget.TextView",
                classLoader,
                "setEnabled",
                Boolean::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.args[0] = true
                    }
                })
        }
    }
}