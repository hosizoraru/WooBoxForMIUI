package com.lt2333.simplicitytools.hooks.rules.all.wini

import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getInt

// 通知
data class NotificationBlurModel(
    var enable: Boolean = getBoolean("enable",true),
    var cornerRadius: Int = getInt("cornerRadius",48),
    var blurRadius: Int = getInt("blurRadius",99),
    var blurBackgroundAlpha: Int = getInt("blurBackgroundAlpha",100),
    var defaultBackgroundAlpha: Int = getInt("defaultBackgroundAlpha",200),
)