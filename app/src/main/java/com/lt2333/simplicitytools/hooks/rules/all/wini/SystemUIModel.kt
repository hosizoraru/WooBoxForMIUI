package com.lt2333.simplicitytools.hooks.rules.all.wini

// 系统UI
data class SystemUIModel(
    var notification: NotificationBlurModel = NotificationBlurModel(),
    val quickSetting: QuickSettingModel = QuickSettingModel()
)