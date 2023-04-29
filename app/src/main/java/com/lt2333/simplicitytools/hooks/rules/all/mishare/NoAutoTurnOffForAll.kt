package com.lt2333.simplicitytools.hooks.rules.all.mishare

import com.github.kyuubiran.ezxhelper.init.InitFields
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.hooks.apps.MiShare
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.dexKitBridge
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.loadDexKit
import com.lt2333.simplicitytools.utils.Yife.YifeDexKit.closeDexKit

object NoAutoTurnOffForAll : HookRegister() {

    override fun init() = hasEnable("No_Auto_Turn_Off") {

        when (MiShare.versionCode) {
            21500 -> {
                findMethod("com.miui.mishare.connectivity.MiShareService\$d\$g") {
                    name == "b"
                }.hookBefore {
                    it.result = null
                }
            }
            21600 -> {
                findMethod("com.miui.mishare.connectivity.MiShareService\$j\$g") {
                    name == "a"
                }.hookBefore {
                    it.result = null
                }
            }
        }

//        loadDexKit()
//        dexKitBridge.findMethodUsingString {
//            usingString = "EnabledState"
//            usingString = "mishare_enabled"
//        }.map {
//            it.getMethodInstance(InitFields.ezXClassLoader)
//        }.hookBefore {
//            it.result = null
//        }
//
//        dexKitBridge.batchFindMethodsUsingStrings {
//            addQuery("qwq", listOf("EnabledState","mishare_enabled"))
//        }.forEach { ( _, classes) ->
//            classes.map {
//                it.getMethodInstance(InitFields.ezXClassLoader)
//            }.hookBefore {
//                it.result = null
//            }
//        }
//        closeDexKit()

//        try {
//            // 2.15.0
//            findMethod("com.miui.mishare.connectivity.MiShareService\$d\$g") {
//                name == "b"
//            }.hookBefore {
//                it.result = null
//            }
//            Log.ix("Voyager-Test: Hook MiShare 2.15.0 Success!")
//        } catch (e: Throwable) {
//            Log.ex("Voyager-Test: Hook MiShare 2.15.0 Fail!")
//        }
//
//        try {
//            // 2.16.0
//            findMethod("com.miui.mishare.connectivity.MiShareService\$j\$g") {
//                name == "a"
//            }.hookBefore {
//                it.result = null
//            }
//            Log.ix("Voyager-Test: Hook MiShare 2.16.0 Success!")
//        } catch (e: Throwable) {
//            Log.ex("Voyager-Test: Hook MiShare 2.16.0 Fail!")
//        }

    }
}