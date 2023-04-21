package com.lt2333.simplicitytools.hooks.rules.all.screenrecorder

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.dexKitBridge
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.loadDexKit
import com.lt2333.simplicitytools.utils.isFinal

object FrameLevel : HookRegister() {
    override fun init() = hasEnable("frame_level") {
        loadDexKit()
        val qwq = "w0.c"
        // 有混淆的帧率
        findMethod(qwq) {
            name == "w"
        }.hookBefore { param->
//            param.args[0] = 7200
//            param.args[1] = 1
            param.args[0] = 3600
            param.args[1] = 1
            param.method.declaringClass.declaredFields.firstOrNull { field ->
                field.also {
                    it.isAccessible = true
                }.let { fieldAccessible ->
                    fieldAccessible.isFinal &&
                            fieldAccessible.get(null).let {
                                kotlin.runCatching {
                                    (it as IntArray).contentEquals(intArrayOf(15, 24, 30, 48, 60, 90))
                                }.getOrDefault(false)
                            }
                }
            }?.set(null,intArrayOf(15, 24, 30, 48, 60, 90, 120, 144))
        }
        // 120+144
//        dexKitBridge.findMethodUsingString {
//            usingString = "Error when set frame value, maxValue = "
//        }.single()
//            .getMethodInstance(ClassLoader.getSystemClassLoader())
//            .hookBefore { param ->
//                param.args[0] = 3600
//                param.args[1] = 1
//                param.method.declaringClass.declaredFields.firstOrNull { field ->
//                    field.also {
//                        it.isAccessible = true
//                    }.let { fieldAccessible ->
//                        fieldAccessible.isFinal &&
//                                fieldAccessible.get(null).let {
//                                    kotlin.runCatching {
//                                        (it as IntArray).contentEquals(intArrayOf(15, 24, 30, 48, 60, 90))
//                                    }.getOrDefault(false)
//                                }
//                    }
//                }?.set(null,intArrayOf(15, 24, 30, 48, 60, 90, 120, 144))
//            }
    }
}