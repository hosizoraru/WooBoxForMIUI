package com.lt2333.simplicitytools.hooks.rules.t.systemui

import android.content.ComponentName
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.XSPUtils
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.views.WeatherView

object OldNotificationWeatherForT : HookRegister() {

    override fun init() = hasEnable("notification_weather") {
        var mWeatherView: TextView? = null
        val isDisplayCity = XSPUtils.getBoolean("notification_weather_city", false)
        findMethod("com.android.systemui.qs.MiuiQSHeaderView") {
            name == "onFinishInflate"
        }.hookAfter {
            val viewGroup = it.thisObject as ViewGroup
            val context = viewGroup.context
            val layoutParam =
                loadClass("androidx.constraintlayout.widget.ConstraintLayout\$LayoutParams")
                    .getConstructor(Int::class.java, Int::class.java)
                    .newInstance(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ) as ViewGroup.MarginLayoutParams

            layoutParam.putObject(
                "endToStart",
                context.resources.getIdentifier(
                    "notification_shade_shortcut",
                    "id",
                    context.packageName
                )
            )
            layoutParam.putObject(
                "topToTop",
                context.resources.getIdentifier(
                    "notification_shade_shortcut",
                    "id",
                    context.packageName
                )
            )
            layoutParam.putObject(
                "bottomToBottom",
                context.resources.getIdentifier(
                    "notification_shade_shortcut",
                    "id",
                    context.packageName
                )
            )

            mWeatherView = WeatherView(context, isDisplayCity).apply {
                setTextAppearance(
                    context.resources.getIdentifier(
                        "TextAppearance.StatusBar.Expanded.Clock.QuickSettingDate",
                        "style",
                        context.packageName
                    )
                )
                layoutParams = layoutParam
            }
            viewGroup.addView(mWeatherView)
            (mWeatherView as WeatherView).setOnClickListener {
                try {
                    val intent = Intent().apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        component = ComponentName(
                            "com.miui.weather2",
                            "com.miui.weather2.ActivityWeatherMain"
                        )
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "启动失败，可能是不支持", Toast.LENGTH_LONG).show()
                }
            }
        }

        //解决横屏重叠
        findMethod("com.android.systemui.qs.MiuiQSHeaderView") {
            name == "updateLayout"
        }.hookAfter {
            val mOritation = it.thisObject.getObject("mOrientation") as Int
            if (mOritation == 1) {
                mWeatherView!!.visibility = View.VISIBLE
            } else {
                mWeatherView!!.visibility = View.GONE
            }
        }
    }

}
