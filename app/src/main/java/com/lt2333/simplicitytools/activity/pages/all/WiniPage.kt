package com.lt2333.simplicitytools.activity.pages.all

import android.view.View
import cn.fkj233.ui.activity.MIUIActivity.Companion.safeSP
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SeekBarWithTextV
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("scope_wini", "WINI", hideMenu = false)

class WiniPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_wini)
        val BlurSysBinding = GetDataBinding({ safeSP.getBoolean("enable", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.NotificationBlurModel),
            SwitchV("enable", dataBindingSend = BlurSysBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.blurRadius),
            SeekBarWithTextV("blurRadius", 20, 99, 60),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.cornerRadius),
            SeekBarWithTextV("cornerRadius", 0, 100, 48),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.blurBackgroundAlpha),
            SeekBarWithTextV("blurBackgroundAlpha", 100, 240, 170),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.defaultBackgroundAlpha),
            SeekBarWithTextV("defaultBackgroundAlpha", 120, 250, 200),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        Line()
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.hideMiPlayEntry),
            SwitchV("hideMiPlayEntry")
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.controlDetailBackgroundAlpha),
            SeekBarWithTextV("controlDetailBackgroundAlpha", 120, 255, 120),
        )
        Line()
        val BlurHomeBinding = GetDataBinding({ safeSP.getBoolean("blur_when_show_shortcut_menu", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.blur_when_show_shortcut_menu),
            SwitchV("blur_when_show_shortcut_menu", dataBindingSend = BlurHomeBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.shortcutMenuBackgroundAlpha),
            SeekBarWithTextV("shortcutMenuBackgroundAlpha", 120, 255, 255),
            dataBindingRecv = BlurHomeBinding.getRecv(1)
        )
//        TextSummaryWithArrow(
//            TextSummaryV(
//                textId = R.string.shortcutMenuBackgroundAlpha,
//                onClickListener = {
//                    MIUIDialog(activity) {
//                        setTitle(R.string.shortcutMenuBackgroundAlpha)
//                        setEditText(
//                            "",
//                            "${activity.getString(R.string.current)}${
//                                safeSP.getInt("shortcutMenuBackgroundAlpha", 120)
//                            }"
//                        )
//                        setLButton(textId = R.string.cancel) {
//                            dismiss()
//                        }
//                        setRButton(textId = R.string.Done) {
//                            if (getEditText() != "") {
//                                safeSP.putAny(
//                                    "shortcutMenuBackgroundAlpha",
//                                    getEditText().toInt()
//                                )
//                            }
//                            dismiss()
//                        }
//                    }.show()
//                }), dataBindingRecv = BlurBinding.binding.getRecv(1)
//        )
    }
}