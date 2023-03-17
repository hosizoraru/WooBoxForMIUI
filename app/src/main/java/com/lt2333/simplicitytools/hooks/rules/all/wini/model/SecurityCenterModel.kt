package com.lt2333.simplicitytools.hooks.rules.all.wini.model

import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.XSPUtils.getString

// 手机管家
data class SecurityCenterModel(
    var dockBackground: BaseBlurBackgroundModel = BaseBlurBackgroundModel(
        getBoolean("se_enable",true),
        getInt("security_blurRadius",60),
        getString("security_color", "#3C000000")!!,
    )
)