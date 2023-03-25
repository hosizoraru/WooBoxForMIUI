package com.lt2333.simplicitytools.activity.pages.t

import android.view.View
import android.widget.Switch
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import com.lt2333.simplicitytools.R


@BMPage("scope_android","Android", hideMenu = false)
class AndroidPageForT : BasePage() {

    override fun onCreate() {
        TitleText(textId = R.string.scope_android_summary)
        TitleText(textId = R.string.corepacth)
        val corepatchBinding = GetDataBinding({ MIUIActivity.safeSP.getBoolean("scope_corepatch", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.scope_corepatch,
            ), SwitchV("scope_corepatch",true, dataBindingSend = corepatchBinding.bindingSend)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.downgr,
                tipsId = R.string.downgr_summary
            ), SwitchV("downgrade"), dataBindingRecv = corepatchBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.authcreak,
                tipsId = R.string.authcreak_summary
            ), SwitchV("authcreak"), dataBindingRecv = corepatchBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.digestCreak,
                tipsId = R.string.digestCreak_summary
            ), SwitchV("digestCreak"), dataBindingRecv = corepatchBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.UsePreSig,
                tipsId = R.string.UsePreSig_summary
            ), SwitchV("UsePreSig"), dataBindingRecv = corepatchBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enhancedMode,
                tipsId = R.string.enhancedMode_summary
            ), SwitchV("enhancedMode"), dataBindingRecv = corepatchBinding.binding.getRecv(1)
        )
        Line()
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.disable_flag_secure,
                tipsId = R.string.disable_flag_secure_summary
            ), SwitchV("disable_flag_secure")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.delete_on_post_notification,
                tipsId = R.string.delete_on_post_notification_summary
            ), SwitchV("delete_on_post_notification")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.remove_small_window_restrictions,
                tipsId = R.string.remove_small_window_restrictions_summary
            ), SwitchV("remove_small_window_restrictions")
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.max_wallpaper_scale,
                onClickListener = {
                    MIUIDialog(activity) {
                        setTitle(R.string.max_wallpaper_scale)
                        setEditText(
                            "",
                            "${activity.getString(R.string.def)}1.2, ${activity.getString(R.string.current)}${
                                MIUIActivity.safeSP.getFloat("max_wallpaper_scale", 1.2f)
                            }"
                        )
                        setLButton(textId = R.string.cancel) {
                            dismiss()
                        }
                        setRButton(textId = R.string.Done) {
                            if (getEditText() != "") {
                                MIUIActivity.safeSP.putAny(
                                    "max_wallpaper_scale",
                                    getEditText().toFloat()
                                )
                            }
                            dismiss()
                        }
                    }.show()
                })
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.allow_untrusted_touches,
                tipsId = R.string.take_effect_after_reboot
            ), SwitchV("allow_untrusted_touches")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.bypass_sign_check,
                tipsId = R.string.take_effect_after_reboot
            ), SwitchV("getMinimumSignatureSchemeVersionForTargetSdk")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.kill_domain_verification,
                tipsId = R.string.kill_domain_verification_summary
            ), SwitchV("kill_domain_verification")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.side_hide_freeform,
                tipsId = R.string.side_hide_freeform_summary
            ), SwitchV("side_hide_freeform")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.max_free_form
            ), SwitchV("max_free_form")
        )
        Line()
        TitleText(textId = R.string.sound)
        val mediaVolumeStepsSwitchBinding = GetDataBinding({
            MIUIActivity.safeSP.getBoolean(
                "media_volume_steps_switch",
                false
            )
        }) { view, flags, data ->
            when (flags) {
                1 -> (view as Switch).isEnabled = data as Boolean
                2 -> view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
            }
        }
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.media_volume_steps_switch,
                tips = "${getString(R.string.take_effect_after_reboot)}\n${getString(R.string.media_volume_steps_summary)}"
            ),
            SwitchV(
                "media_volume_steps_switch",
                dataBindingSend = mediaVolumeStepsSwitchBinding.bindingSend
            )
        )
        SeekBarWithText(
            "media_volume_steps",
            15,
            29,
            15,
            dataBindingRecv = mediaVolumeStepsSwitchBinding.binding.getRecv(2)
        )
    }

}
