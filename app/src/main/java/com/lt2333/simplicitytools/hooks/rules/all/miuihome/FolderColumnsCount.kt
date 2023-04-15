package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import android.view.ViewGroup
import android.widget.GridView
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.YuKongA.hookAfterAllMethods
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object FolderColumnsCount : HookRegister() {
    override fun init() {
        val value = getInt("home_folder_columns", 3)
        if (value == 3) return
        loadClass("com.miui.home.launcher.Folder").hookAfterAllMethods(
            "bind"
        ) {
            val columns: Int = value
            val mContent = XposedHelpers.getObjectField(it.thisObject, "mContent") as GridView
            mContent.numColumns = columns
            hasEnable("home_folder_width") {
                if (columns > 3) {
                    mContent.setPadding(0, 0, 0, 0)
                    val lp = mContent.layoutParams
                    lp.width = ViewGroup.LayoutParams.MATCH_PARENT
                    mContent.layoutParams = lp
                }
            }

            if (columns > 3) {
                val mBackgroundView = XposedHelpers.getObjectField(it.thisObject, "mBackgroundView") as ViewGroup
                mBackgroundView.setPadding(
                    mBackgroundView.paddingLeft / 3, mBackgroundView.paddingTop, mBackgroundView.paddingRight / 3, mBackgroundView.paddingBottom
                )
            }
        }
    }
}