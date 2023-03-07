package com.lt2333.simplicitytools.hooks.rules.all.securitycenter

import de.robv.android.xposed.callbacks.XC_LoadPackage
import com.lt2333.simplicitytools.utils.chsbuffer.*


object SecurityHost : Hook() {
    override fun init(lpparam: XC_LoadPackage.LoadPackageParam) {
        inContext(lpparam) { app ->
            useDexKit(lpparam) { dexKit ->
                hooks(
                    lpparam,
                    RemoveBehaviorRecordWhiteListAndNoIgnoreSystemApp(dexKit),
                    RemoveSetSystemAppWifiRuleAllow,
                    EnabledAllTextView,
                    AppDetails(dexKit, app),
                    IntlEnableBehaviorRecord(dexKit),
                )
            }
        }
    }
}