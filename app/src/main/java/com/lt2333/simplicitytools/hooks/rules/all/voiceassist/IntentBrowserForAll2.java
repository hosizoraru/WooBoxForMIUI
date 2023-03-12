package com.lt2333.simplicitytools.hooks.rules.all.voiceassist;

import android.content.Intent;
import android.net.Uri;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class IntentBrowserForAll2 {
    public IntentBrowserForAll2(XC_LoadPackage.LoadPackageParam lpparam) {
        Class<?> clazz = XposedHelpers.findClass("e.D.L.pa.Wa", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(clazz,"startActivityWithIntent", Intent.class, boolean.class,int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Intent intent = (Intent) param.args[0];
                if (intent.getPackage().equals("com.android.browser")) {
                    Uri uri = Uri.parse(intent.getDataString());
                    Intent newIntent = new Intent();
                    newIntent.setAction("android.intent.action.VIEW");
                    newIntent.setData(uri);
                    param.args[0] = newIntent;
                }
            }
        });
    }
}
