package com.lt2333.simplicitytools.hooks.rules.t.miuihome

import android.content.res.Resources
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.ResourcesHookRegister
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam

object MonetColorForT : ResourcesHookRegister() {
    override fun init() = hasEnable("monoet_color"){
        val monoColorId = Resources.getSystem().getIdentifier("system_accent1_100", "color", "android")
        val monoColor = Resources.getSystem().getColor(monoColorId)
        getInitPackageResourcesParam().res.setReplacement(
            "com.miui.home",
            "color",
            "monochrome_default",
            monoColor
        )
    }
}