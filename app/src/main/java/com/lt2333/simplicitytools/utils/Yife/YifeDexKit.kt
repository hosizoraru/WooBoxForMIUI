package com.lt2333.simplicitytools.utils.Yife

import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.luckypray.dexkit.DexKitBridge

object YifeDexKit {
    lateinit var hostDir: String
    lateinit var dexKitBridge: DexKitBridge

    fun initDexKit(loadPackageParam: LoadPackageParam) {
        hostDir = loadPackageParam.appInfo.sourceDir
    }

    fun loadDexKit() {
        if (this::dexKitBridge.isInitialized) return
        System.loadLibrary("dexkit")
        DexKitBridge.create(hostDir)?.let {
            dexKitBridge = it
        }
    }

    fun closeDexKit() {
        if (this::dexKitBridge.isInitialized) dexKitBridge.close()
    }
}