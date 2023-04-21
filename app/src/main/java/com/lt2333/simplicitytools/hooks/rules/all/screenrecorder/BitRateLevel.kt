package com.lt2333.simplicitytools.hooks.rules.all.screenrecorder

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.isFinal
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object BitRateLevel : HookRegister() {
    override fun init() = hasEnable("bitrate_level") {
        val qwq = "w0.c"
        // 有混淆的码率
        findMethod(qwq) {
            name == "u"
        }.hookBefore { param->
//            param.args[0] = 250
//            param.args[1] = 1
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
        }
    }
}