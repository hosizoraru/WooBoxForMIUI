package com.lt2333.simplicitytools.hooks.rules.all.wini

import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getInt

// 快捷设置
data class QuickSettingModel(
    var hideMiPlayEntry:Boolean = getBoolean("hideMiPlayEntry",false),
    var controlDetailBackgroundAlpha: Int = getInt("controlDetailBackgroundAlpha",120),
)