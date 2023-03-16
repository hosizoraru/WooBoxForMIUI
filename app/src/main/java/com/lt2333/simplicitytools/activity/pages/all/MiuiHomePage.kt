package com.lt2333.simplicitytools.activity.pages.all

import android.os.Build
import android.view.View
import cn.fkj233.ui.activity.MIUIActivity.Companion.safeSP
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SeekBarWithTextV
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.activity.view.TextV
import cn.fkj233.ui.dialog.MIUIDialog
import com.lt2333.simplicitytools.R
import de.robv.android.xposed.XposedBridge

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
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.miuihome_unlock_cell_count,
                tipsId = R.string.miuihome_unlock_cell_count_summary
            ), SwitchV("miuihome_unlock_cell_count")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.miuihome_blur_when_open_folder,
                tipsId = R.string.miuihome_blur_when_open_folder_summary
            ), SwitchV("miuihome_blur_when_open_folder")
        )
        val blurBinding = GetDataBinding({ safeSP.getBoolean("miuihome_use_complete_blur", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_use_complete_blur, tipsId = R.string.miuihome_use_complete_blur_summary),
            SwitchV("miuihome_use_complete_blur", false, dataBindingSend = blurBinding.bindingSend)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_complete_blur_fix, tipsId = R.string.miuihome_complete_blur_fix_summary),
            SwitchV("miuihome_complete_blur_fix", false),
            dataBindingRecv = blurBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_highend_device, tipsId = R.string.miuihome_highend_device_summary),
            SwitchV("miuihome_highend_device", false)
        )
        val animRatioBinding = GetDataBinding({ safeSP.getBoolean("miuihome_anim_ratio_binding", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_anim_ratio_binding, tipsId = R.string.miuihome_anim_ratio_binding_summary),
            SwitchV("miuihome_anim_ratio_binding", dataBindingSend = animRatioBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.miuihome_anim_ratio, tipsId = R.string.miuihome_anim_ratio_summary),
            SeekBarWithTextV("miuihome_anim_ratio", 0, 300, 100),
            dataBindingRecv = animRatioBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.miuihome_anim_ratio_recent, tipsId = R.string.miuihome_anim_ratio_recent_summary),
            SeekBarWithTextV("miuihome_anim_ratio_recent", 0, 300, 100),
            dataBindingRecv = animRatioBinding.getRecv(1)
        )
        val cardSizeBinding = GetDataBinding({ safeSP.getBoolean("miuihome_task_view_card_size_binding", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.miuihome_task_view_card_size_binding, tipsId = R.string.miuihome_task_view_card_size_binding_summary),
            SwitchV("miuihome_task_view_card_size_binding", dataBindingSend = cardSizeBinding.bindingSend)
        )
        TextWithSeekBar(
            TextV(textId = R.string.miuihome_task_view_card_size_vertical),
            SeekBarWithTextV("miuihome_task_view_card_size_vertical", 80, 120, 100),
            dataBindingRecv = cardSizeBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.miuihome_task_view_card_size_horizontal1),
            SeekBarWithTextV("miuihome_task_view_card_size_horizontal1", 80, 120, 100),
            dataBindingRecv = cardSizeBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.miuihome_task_view_card_size_horizontal2),
            SeekBarWithTextV("miuihome_task_view_card_size_horizontal2", 80, 120, 100),
            dataBindingRecv = cardSizeBinding.getRecv(1)
        )
        val HomeFoldAnimBinding = GetDataBinding({ safeSP.getBoolean("home_folder_anim", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.home_folder_anim),
            SwitchV("home_folder_anim", dataBindingSend = HomeFoldAnimBinding.bindingSend)
        )
        TextWithSeekBar(
            TextV(textId = R.string.home_folder_anim_1),
            SeekBarWithTextV("home_folder_anim_1", 50, 150, 90),
            dataBindingRecv = HomeFoldAnimBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.home_folder_anim_2),
            SeekBarWithTextV("home_folder_anim_2", 10, 60, 30),
            dataBindingRecv = HomeFoldAnimBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.home_folder_anim_3),
            SeekBarWithTextV("home_folder_anim_3", 50, 150, 99),
            dataBindingRecv = HomeFoldAnimBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.home_folder_anim_4),
            SeekBarWithTextV("home_folder_anim_4", 10, 60, 24),
            dataBindingRecv = HomeFoldAnimBinding.getRecv(1)
        )
        val FoldDockBinding = GetDataBinding({ safeSP.getBoolean("home_fold_dock", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.home_fold_dock),
            SwitchV("home_fold_dock", dataBindingSend = FoldDockBinding.bindingSend)
        )
        TextWithSeekBar(
            TextV(textId = R.string.fold_dock_hotseat),
            SeekBarWithTextV("fold_dock_hotseat", 1, 10, 3),
            dataBindingRecv = FoldDockBinding.getRecv(1)
        )
        TextWithSeekBar(
            TextV(textId = R.string.fold_dock_run),
            SeekBarWithTextV("fold_dock_run", 1, 10, 2),
            dataBindingRecv = FoldDockBinding.getRecv(1)
        )
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                val monoBinding = GetDataBinding({ safeSP.getBoolean("mono_chrome_icon", false) }) { view, flags, data ->
                    if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
                }
                TextSummaryWithSwitch(
                    TextSummaryV(
                        textId = R.string.mono_chrome_icon,
                    ), SwitchV("mono_chrome_icon", false, dataBindingSend = monoBinding.bindingSend)
                )
                TextSummaryWithSwitch(
                    TextSummaryV(
                        textId = R.string.monoet_color,
                    ), SwitchV("monoet_color", false), dataBindingRecv = monoBinding.binding.getRecv(1)
                )
                TextSummaryWithSwitch(
                    TextSummaryV(
                        textId = R.string.use_edit_color,
                    ), SwitchV("use_edit_color",false), dataBindingRecv = monoBinding.binding.getRecv(1)
                )
                TextSummaryWithArrow(
                    TextSummaryV(
                        textId = R.string.your_color,
                        onClickListener = {
                            MIUIDialog(activity) {
                                setTitle(R.string.your_color)
                                setEditText(
                                    "",
                                    "${activity.getString(R.string.current)}${
                                        safeSP.getString("your_color", "#0d84ff")
                                    }"
                                )
                                setLButton(textId = R.string.cancel) {
                                    dismiss()
                                }
                                setRButton(textId = R.string.Done) {
                                    if (getEditText() != "") {
                                        safeSP.putAny(
                                            "your_color",
                                            getEditText()
                                        )
                                    }
                                    dismiss()
                                }
                            }.show()
                        }), dataBindingRecv = monoBinding.binding.getRecv(1)
                )
            }
        }
        Line()
        TitleText(textId = R.string.scope_personalassistant)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.personalassistant_minus_one_blur,
                tipsId = R.string.personalassistant_minus_one_blur_summary
            ), SwitchV("personalassistant_minus_one_blur")
        )
    }
}