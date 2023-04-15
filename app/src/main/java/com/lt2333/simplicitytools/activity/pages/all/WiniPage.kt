package com.lt2333.simplicitytools.activity.pages.all

import android.view.View
import cn.fkj233.ui.activity.MIUIActivity.Companion.safeSP
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SeekBarWithTextV
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import com.lt2333.simplicitytools.R

@BMPage("scope_wini", "WINI", hideMenu = false)

class WiniPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_systemui)
        val BlurSysBinding = GetDataBinding({ safeSP.getBoolean("n_enable", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.NotificationBlurModel),
            SwitchV("n_enable", dataBindingSend = BlurSysBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.blurRadius_summary),
            SeekBarWithTextV("blurRadius", 20, 99, 60),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.cornerRadius_summary),
            SeekBarWithTextV("cornerRadius", 0, 100, 48),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.blurBackgroundAlpha_summary),
            SeekBarWithTextV("blurBackgroundAlpha", 100, 240, 170),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.defaultBackgroundAlpha_summary),
            SeekBarWithTextV("defaultBackgroundAlpha", 120, 250, 200),
            dataBindingRecv = BlurSysBinding.getRecv(1)
        )
        Line()
        TitleText(textId = R.string.scope_systemui)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.hideMiPlayEntry),
            SwitchV("hideMiPlayEntry")
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.controlDetailBackgroundAlpha_summary),
            SeekBarWithTextV("controlDetailBackgroundAlpha", 120, 255, 120),
        )
        Line()
        TitleText(textId = R.string.scope_miuihome)
        val BlurHomeBinding = GetDataBinding({ safeSP.getBoolean("blur_when_show_shortcut_menu", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.blur_when_show_shortcut_menu),
            SwitchV("blur_when_show_shortcut_menu", dataBindingSend = BlurHomeBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.shortcutMenuBackgroundAlpha_summary),
            SeekBarWithTextV("shortcutMenuBackgroundAlpha", 120, 255, 255),
            dataBindingRecv = BlurHomeBinding.getRecv(1)
        )
        Line()
        TitleText(textId = R.string.scope_personalassistant)
        val BlurPersonalBinding = GetDataBinding({ safeSP.getBoolean("pa_enable", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.personalAssistant_blur_model),
            SwitchV("pa_enable", dataBindingSend = BlurPersonalBinding.bindingSend)
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.personalAssistant_blurradius_summary),
            SeekBarWithTextV("personalAssistant_blurRadius", 30, 99, 80),
            dataBindingRecv = BlurPersonalBinding.getRecv(1)
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.personalAssistant_color_summary,
                onClickListener = {
                    MIUIDialog(activity) {
                        setTitle(R.string.personalAssistant_color_summary)
                        setEditText(
                            "",
                            "${activity.getString(R.string.current)}${
                                safeSP.getString("personalAssistant_color", "#1E000000")
                            }"
                        )
                        setLButton(textId = R.string.cancel) {
                            dismiss()
                        }
                        setRButton(textId = R.string.Done) {
                            if (getEditText() != "") {
                                safeSP.putAny(
                                    "personalAssistant_color",
                                    getEditText()
                                )
                            }
                            dismiss()
                        }
                    }.show()
                }), dataBindingRecv = BlurPersonalBinding.binding.getRecv(1))
        Line()
        TitleText(textId = R.string.scope_securitycenter)
        val BlurSecurityBinding = GetDataBinding({ safeSP.getBoolean("se_enable", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.security_blur_model),
            SwitchV("se_enable", dataBindingSend = BlurSecurityBinding.bindingSend)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.a_fix_summary),
            SwitchV("a_fix", dataBindingRecv = BlurSecurityBinding.getRecv(1))
        )
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.security_blurradius_summary),
            SeekBarWithTextV("security_blurRadius", 30, 99, 60),
            dataBindingRecv = BlurSecurityBinding.getRecv(1)
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.security_color_summary,
                onClickListener = {
                    MIUIDialog(activity) {
                        setTitle(R.string.security_color_summary)
                        setEditText(
                            "",
                            "${activity.getString(R.string.current)}${
                                safeSP.getString("security_color", "#1E000000")
                            }"
                        )
                        setLButton(textId = R.string.cancel) {
                            dismiss()
                        }
                        setRButton(textId = R.string.Done) {
                            if (getEditText() != "") {
                                safeSP.putAny(
                                    "security_color",
                                    getEditText()
                                )
                            }
                            dismiss()
                        }
                    }.show()
                }), dataBindingRecv = BlurSecurityBinding.binding.getRecv(1))
    }
}