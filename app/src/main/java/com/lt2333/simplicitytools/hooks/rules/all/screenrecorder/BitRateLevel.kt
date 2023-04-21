package com.lt2333.simplicitytools.hooks.rules.all.screenrecorder

import com.github.kyuubiran.ezxhelper.init.InitFields
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.dexKitBridge
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.loadDexKit
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.isFinal
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import io.luckypray.dexkit.enums.MatchType

object BitRateLevel : HookRegister() {
    override fun init() = hasEnable("bitrate_level") {
        loadDexKit()
        dexKitBridge.findMethodUsingString {
            usingString = "defaultBitRate = "
            matchType = MatchType.FULL
        }.map {
            it.getMethodInstance(InitFields.ezXClassLoader)
        }.firstOrNull {
            it.parameterCount == 2
                    && it.parameterTypes[0] == Int::class.java
                    && it.parameterTypes[1] == Int::class.java
        }?.hookBefore { param->
            param.args[0] = 1200
            param.args[1] = 1
            param.method.declaringClass.declaredFields.firstOrNull { field ->
                field.also {
                    it.isAccessible = true
                }.let { fieldAccessible ->
                    fieldAccessible.isFinal &&
                            fieldAccessible.get(null).let {
                                kotlin.runCatching {
                                    (it as IntArray).contentEquals(intArrayOf(200, 100, 50, 32, 24, 16, 8, 6, 4, 1))
                                }.getOrDefault(false)
                            }
                }
            }?.set(null, intArrayOf(1200, 1000, 750, 500, 400, 300, 250, 200, 100, 50, 32, 24, 16, 8, 6, 4, 1))
//
//        val qwq = "w0.c"
//        // 有混淆的码率
//        findMethod(qwq) {
//            name == "u"
//        }.hookBefore { param->
////            param.args[0] = 250
////            param.args[1] = 1
//            param.args[0] = 1200
//            param.args[1] = 1
//            param.method.declaringClass.declaredFields.firstOrNull { field ->
//                field.also {
//                    it.isAccessible = true
//                }.let { fieldAccessible ->
//                    fieldAccessible.isFinal &&
//                            fieldAccessible.get(null).let {
//                                kotlin.runCatching {
//                                    (it as IntArray).contentEquals(intArrayOf(200, 100, 50, 32, 24, 16, 8, 6, 4, 1))
//                                }.getOrDefault(false)
//                            }
//                }
//            }?.set(null, intArrayOf(1200, 1000, 750, 500, 400, 300, 250, 200, 100, 50, 32, 24, 16, 8, 6, 4, 1))
        }
    }
}