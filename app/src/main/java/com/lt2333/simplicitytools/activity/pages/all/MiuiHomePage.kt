package com.lt2333.simplicitytools.activity.pages.all

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("scope_miuihome", "Home", hideMenu = false)

class MiuiHomePage : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.scope_miuihome)

        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.home_time,
                tipsId = R.string.home_time_summary
            ), SwitchV("home_time")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.double_tap_to_sleep,
                tipsId = R.string.home_double_tap_to_sleep_summary
            ), SwitchV("double_tap_to_sleep")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.miuihome_recentwiew_wallpaper_darkening, tipsId = R.string.miuihome_recentwiew_wallpaper_darkening_summary
            ), SwitchV("miuihome_recentwiew_wallpaper_darkening", false)
        )
        //TODO：未开发完成
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.miuihome_recentview_remove_card_animation, tipsId = R.string.miuihome_recentview_remove_card_animation_summary
            ), SwitchV("miuihome_recentview_remove_card_animation", false)
        )
        //TODO：未开发完成
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_shortcut_add_small_window, tipsId = R.string.miuihome_shortcut_add_small_window_summary),
            SwitchV("miuihome_shortcut_add_small_window", false)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_scroll_icon_name, tipsId = R.string.miuihome_scroll_icon_name_summary),
            SwitchV("miuihome_scroll_icon_name", false)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Use_Transition_Animation),
            SwitchV("Use_Transition_Animation")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Overlap_Mode),
            SwitchV("Overlap_Mode")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.home_widget_to_minus),
            SwitchV("home_widget_to_minus")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Show_MIUI_Widget),
            SwitchV("Show_MIUI_Widget")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.shortcut_remove_restrictions),
            SwitchV("shortcut_remove_restrictions")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.unlock_hotseat),
            SwitchV("unlock_hotseat")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.optimize_unlock_anim),
            SwitchV("optimize_unlock_anim")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.icon_corner),
            SwitchV("icon_corner")
        )
    }
}