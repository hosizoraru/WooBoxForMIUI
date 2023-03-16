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

@BMPage("scope_gallery", "Gallery", hideMenu = false)

class GalleryMediaEditorPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_gallery)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.Unlock_HDR_Enhance,
                tipsId = R.string.Unlock_HDR_Enhance_summary
            ), SwitchV("Unlock_HDR_Enhance")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_ocr,
            ), SwitchV("enable_ocr")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_ocr_form,
            ), SwitchV("enable_ocr_form")
        )
        Line()
        TitleText(textId = R.string.scope_mediaeditor)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.unlock_unlimited_cropping,
                tipsId = R.string.unlock_unlimited_cropping_summary
            ), SwitchV("unlock_unlimited_cropping")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.filter_manager,
            ), SwitchV("filter_manager")
        )
    }
}