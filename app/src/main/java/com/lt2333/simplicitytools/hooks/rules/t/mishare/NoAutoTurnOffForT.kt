package com.lt2333.simplicitytools.hooks.rules.t.mishare

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object NoAutoTurnOffForT : HookRegister() {
    override fun init() {
        findMethod("com.miui.mishare.connectivity.MiShareService\$d\$g") {
            name == "b"
        }.hookBefore {
            hasEnable("No_Auto_Trun_Off") {
                it.result = null
            }
        }
    }

}