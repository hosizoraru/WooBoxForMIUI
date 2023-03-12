package com.lt2333.simplicitytools.hooks.rules.all.taplus;

import android.app.SearchManager;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class IntentBrowserForAll1 {
    public IntentBrowserForAll1(XC_LoadPackage.LoadPackageParam lpparam) {
        final Class<?> clazz = XposedHelpers.findClass("com.miui.contentextension.utils.AppsUtils", lpparam.classLoader);
        //getClassInfo(clazz);

        XposedHelpers.findAndHookMethod(clazz, "getIntentWithBrowser", String.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                Uri uri = Uri.parse(param.args[0].toString());
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                return intent;
            }
        });

        XposedHelpers.findAndHookMethod(clazz, "openGlobalSearch", Context.class, String.class, String.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, param.args[1].toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Context) param.args[0]).startActivity(intent);
                return null;
            }
        });
    }
}
