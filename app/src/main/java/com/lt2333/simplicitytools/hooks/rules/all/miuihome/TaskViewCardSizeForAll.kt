package com.lt2333.simplicitytools.hooks.rules.all.miuihome

import android.graphics.RectF
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.XSPUtils.getInt
import com.lt2333.simplicitytools.utils.XSPUtils.getBoolean
import com.lt2333.simplicitytools.utils.callMethod
import com.lt2333.simplicitytools.utils.callStaticMethod
import com.lt2333.simplicitytools.utils.findClass
import com.lt2333.simplicitytools.utils.hookAfterMethod

object TaskViewCardSizeForAll : HookRegister() {

    override fun init() = hasEnable("miuihome_task_view_card_size_binding") {
        val value = getInt("miuihome_task_view_card_size_vertical", 100).toFloat() / 100f
        val value1 = getInt("miuihome_task_view_card_size_horizontal1", 100).toFloat() / 100f
        val value2 = getInt("miuihome_task_view_card_size_horizontal2", 100).toFloat() / 100f

        "com.miui.home.recents.views.TaskStackViewsAlgorithmVertical".hookAfterMethod(
            "scaleTaskView", RectF::class.java
        ) {
            "com.miui.home.recents.util.Utilities".findClass().callStaticMethod(
                "scaleRectAboutCenter", it.args[0], value
            )
        }

        "com.miui.home.recents.views.TaskStackViewsAlgorithmHorizontal".hookAfterMethod(
            "scaleTaskView", RectF::class.java,
        ) {
            "com.miui.home.recents.util.Utilities".findClass().callStaticMethod(
                "scaleRectAboutCenter", it.args[0], if (it.thisObject.callMethod("isLandscapeVisually") as Boolean) value2 else value1
            )
        }
    }
}