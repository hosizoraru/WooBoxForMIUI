package com.lt2333.simplicitytools.activity.pages.all

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("scope_taplus", "Taplus", hideMenu = false)
class TaplusPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_taplus)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.horizontal_contentextension),
            SwitchV("horizontal_contentextension")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.double_press),
            SwitchV("double_press")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.super_image),
            SwitchV("super_image")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.intent_browser),
            SwitchV("intent_browser")
        )
    }
}