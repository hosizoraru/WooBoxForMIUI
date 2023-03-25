package com.lt2333.simplicitytools.activity.pages.all

import android.view.View
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("scope_miai", "Mi Ai", hideMenu = false)

class MiAiPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_miai)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.intent_browser),
            SwitchV("intent_browser")
        )
        Line()
        TitleText(textId = R.string.scope_scanner)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_card,
            ), SwitchV("enable_card")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_doc_ppt,
            ), SwitchV("enable_doc_ppt")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_ocr2,
            ), SwitchV("enable_ocr2")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_translation,
            ), SwitchV("enable_translation")
        )
        val documentBinding = GetDataBinding({ MIUIActivity.safeSP.getBoolean("enable_document", false) }) { view, flags, data ->
            if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
        }
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.enable_document
            ), SwitchV(
                "enable_document",
                false,
                dataBindingSend = documentBinding.bindingSend
            )
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.enable_excel),
            SwitchV("enable_excel", false),
            dataBindingRecv = documentBinding.binding.getRecv(1)
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.enable_ppt),
            SwitchV("enable_ppt", false),
            dataBindingRecv = documentBinding.binding.getRecv(1)
        )
        Line()
        TitleText(textId = R.string.scope_aireco)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.device_modify,
                tipsId = R.string.device_modify_summary,
            ), SwitchV("device_modify")
        )
        Line()
        TitleText(textId = R.string.scope_taplus)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.horizontal_contentextension),
            SwitchV("horizontal_contentextension")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.double_press),
            SwitchV("double_press")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.super_image),
            SwitchV("super_image")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.intent_browser),
            SwitchV("intent_browser")
        )
    }
}