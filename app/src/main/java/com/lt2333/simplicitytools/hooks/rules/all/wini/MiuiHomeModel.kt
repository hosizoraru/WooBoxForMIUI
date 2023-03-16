package com.lt2333.simplicitytools.hooks.rules.all.wini

import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getInt

// 基础模糊配置
data class MiuiHomeModel(
    var enableShortcutBackgroundBlur: Boolean = getBoolean("blur_when_show_shortcut_menu",false),
    var shortcutMenuBackgroundAlpha: Int = getInt("shortcutMenuBackgroundAlpha",120),
)