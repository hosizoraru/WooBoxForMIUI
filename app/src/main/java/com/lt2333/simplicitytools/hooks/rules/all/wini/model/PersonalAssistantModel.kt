package com.lt2333.simplicitytools.hooks.rules.all.wini.model

import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.XSPUtils.getString

// 智能助理
data class PersonalAssistantModel(
    var background: BaseBlurBackgroundModel = BaseBlurBackgroundModel(
        getBoolean("pa_enable", true),
        getInt("personalAssistant_blurRadius",80),
        getString("personalAssistant_color","#1E000000")!!,
    )
)