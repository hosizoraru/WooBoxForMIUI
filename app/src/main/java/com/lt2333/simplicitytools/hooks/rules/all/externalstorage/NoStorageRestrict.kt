package com.lt2333.simplicitytools.hooks.rules.all.externalstorage

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object NoStorageRestrict : HookRegister() {
    override fun init() = hasEnable("No_Storage_Restrict") {
        findMethod("com.android.externalstorage.ExternalStorageProvider") {
            name == "shouldBlockFromTree" &&
                    parameterTypes[0] == String::class.java
        }.hookReturnConstant(true)
    }
}