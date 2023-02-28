package com.lt2333.simplicitytools.activity.pages.t

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextV
import com.lt2333.simplicitytools.R


@BMPage("icon_position","Icon Position", hideMenu = false) class IconPositionPageForT : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.atright_status_bar_icon)
        TextWithSwitch(
            TextV(textId = R.string.atright_alarm_icon),
            SwitchV("atright_alarm_icon")
        )
        TextWithSwitch(
            TextV(textId = R.string.atright_nfc_icon),
            SwitchV("atright_nfc_icon")
        )
        TextWithSwitch(
            TextV(textId = R.string.atright_volume_icon),
            SwitchV("atright_volume_icon")
        )
        TextWithSwitch(
            TextV(textId = R.string.atright_zen_icon),
            SwitchV("atright_zen_icon")
        )
    }

}