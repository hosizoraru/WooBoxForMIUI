package com.lt2333.simplicitytools.activity.pages.all

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("pkg_installer", "App Manager", hideMenu = false)
class ApplicationManagementPage: BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.pkg_installer)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.pkg_installer_summary),
            SwitchV("pkg_installer_count_checking")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.packageinstaller_remove_ads),
            SwitchV("packageinstaller_remove_ads")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.packageinstaller_allow_update_system_app),
            SwitchV("packageinstaller_allow_update_system_app")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.packageinstaller_show_more_apk_info),
            SwitchV("packageinstaller_show_more_apk_info")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Disable_Safe_Model_Tip),
            SwitchV("Disable_Safe_Model_Tip")
        )
        Line()
        TitleText(textId = R.string.scope_Market)
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Market_Mi13Pro),
            SwitchV("Market_Mi13Pro")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Market_Mi13Ultra),
            SwitchV("Market_Mi13Ultra")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Market_MiPad5Pro124),
            SwitchV("Market_MiPad5Pro124")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Market_MiPad6Pro),
            SwitchV("Market_MiPad6Pro")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.Market_MixFold2),
            SwitchV("Market_MixFold2")
        )
    }
}