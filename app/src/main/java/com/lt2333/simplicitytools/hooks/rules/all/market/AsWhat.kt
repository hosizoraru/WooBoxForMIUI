package com.lt2333.simplicitytools.hooks.rules.all.market

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.hookAllConstructorBefore
import com.lt2333.simplicitytools.utils.Yife.XSharedPreferences
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object AsWhat : HookRegister() {
    override fun init() {
        val a = XSharedPreferences.getString("Market_As","Mi13Ultra")
        hookAllConstructorBefore("com.xiaomi.market.MarketApp") {
            when(a) {
                "Default"-> {
                    Log.i("Use Your Default Device.")
                }
                "Mi13Pro"-> {
                    XposedHelpers.setStaticObjectField(Build::class.java, "DEVICE", "nuwa")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MODEL", "2210132C")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MANUFACTURER", "Xiaomi")
                }
                "Mi13Ultra"-> {
                    XposedHelpers.setStaticObjectField(Build::class.java, "DEVICE", "ishtar")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MODEL", "2304FPN6DC")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MANUFACTURER", "Xiaomi")
                }
                "MiPad5Pro12.4"-> {
                    XposedHelpers.setStaticObjectField(Build::class.java,"DEVICE", "dagu")
                    XposedHelpers.setStaticObjectField(Build::class.java,"MODEL", "22081281AC")
                    XposedHelpers.setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
                }
                "MiPad6Pro"-> {
                    XposedHelpers.setStaticObjectField(Build::class.java,"DEVICE", "liuqin")
                    XposedHelpers.setStaticObjectField(Build::class.java,"MODEL", "23046RP50C")
                    XposedHelpers.setStaticObjectField(Build::class.java,"MANUFACTURER", "Xiaomi")
                }
                "MixFold2"-> {
                    XposedHelpers.setStaticObjectField(Build::class.java, "DEVICE", "zizhan")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MODEL", "22061218C")
                    XposedHelpers.setStaticObjectField(Build::class.java, "MANUFACTURER", "Xiaomi")
                }
            }
        }
    }
}