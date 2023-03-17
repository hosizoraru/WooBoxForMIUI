package com.lt2333.simplicitytools.hooks.rules.all.wini.model

// 系统UI
data class SystemUIModel(
    var notification: NotificationBlurModel = NotificationBlurModel(),
    val quickSetting: QuickSettingModel = QuickSettingModel()
)