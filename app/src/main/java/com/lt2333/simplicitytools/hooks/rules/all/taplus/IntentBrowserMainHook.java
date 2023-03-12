package com.lt2333.simplicitytools.hooks.rules.all.taplus;

import com.lt2333.simplicitytools.hooks.rules.all.voiceassist.IntentBrowserForAll2;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class IntentBrowserMainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        switch (lpparam.packageName) {
            case "com.miui.contentextension" -> new IntentBrowserForAll1(lpparam);
            case "com.miui.voiceassist" -> new IntentBrowserForAll2(lpparam);
        }
    }
}
