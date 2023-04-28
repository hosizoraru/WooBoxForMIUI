package com.lt2333.simplicitytools.hooks.rules.all.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SettingsDontThroughTheList implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.android.settings")){
            XposedHelpers.findAndHookMethod("com.android.settings.SettingsActivity", loadPackageParam.classLoader, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("onCreate已执行");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object thisObject = param.thisObject;
                    Context context = (Context) thisObject;
                    Method getIntentMethod = thisObject.getClass().getMethod("getIntent");
                    Intent intent = (Intent) getIntentMethod.invoke(thisObject);
                    XposedBridge.log("settingsIntent:" + intent.toString());
                    if (intent.getAction().equals("android.settings.action.MANAGE_OVERLAY_PERMISSION")){
                        //intent中的data Uri 示例： package:com.xxx.xxxxxxx ，故去掉前面的package就是应用包名
                        String packageName = intent.getData().toString().substring(8);
                        @SuppressLint("PrivateApi") Intent intentOpenSub = new Intent(context,loadPackageParam.classLoader.loadClass("com.android.settings.SubSettings"));
                        intentOpenSub.setAction("android.intent.action.MAIN");
                        intentOpenSub.putExtra(":settings:source_metrics",221);
                        intentOpenSub.putExtra(":settings:show_fragment","com.android.settings.applications.appinfo.DrawOverlayDetails");
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("package",packageName);
                        intentOpenSub.putExtra(":settings:show_fragment_args",bundle1);
                        context.startActivity(intentOpenSub);
                    }
                }
            });
        }
    }
}
