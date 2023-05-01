package com.lt2333.simplicitytools.activity.pages.all

import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SpinnerV
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.hooks.apps.Market

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
        TextSummaryWithSpinner(
            TextSummaryV(
                textId = R.string.Market_As,
            ),
            SpinnerV(MIUIActivity.safeSP.getString("Market_As", "Mi13Ultra"),dropDownWidth = 200F) {
                add("Default") {
                    MIUIActivity.safeSP.putAny("Market_As", "Default")
                }
                add("Mi13Pro") {
                    MIUIActivity.safeSP.putAny("Market_As", "Mi13Pro")
                }
                add("Mi13Ultra") {
                    MIUIActivity.safeSP.putAny("Market_As", "Mi13Ultra")
                }
                add("MiPad5Pro12.4") {
                    MIUIActivity.safeSP.putAny("Market_As", "MiPad5Pro12.4")
                }
                add("MiPad6Pro") {
                    MIUIActivity.safeSP.putAny("Market_As", "MiPad6Pro")
                }
                add("MixFold2") {
                    MIUIActivity.safeSP.putAny("Market_As", "MixFold2")
                }
            },
        )
    }
}