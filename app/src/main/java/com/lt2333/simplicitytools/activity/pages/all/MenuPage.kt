package com.lt2333.simplicitytools.activity.pages.all

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import cn.fkj233.ui.activity.annotation.BMMenuPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.activity.view.TextV
import cn.fkj233.ui.dialog.MIUIDialog
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.utils.ShellUtils
import com.lt2333.simplicitytools.utils.YuKongA.BackupUtils
import java.util.*


@BMMenuPage("Menu")
class MenuPage : BasePage() {
    @SuppressLint("WorldReadableFiles")
    override fun onCreate() {
        TextSummaryWithArrow(TextSummaryV(textId = R.string.reboot, onClickListener = {
            MIUIDialog(activity) {
                setTitle(R.string.Tips)
                setMessage(R.string.are_you_sure_reboot)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.Done) {
                    val command = arrayOf("reboot")
                    ShellUtils.execCommand(command, true)
                    dismiss()
                }
            }.show()
        }))

        TextSummaryWithArrow(TextSummaryV(textId = R.string.reboot_host, onClickListener = {
            MIUIDialog(activity) {
                setTitle(R.string.Tips)
                setMessage(R.string.are_you_sure_reboot_scope)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.Done) {
                    val command = arrayOf(
                        "killall com.android.systemui",
                        "killall com.miui.home",
                        "killall com.miui.securitycenter ",
                        "killall com.android.settings",
                        "killall com.miui.powerkeeper",
                        "killall com.android.updater",
                        "killall com.miui.mediaeditor",
                        "killall com.miui.screenshot",
                        "killall com.milink.service",
                        "killall com.xiaomi.misubscreenui",
                        "killall com.miui.packageinstaller",
                        "killall com.android.deskclock",
                        "killall com.miui.mishare.connectivity",
                        "killall com.miui.gallery",
                        "killall com.android.thememanager",
                        "killall com.xiaomi.misettings",
                        "killall com.miui.contentextension",
                        "killall com.miui.personalassistant",
                        "killall com.lbe.security.miui",
                        "killall com.android.externalstorage",
                        "killall com.miui.voiceassist",
                        "killall com.miui.guardprovider",
                        "killall com.xiaomi.scanner",
                        "killall com.xiaomi.aireco",
                        "killall com.xiaomi.market",
                        "killall com.android.browser",
                        "killall com.newskyer.draw",
                        "killall com.miui.screenrecorder",
                    )
                    ShellUtils.execCommand(command, true)
                    dismiss()
                }
            }.show()
        }))

        TextSummaryWithArrow(TextSummaryV(textId = R.string.reboot_systemui, onClickListener = {
            MIUIDialog(activity) {
                setTitle(R.string.Tips)
                setMessage(R.string.are_you_sure_reboot_systemui)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.Done) {
                    val command = arrayOf(
                        "killall com.android.systemui",
                    )
                    ShellUtils.execCommand(command, true)
                    dismiss()
                }
            }.show()
        }))

        TextSummaryWithArrow(TextSummaryV(textId = R.string.reboot_miuihome, onClickListener = {
            MIUIDialog(activity) {
                setTitle(R.string.Tips)
                setMessage(R.string.are_you_sure_reboot_miuihome)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.Done) {
                    val command = arrayOf(
                        "killall com.miui.home",
                    )
                    ShellUtils.execCommand(command, true)
                    dismiss()
                }
            }.show()
        }))

        Line()

        TextSummaryWithArrow(TextSummaryV(
            textId = R.string.backup, onClickListener = {
            BackupUtils.backup(activity, activity.createDeviceProtectedStorageContext().getSharedPreferences("config", Context.MODE_WORLD_READABLE))
        }))

        TextSummaryWithArrow(TextSummaryV(textId = R.string.recovery, onClickListener = {
            BackupUtils.recovery(activity, activity.createDeviceProtectedStorageContext().getSharedPreferences("config", Context.MODE_WORLD_READABLE))
        }))

        TextWithArrow(TextV(textId = R.string.ResetModule, onClickListener = {
            MIUIDialog(activity) {
                setTitle(R.string.ResetModuleDialog)
                setMessage(R.string.ResetModuleDialogTips)
                setLButton(R.string.Done) {
                    activity.getSharedPreferences("config", Activity.MODE_WORLD_READABLE).edit().clear().apply()
                    Toast.makeText(activity, activity.getString(R.string.ResetSuccess), Toast.LENGTH_LONG).show()
                }
                setRButton(R.string.cancel)
                finally { dismiss() }
            }.show()
        }))
    }

}
