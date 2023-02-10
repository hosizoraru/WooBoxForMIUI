package com.lt2333.simplicitytools.hooks.rules.t.android

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookMethod
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.hasEnable
import de.robv.android.xposed.XposedBridge


object GetMinimumSignatureSchemeVersionForTargetSdkForT : HookRegister() {
    override fun init() = hasEnable("getMinimumSignatureSchemeVersionForTargetSdk") {
        try {
            findMethod("android.util.apk.ApkSignatureVerifier") {
                name == "getMinimumSignatureSchemeVersionForTargetSdk"
            }.hookMethod {
                after { param ->
                    param.result = 0
                }
            }
            XposedBridge.log("BypassCheck: Hook getMinimumSignatureSchemeVersionForTargetSdk success!")
        } catch (e: Throwable) {
            XposedBridge.log("BypassCheck: Hook getMinimumSignatureSchemeVersionForTargetSdk failed!")
        }

    }

}