package com.lt2333.simplicitytools.activity.pages.t

import android.content.ComponentName
import android.content.Intent
import android.view.View
import android.widget.Switch
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.lt2333.simplicitytools.R

@BMPage("scope_powerkeeper", "PowerKeeper", hideMenu = false)
class PowerKeeperPageForT : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_powerkeeper)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.lock_max_fps,
                tipsId = R.string.lock_max_fps_summary
            ), SwitchV("lock_max_fps")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.prevent_recovery_of_battery_optimization_white_list,
                tipsId = R.string.failed_after_restart
            ), SwitchV("prevent_recovery_of_battery_optimization_white_list")
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.do_not_clear_app,
                tipsId = R.string.do_not_clear_app_summary
            ), SwitchV("do_not_clear_app")
        )
        val makeMilletMoreAggressiveSwitchBinding = GetDataBinding({
            MIUIActivity.safeSP.getBoolean(
                "make_millet_more_aggressive",
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
                textId = R.string.make_millet_more_aggressive,
                tipsId = R.string.make_millet_more_aggressive_summary
            ),
            SwitchV(
                "make_millet_more_aggressive",
                dataBindingSend = makeMilletMoreAggressiveSwitchBinding.bindingSend
            )
        )
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.make_millet_ignore_active,
                tipsId = R.string.make_millet_ignore_active_summary
            ),
            SwitchV("make_millet_ignore_active"),
            dataBindingRecv = makeMilletMoreAggressiveSwitchBinding.binding.getRecv(2)
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.battery_optimization,
                tipsId = R.string.battery_optimization_summary,
                onClickListener = {
                    try {
                        val intent = Intent()
                        val comp = ComponentName(
                            "com.android.settings",
                            "com.android.settings.Settings\$HighPowerApplicationsActivity"
                        )
                        intent.component = comp
                        activity.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(activity, "启动失败，可能是不支持", Toast.LENGTH_LONG).show()
                    }
                })
        )
        //自定义高刷新率应用
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.custom_refresh_rate,
            ), SwitchV("custom_refresh_rate")
        )
    }
}