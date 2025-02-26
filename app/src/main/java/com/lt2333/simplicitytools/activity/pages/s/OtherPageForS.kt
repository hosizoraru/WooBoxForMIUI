package com.lt2333.simplicitytools.activity.pages.s

import android.content.ComponentName
import android.content.Intent
import android.view.View
import android.widget.Switch
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.utils.Yife.Terminal


@BMPage("scope_other", "Other", hideMenu = false)
class OtherPageForS : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.scope_mishare)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.No_Auto_Turn_Off,
                tipsId = R.string.No_Auto_Turn_Off_summary
            ), SwitchV("No_Auto_Turn_Off")
        )
        Line()
        TitleText(textId = R.string.scope_ScreenRecorder)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.frame_level),
            SwitchV("frame_level")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.bitrate_level),
            SwitchV("bitrate_level")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.sound_where),
            SwitchV("sound_where")
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
                textId = R.string.Settings_Dont_Through_The_List,
            ), SwitchV("Settings_Dont_Through_The_List")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.system_settings_permission_unknown_origin_app,
                tipsId = R.string.system_settings_permission_unknown_origin_app_desc
            ), SwitchV("system_settings_permission_unknown_origin_app")
        )
        Line()
        TitleText(textId = R.string.cast)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.force_support_send_app,
            ), SwitchV("force_support_send_app")
        )
        Line()
        TitleText(textId = R.string.scope_file_explorer)
        TextSSw(getString(R.string.file_explorer_can_selectable), key = "file_explorer_can_selectable")
        TextSSw(getString(R.string.file_explorer_is_single_line), key = "file_explorer_is_single_line")
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
