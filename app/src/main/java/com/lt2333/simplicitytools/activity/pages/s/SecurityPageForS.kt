package com.lt2333.simplicitytools.activity.pages.s

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import android.widget.Toast
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.utils.Yife.Terminal

@BMPage("scope_securitycenter", "Security", hideMenu = false)
class SecurityPageForS : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_securitycenter)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.skip_waiting_time,
                tipsId = R.string.skip_waiting_time_summary
            ), SwitchV("skip_waiting_time")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.remove_open_app_confirmation_popup,
                tipsId = R.string.remove_open_app_confirmation_popup_summary
            ), SwitchV("remove_open_app_confirmation_popup")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.lock_one_hundred,
                tipsId = R.string.lock_one_hundred_summary
            ), SwitchV("lock_one_hundred")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.remove_macro_blacklist),
            SwitchV("remove_macro_blacklist")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.battery_life_function),
            SwitchV("battery_life_function")
        )
        //miuiqol
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.behavior_record_enhance_title,
                tipsId = R.string.behavior_record_enhance_summary
            ), SwitchV("behavior_record_enhance")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.behavior_record_system_app_whitelist_title,
                tipsId = R.string.behavior_record_system_app_whitelist_summary
            ), SwitchV("behavior_record_system_app_whitelist")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.system_app_wlan_control_title,
                tipsId = R.string.system_app_wlan_control_summary
            ), SwitchV("system_app_wlan_control",true)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_all_text_view_title,
                tipsId = R.string.enable_all_text_view_summary
            ), SwitchV("enable_all_text_view",true)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.original_default_open_setting_title,
            ), SwitchV("original_default_open_setting")
        )
        //miuiqol
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.remove_conversation_bubble_settings_restriction,
                tipsId = R.string.remove_conversation_bubble_settings_restriction_summary
            ), SwitchV("remove_conversation_bubble_settings_restriction")
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.try_to_fix_conversation_bubbles,
                tipsId = R.string.try_to_fix_conversation_bubbles_summary
            ) {
                Terminal.exec("pm enable com.miui.securitycenter/com.miui.bubbles.services.BubblesNotificationListenerService")
                Toast.makeText(
                    activity,
                    getString(R.string.finished),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}