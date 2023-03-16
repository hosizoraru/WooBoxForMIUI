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
                textId = R.string.enable_pdf,
            ), SwitchV("enable_pdf")
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
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_id_photo,
            ), SwitchV("enable_id_photo")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_photo_movie,
            ), SwitchV("enable_photo_movie")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_video_post,
            ), SwitchV("enable_video_post")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_video_editor,
            ), SwitchV("enable_video_editor")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_text_yanhua,
            ), SwitchV("enable_text_yanhua")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_remover_2,
            ), SwitchV("enable_remover_2")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_magic_matting,
            ), SwitchV("enable_magic_matting")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_magic_sky,
            ), SwitchV("enable_magic_sky")
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