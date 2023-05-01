package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import android.app.Activity
import android.app.AndroidAppHelper
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.github.kyuubiran.ezxhelper.init.InitFields.moduleRes
import com.github.kyuubiran.ezxhelper.utils.Log
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.utils.Sevtinge.Helpers.getModuleRes
import com.lt2333.simplicitytools.utils.callMethod
import com.lt2333.simplicitytools.utils.callStaticMethod
import com.lt2333.simplicitytools.utils.findClass
import com.lt2333.simplicitytools.utils.getStaticObjectField
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.hookAfterAllMethods
import com.lt2333.simplicitytools.utils.hookBeforeAllMethods
import com.lt2333.simplicitytools.utils.hookBeforeMethod
import com.lt2333.simplicitytools.utils.setStaticObjectField
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers


object ShortcutAddSmallWindowForAll : HookRegister() {
//    private var mActivityUtilsCompat: Class<*>? = null
    override fun init() = hasEnable("miuihome_shortcut_add_small_window") {

    val mViewDarkModeHelper = ("com.miui.home.launcher.util.ViewDarkModeHelper").findClass()
    val mSystemShortcutMenu = ("com.miui.home.launcher.shortcuts.SystemShortcutMenu").findClass()
    val mSystemShortcutMenuItem = ("com.miui.home.launcher.shortcuts.SystemShortcutMenuItem").findClass()
    val mAppShortcutMenu = ("com.miui.home.launcher.shortcuts.AppShortcutMenu").findClass()
    val mShortcutMenuItem = ("com.miui.home.launcher.shortcuts.ShortcutMenuItem").findClass()
    val mAppDetailsShortcutMenuItem = ("com.miui.home.launcher.shortcuts.SystemShortcutMenuItem\$AppDetailsShortcutMenuItem").findClass()
    val mActivityUtilsCompat = ("com.miui.launcher.utils.ActivityUtilsCompat").findClass()

    mViewDarkModeHelper.hookAfterAllMethods("onConfigurationChanged") {
        mSystemShortcutMenuItem.callStaticMethod("createAllSystemShortcutMenuItems")
    }
    mShortcutMenuItem.hookAfterAllMethods("getShortTitle") {
        if (it.result == "应用信息") {
            it.result = "信息"
        }
    }
    mAppDetailsShortcutMenuItem.hookBeforeMethod("lambda\$getOnClickListener$0", mAppDetailsShortcutMenuItem, View::class.java) {
        val obj = it.args[0]
        val view: View = it.args[1] as View
        val mShortTitle = obj.callMethod("getShortTitle") as CharSequence
        if (mShortTitle == moduleRes.getString(R.string.miuihome_shortcut_add_small_window_title)) {
            it.result = null
            val intent = Intent()
            val mComponentName = obj.callMethod("getComponentName") as ComponentName
            intent.action = "android.intent.action.MAIN"
            intent.addCategory("android.intent.category.LAUNCHER")
            intent.component = mComponentName
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val callStaticMethod = mActivityUtilsCompat.callStaticMethod("makeFreeformActivityOptions", view.context, mComponentName.packageName)
            if (callStaticMethod != null) {
                view.context.startActivity(intent, callStaticMethod.callMethod("toBundle") as Bundle)
            }
        }
    }
    mSystemShortcutMenu.hookAfterAllMethods("getMaxShortcutItemCount") {
        it.result = 5
    }
    mAppShortcutMenu.hookAfterAllMethods("getMaxShortcutItemCount") {
        it.result = 5
    }
    mSystemShortcutMenuItem.hookAfterAllMethods("createAllSystemShortcutMenuItems") {
        val isDarkMode =
            AndroidAppHelper.currentApplication().applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        val mAllSystemShortcutMenuItems = mSystemShortcutMenuItem.getStaticObjectField("sAllSystemShortcutMenuItems") as Collection<*>
        val mSmallWindowInstance = XposedHelpers.newInstance(mAppDetailsShortcutMenuItem)
        val drawableResId = if (isDarkMode) R.drawable.ic_small_window_dark else R.drawable.ic_small_window_light
        val typedValue = TypedValue()
        mSmallWindowInstance.callMethod("setShortTitle", moduleRes.getString(R.string.miuihome_shortcut_add_small_window_title))
        mSmallWindowInstance.callMethod(
            "setIconDrawable",
            moduleRes.getValue(drawableResId,typedValue,true)
        )
        val sAllSystemShortcutMenuItems = ArrayList<Any>()
        sAllSystemShortcutMenuItems.add(mSmallWindowInstance)
        sAllSystemShortcutMenuItems.addAll(listOf(mAllSystemShortcutMenuItems))
        mSystemShortcutMenuItem.setStaticObjectField("sAllSystemShortcutMenuItems", sAllSystemShortcutMenuItems)
    }

//        lateinit var mContext: Context
//        val mRecentsAndFSGestureUtils: Class<*>? = null
//        val mActivity = Activity::class.java
//        val mViewDarkModeHelper = ("com.miui.home.launcher.util.ViewDarkModeHelper").findClass()
//        val mSystemShortcutMenu =
//            ("com.miui.home.launcher.shortcuts.SystemShortcutMenu").findClass()
//        val mSystemShortcutMenuItem =
//            ("com.miui.home.launcher.shortcuts.SystemShortcutMenuItem").findClass()
//        val mAppShortcutMenu = ("com.miui.home.launcher.shortcuts.AppShortcutMenu").findClass()
//        val mShortcutMenuItem = ("com.miui.home.launcher.shortcuts.ShortcutMenuItem").findClass()
//        val mAppDetailsShortcutMenuItem =
//            ("com.miui.home.launcher.shortcuts.SystemShortcutMenuItem\$AppDetailsShortcutMenuItem").findClass()
//        mActivityUtilsCompat = ("com.miui.launcher.utils.ActivityUtilsCompat").findClass()
//
//
//        mViewDarkModeHelper.hookAfterAllMethods("onConfigurationChanged") {
//            mSystemShortcutMenuItem.callStaticMethod("createAllSystemShortcutMenuItems")
//        }
//
//        mShortcutMenuItem.hookAfterAllMethods("getShortTitle") { param ->
////            if (it.result == "应用信息") {
////                it.result = "信息"
////            }
//            if (param.result.equals("应用信息")) {
//                param.result = "信息"
//            }
//        }
////        findAllMethods(mActivity) {
////            name == "onCreate"
////        }.hookAfter { param->
////            mContext = param.thisObject as Context
////        }
//        mActivity.hookAfterAllMethods("onCreate") { param ->
//            mContext = param.thisObject as Context
//        }
//        mAppDetailsShortcutMenuItem.hookBeforeAllMethods("getOnClickListener") { param ->
//            val modRes: Resources = getModuleRes(mContext)
//            val obj = param.thisObject
//            val mShortTitle = XposedHelpers.callMethod(obj, "getShortTitle") as CharSequence
//            if (mShortTitle == modRes.getString(R.string.share_center)) {
//                XposedHelpers.callStaticMethod(mRecentsAndFSGestureUtils, "startWorld", mContext)
//            } else if (mShortTitle == modRes.getString(R.string.miuihome_shortcut_add_small_window_title)) {
//                param.result = getFreeformOnClickListener(obj)
//            }
//        }
////        findAllMethods(mAppDetailsShortcutMenuItem) {
////            name == "getOnClickListener"
////        }.hookBefore { param ->
////            val modRes: Resources = getModuleRes(mContext)
////            val obj = param.thisObject
////            val mShortTitle = XposedHelpers.callMethod(obj, "getShortTitle") as CharSequence
////            if (mShortTitle == modRes.getString(R.string.share_center)) {
////                XposedHelpers.callStaticMethod(mRecentsAndFSGestureUtils, "startWorld", mContext)
////            } else if (mShortTitle == modRes.getString(R.string.miuihome_shortcut_add_small_window_title)) {
////                param.result = getFreeformOnClickListener(obj)
////            }
////        }
////        mAppDetailsShortcutMenuItem.hookBeforeMethod("lambda\$getOnClickListener$0", mAppDetailsShortcutMenuItem, View::class.java) {
////            val obj = it.args[0]
////            val view: View = it.args[1] as View
////            val mShortTitle = obj.callMethod("getShortTitle") as CharSequence
////            if (mShortTitle == moduleRes.getString(R.string.miuihome_shortcut_add_small_window_title)) {
////                it.result = null
////                val intent = Intent()
////                val mComponentName = obj.callMethod("getComponentName") as ComponentName
////                intent.action = "android.intent.action.MAIN"
////                intent.addCategory("android.intent.category.LAUNCHER")
////                intent.component = mComponentName
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////                val callStaticMethod = mActivityUtilsCompat!!.callStaticMethod("makeFreeformActivityOptions", view.context, mComponentName.packageName)
////                if (callStaticMethod != null) {
////                    view.context.startActivity(intent, callStaticMethod.callMethod("toBundle") as Bundle)
////                }
////            }
////        }
//        mSystemShortcutMenu.hookAfterAllMethods("getMaxShortcutItemCount") { param ->
//            param.result = 5
//        }
//        mAppShortcutMenu.hookAfterAllMethods("getMaxShortcutItemCount") { param ->
//            param.result = 5
//        }
//        mSystemShortcutMenuItem.hookAfterAllMethods("createAllSystemShortcutMenuItems") {
//            val modRes: Resources = getModuleRes(mContext)
//            val mAllSystemShortcutMenuItems = XposedHelpers.getStaticObjectField(
//                mSystemShortcutMenuItem,
//                "sAllSystemShortcutMenuItems"
//            ) as List<*>
//            val isDarkMode =
//                AndroidAppHelper.currentApplication().applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
////            val mAllSystemShortcutMenuItems =
////                mSystemShortcutMenuItem.getStaticObjectField("sAllSystemShortcutMenuItems") as Collection<*>
//            val mSmallWindowInstance = XposedHelpers.newInstance(mAppDetailsShortcutMenuItem)
//            val drawableResId =
//                if (isDarkMode) R.drawable.ic_small_window_dark else R.drawable.ic_small_window_light
//            val typedValue = TypedValue()
////            mSmallWindowInstance.callMethod("setShortTitle", moduleRes.getString(R.string.miuihome_shortcut_add_small_window_title))
//            mSmallWindowInstance.callMethod("setShortTitle", modRes.getString(R.string.miuihome_shortcut_add_small_window_title))
////            XposedHelpers.callMethod(
////                mSmallWindowInstance,
////                "setIconDrawable",
////                ContextCompat.getDrawable(
////                    mContext,
////                    mContext.resources.getIdentifier(
////                        "ic_small_window_light",
////                        "drawable",
////                        mContext.packageName
////                    )
////                )
////            )
//            mSmallWindowInstance.callMethod(
//                "setIconDrawable",
//                modRes.getValue(drawableResId, typedValue, true)
//            )
//            val sAllSystemShortcutMenuItems = ArrayList<Any>()
//            sAllSystemShortcutMenuItems.add(mSmallWindowInstance)
//            sAllSystemShortcutMenuItems.addAll(listOf(mAllSystemShortcutMenuItems))
//            mSystemShortcutMenuItem.setStaticObjectField(
//                "sAllSystemShortcutMenuItems",
//                sAllSystemShortcutMenuItems
//            )
//        }
    }

//    private fun getFreeformOnClickListener(obj: Any): View.OnClickListener {
//        mActivityUtilsCompat = ("com.miui.launcher.utils.ActivityUtilsCompat").findClass()
//        return View.OnClickListener { view: View ->
//            val intent = Intent()
//            val mContext = view.context
//            val mComponentName = XposedHelpers.callMethod(
//                obj,
//                "getComponentName",
//                *arrayOfNulls(0)
//            ) as ComponentName
//            intent.action = "android.intent.action.MAIN"
//            intent.addCategory("android.intent.category.DEFAULT")
//            intent.component = mComponentName
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            val makeFreeformActivityOptions = XposedHelpers.callStaticMethod(
//                mActivityUtilsCompat,
//                "makeFreeformActivityOptions",
//                mContext, mComponentName.packageName
//            )
//            if (makeFreeformActivityOptions != null) {
//                mContext.startActivity(
//                    intent,
//                    XposedHelpers.callMethod(
//                        makeFreeformActivityOptions,
//                        "toBundle",
//                        *arrayOfNulls(0)
//                    ) as Bundle
//                )
//            }
//        }
//    }
}