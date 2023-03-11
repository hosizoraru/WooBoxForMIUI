package com.lt2333.simplicitytools.hooks.rules.all.securitycenter

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.XSPUtils
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

object RemoveMacroBlacklistForAll1 : HookRegister() {

    override fun init() {
        if (XSPUtils.getBoolean("remove_macro_blacklist", false)) {
            var letter = 'a'
            for (i in 0..25) {
                val classIfExists = XposedHelpers.findClassIfExists(
                    "com.miui.gamebooster.utils.$letter" + "0", getDefaultClassLoader()
                ) ?: continue
                if (classIfExists.declaredMethods.size in 10..15 && classIfExists.fields.isEmpty() && classIfExists.declaredFields.size >= 2) {
                    XposedBridge.log("Woobox: RemoveMacroBlacklist in ${classIfExists.name}")
                    findMethod(classIfExists){
                        name=="c" && returnType==Boolean::class.java && isStatic &&parameterCount==1
                    }.hookBefore {
                        XposedBridge.log("Woobox: c")
                        it.result = false
                    }
                    return
                }
                letter++
            }

            findMethod("q7.m0") {
                name == "n" && parameterTypes[0] == ArrayList::class.java
            }.hookBefore { param ->
                param.args[0] = "[]"
            }

            findMethod("q7.m0"){
                name == "g" && parameterTypes[0] == String::class.java
            }.hookReturnConstant(false)

            findMethod("w6.b"){
                name == "e" && parameterTypes[0] == Context::class.java && parameterTypes[1] == String::class.java
            }.hookReturnConstant(true)
        }
    }

}