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

@BMPage("scope_wini", "WINI", hideMenu = false)

class WiniPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_wini)
        val BlurBinding = GetDataBinding({ safeSP.getBoolean("blur_when_show_shortcut_menu", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.blur_when_show_shortcut_menu),
            SwitchV("blur_when_show_shortcut_menu", dataBindingSend = BlurBinding.bindingSend)
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
        TextSummaryWithSeekBar(
            TextSummaryV(textId = R.string.shortcutMenuBackgroundAlpha),
            SeekBarWithTextV("shortcutMenuBackgroundAlpha", 120, 255, 255),
            dataBindingRecv = BlurBinding.getRecv(1)
        )
    }
}