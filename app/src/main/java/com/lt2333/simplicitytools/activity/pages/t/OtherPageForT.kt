package com.lt2333.simplicitytools.activity.pages.t

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R


@BMPage("scope_other", "Other", hideMenu = false)
class OtherPageForT : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.scope_mishare)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.No_Auto_Turn_Off,
                tipsId = R.string.No_Auto_Turn_Off_summary
            ), SwitchV("No_Auto_Turn_Off")
        )
        Line()
        TitleText(textId = R.string.scope_deskclock)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.Hour_Glass_Enable,
                tipsId = R.string.Hour_Glass_Enable_summary
            ), SwitchV("Hour_Glass_Enable")
        )
        Line()
        TitleText(textId = R.string.scope_browser)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Enable_Debug_Mode),
            SwitchV("Enable_Debug_Mode")
        )
        Line()
        TitleText(textId = R.string.updater)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.remove_ota_validate,
                tipsId = R.string.remove_ota_validate_summary
            ), SwitchV("remove_ota_validate")
        )
        Line()
        TitleText(textId = R.string.settings)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.show_notification_importance,
                tipsId = R.string.show_notification_importance_summary
            ), SwitchV("show_notification_importance")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.speed_mode,
                tipsId = R.string.speed_mode_summary
            ), SwitchV("speed_mode")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.Settings_Dont_Through_The_List,
            ), SwitchV("Settings_Dont_Through_The_List")
        )
        Line()
        TitleText(textId = R.string.cast)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.force_support_send_app,
            ), SwitchV("force_support_send_app")
        )
        Line()
        TitleText(textId = R.string.rear_display)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.show_weather_main_switch,
            ), SwitchV("rear_show_weather")
        )
        Line()
        TitleText(textId = R.string.remove_ad)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.remove_thememanager_ads),
            SwitchV("remove_thememanager_ads")
        )
        Line()
        TitleText(textId = R.string.scope_lbe)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.hide_miui_clipboard_dialog),
            SwitchV("hide_miui_clipboard_dialog")
        )
        Line()
        TitleText(textId = R.string.scope_guardprovider)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Anti_Defraud_App_Manager),
            SwitchV("Anti_Defraud_App_Manager")
        )
        Line()
        TitleText(textId = R.string.scope_externalstorage)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.No_Storage_Restrict),
            SwitchV("No_Storage_Restrict")
        )
    }

}
