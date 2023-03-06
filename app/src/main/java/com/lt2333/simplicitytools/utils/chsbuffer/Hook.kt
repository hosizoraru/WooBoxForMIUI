package com.lt2333.simplicitytools.utils.chsbuffer

import com.lt2333.simplicitytools.BuildConfig
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

abstract class Hook {
    companion object {
        val xPrefs = XSharedPreferences(
            BuildConfig.APPLICATION_ID,
            "prefs"
        )
    }

    open fun init(lpparam: LoadPackageParam) {
        init(lpparam.classLoader)
    }

    open fun init(classLoader: ClassLoader) {

    }
}