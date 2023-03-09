package com.lt2333.simplicitytools.hooks.rules.t.miuihome

import android.content.res.Resources
import android.graphics.Color
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.ResourcesHookRegister

object MonetColorForT : ResourcesHookRegister() {
    override fun init() = hasEnable("monoet_color") {
        val monet = "system_accent1_100"
        val monoColorId = Resources.getSystem().getIdentifier(monet, "color", "android")
        var monoColor = Resources.getSystem().getColor(monoColorId)
//        hasEnable("use_edit_color") {
//            monoColor = Color.parseColor("your_color")
//        }
        getInitPackageResourcesParam().res.setReplacement(
            "com.miui.home",
            "color",
            "monochrome_default",
            monoColor
        )
//        val ColorEntriesId = Resources.getSystem().getStringArray()
//        val ColorEntries = Resources.getSystem().getStringArray(ColorEntriesId)
//        getInitPackageResourcesParam().res.setReplacement(
//            "com.miui.home",
//            "string",
//            ColorEntries.toString(),
//            "Monet"
//        )
    }
}