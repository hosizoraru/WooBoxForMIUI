package com.lt2333.simplicitytools.hooks.rules.t.securitycenter

import com.lt2333.simplicitytools.utils.chsbuffer.*
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import com.lt2333.simplicitytools.utils.hasEnable
import io.luckypray.dexkit.DexKitBridge
import io.luckypray.dexkit.enums.FieldUsingType

class IntlEnableBehaviorRecord(val dexKit: DexKitBridge) : Hook() {
    override fun init(classLoader: ClassLoader) {
//        if ((!xPrefs.getBoolean("behavior_record_enhance", true)
//                    || !Build.IS_INTERNATIONAL_BUILD)
//            && !BuildConfig.DEBUG
//        ) return

        hasEnable("behavior_record_enhance") {
            val k = dexKit.findMethodUsingString { usingString = "initTolerateApps by cloud success" }
                .single().declaringClassSig

            val spoofCN = BooleanDuringMethod(
                XposedHelpers.findClass(
                    "miui.os.Build", classLoader
                ), "IS_INTERNATIONAL_BUILD", false
            )

            dexKit.findMethodUsingField {
                fieldDeclareClass = "Lmiui/os/Build;"
                fieldName = "IS_INTERNATIONAL_BUILD"
                usingType = FieldUsingType.GET
                callerMethodDeclareClass = k
            }.forEach { (method, _) ->
                XposedBridge.hookMethod(method.getMethodInstance(classLoader), spoofCN)
            }
        }
    }
}