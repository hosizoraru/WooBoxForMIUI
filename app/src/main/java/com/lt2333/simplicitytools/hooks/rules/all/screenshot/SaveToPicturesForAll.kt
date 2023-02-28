package com.lt2333.simplicitytools.hooks.rules.all.screenshot

import com.github.kyuubiran.ezxhelper.utils.findFieldObject
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.callStaticMethod
import com.lt2333.simplicitytools.utils.findClass
import com.lt2333.simplicitytools.utils.setObjectField

object SaveToPicturesForAll : HookRegister() {
    override fun init() = hasEnable("save_to_pictures") {
        "android.os.Environment".setObjectField("DIRECTORY_DCIM", "Pictures")
    }

}