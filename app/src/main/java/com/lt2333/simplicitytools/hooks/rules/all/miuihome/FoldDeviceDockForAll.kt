package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookMethod
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.hookAfterMethod
import com.lt2333.simplicitytools.utils.hookBeforeMethod
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XC_MethodHook

object FoldDeviceDockForAll : HookRegister() {
    override fun init() = hasEnable("home_fold_dock") {
        var hook1: XC_MethodHook.Unhook? = null
        var hook2: XC_MethodHook.Unhook? = null
        var hook3: XC_MethodHook.Unhook? = null
        findMethod("com.miui.home.launcher.hotseats.HotSeats") {
            name == "initContent"
        }.hookMethod {
            before {
                hook1 = "com.miui.home.launcher.DeviceConfig".hookBeforeMethod(
                    "isFoldDevice"
                ) { hookParam ->
                    hookParam.result = true
                }
            }
            after {
                hook1?.unhook()
            }
        }

        try {
            findMethod("com.miui.home.launcher.hotseats.HotSeats") {
                name == "updateContent"
            }
        } catch (e: Exception) {
            findMethod("com.miui.home.launcher.hotseats.HotSeats") {
                name == "updateContentView"
            }
        }.hookMethod {
            before {
                hook2 = "com.miui.home.launcher.Application".hookBeforeMethod(
                    "isInFoldLargeScreen"
                ) { hookParam ->
                    hookParam.result = true
                }
            }
            after {
                hook2?.unhook()

            }
        }

        findMethod("com.miui.home.launcher.hotseats.HotSeats") {
            name == "isNeedUpdateItemInfo"
        }.hookMethod {
            before {
                hook2 = "com.miui.home.launcher.Application".hookBeforeMethod(
                    "isInFoldLargeScreen"
                ) { hookParam ->
                    hookParam.result = true
                }
            }
            after {
                hook2?.unhook()
            }
        }

        findMethod("com.miui.home.launcher.hotseats.HotSeatsListRecentsAppProvider\$1") {
            name == "handleMessage" && parameterCount == 1
        }.hookMethod {
            before {
                hook3 = "com.miui.home.launcher.Application".hookBeforeMethod("isInFoldLargeScreen") { hookParam -> hookParam.result = true }
            }
            after { hook3?.unhook() }
        }

        "com.miui.home.launcher.DeviceConfig".hookAfterMethod("getHotseatMaxCount") {
            it.result = getInt("home_fold_dock_hotseat", 3)
        }

        "com.miui.home.launcher.hotseats.HotSeatsListRecentsAppProvider".hookBeforeMethod("getLimitCount") {
            it.result = getInt("home_fold_dock_run", 2)
        }

        "com.miui.home.launcher.allapps.LauncherMode".hookBeforeMethod("isHomeSupportSearchBar", Context::class.java) {
            it.result = false
        }
    }
}