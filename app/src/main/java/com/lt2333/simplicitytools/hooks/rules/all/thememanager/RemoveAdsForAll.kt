package com.lt2333.simplicitytools.hooks.rules.all.thememanager

import android.view.View
import android.widget.FrameLayout
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.findConstructor
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.DrmManager

object RemoveAdsForAll : HookRegister() {

    override fun init() = hasEnable("remove_thememanager_ads") {
        try {
            findAllMethods(DrmManager::class.java) {
                name == "isSupportAd"
            }.hookBefore {
                it.result = false
            }
        } catch (t: Throwable) {
            Log.ex(t)
        }
        try {
            findAllMethods(DrmManager::class.java) {
                name == "setSupportAd"
            }.hookBefore {
                it.result = false
            }
        } catch (t: Throwable) {
            Log.ex(t)
        }
        try {
            findMethod("com.android.thememanager.basemodule.ad.model.AdInfoResponse") {
                name == "isAdValid" && parameterCount == 1
            }.hookAfter {
                it.result = false
            }
        } catch (t: Throwable) {
            Log.ex(t)
        }
        hook(loadClass("com.android.thememanager.recommend.view.listview.viewholder.PureAdBannerViewHolder"))
        hook(loadClass("com.android.thememanager.recommend.view.listview.viewholder.SelfFontItemAdViewHolder"))
        hook(loadClass("com.android.thememanager.recommend.view.listview.viewholder.SelfRingtoneItemAdViewHolder"))
    }
    private fun hook(clazz: Class<*>) {
        try {
            findConstructor(clazz) {
                parameterTypes.size == 2
            }.hookAfter {
                if (it.args[0] != null) {
                    val view = it.args[0] as View
                    val params = FrameLayout.LayoutParams(0, 0)
                    view.layoutParams = params
                    view.visibility = View.GONE
                }
            }
        } catch (t: Throwable) {
            Log.ex(t)
        }
    }

}