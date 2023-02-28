package com.lt2333.simplicitytools.hooks.rules.t.systemui

import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object IconPositionForT : HookRegister() {

    override fun init() {
        findMethod("com.android.systemui.statusbar.phone.MiuiDripLeftStatusBarIconControllerImpl") {
            name == "setIconVisibility" && parameterCount == 2
        }.hookBefore {
            when (it.args[0] as String) {
                // 移除左侧闹钟
                "alarm_clock" -> hasEnable("atright_alarm_icon") {
                    it.args[1] = false
                }
                // 移除左侧NFC
                "nfc" -> hasEnable("atright_nfc_icon") {
                    it.args[1] = false
                }
                // 移除左侧声音
                "volume" -> hasEnable("atright_volume_icon") {
                    it.args[1] = false
                }
                // 移除左侧勿扰
                "zen" -> hasEnable("atright_zen_icon") {
                    it.args[1] = false
                }
            }
        }

        findMethod("com.android.systemui.statusbar.phone.StatusBarIconController\$IconManager") {
            name == "setBlockList" && parameterTypes[0] == MutableList::class.java
        }.hookBefore {
            if (it.args[0] != null) {
                val rightBlockList = it.args[0] as MutableList<*>
                // 显示右侧闹钟
                hasEnable("atright_alarm_icon") {
                    rightBlockList.remove("alarm_clock")
                }
                // 显示右侧NFC
                hasEnable("atright_nfc_icon") {
                    rightBlockList.remove("nfc")
                }
                // 显示右侧声音
                hasEnable("atright_volume_icon") {
                    rightBlockList.remove("volume")
                }
                // 显示右侧勿扰
                hasEnable("atright_zen_icon") {
                    rightBlockList.remove("zen")
                }
            }
        }


    }

}