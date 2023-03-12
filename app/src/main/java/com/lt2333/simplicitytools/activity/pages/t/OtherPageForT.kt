package com.lt2333.simplicitytools.activity.pages.t

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R


@BMPage("scope_other", "Other", hideMenu = false)
class OtherPageForT : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.scope_gallery)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.Unlock_HDR_Enhance,
                tipsId = R.string.Unlock_HDR_Enhance_summary
            ), SwitchV("Unlock_HDR_Enhance")
        )
        Line()
        TitleText(textId = R.string.scope_mediaeditor)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.unlock_unlimited_cropping,
                tipsId = R.string.unlock_unlimited_cropping_summary
            ), SwitchV("unlock_unlimited_cropping")
        )
        Line()
        TitleText(textId = R.string.scope_mishare)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.No_Auto_Trun_Off,
                tipsId = R.string.No_Auto_Trun_Off_summary
            ), SwitchV("No_Auto_Trun_Off")
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
