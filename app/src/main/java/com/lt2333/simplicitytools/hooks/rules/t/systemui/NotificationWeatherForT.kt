package com.lt2333.simplicitytools.hooks.rules.t.systemui

import android.content.ComponentName
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import cn.fkj233.ui.activity.dp2px
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.SystemProperties
import com.lt2333.simplicitytools.utils.XSPUtils
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.views.WeatherView

object NotificationWeatherForT : HookRegister() {

    override fun init() = hasEnable("notification_weather") {
        var mWeatherView: TextView? = null
        var mConstraintLayout: ConstraintLayout? = null
        val isDisplayCity = XSPUtils.getBoolean("notification_weather_city", false)
        findMethod("com.android.systemui.qs.MiuiNotificationHeaderView") {
            name == "onFinishInflate"
        }.hookAfter { param ->
            val viewGroup = param.thisObject as ViewGroup
            val context = viewGroup.context

            // MIUI编译时间大于 2022-03-12 00:00:00 且为内测版
            if (SystemProperties[context, "ro.build.date.utc"].toInt() >= 1647014400 &&
                !SystemProperties[context, "ro.build.version.incremental"].endsWith("XM")
            ) {
                //获取原组件
                val bigTimeId =
                    context.resources.getIdentifier("big_time", "id", context.packageName)
                val bigTime: TextView = viewGroup.findViewById(bigTimeId)

                val dateTimeId =
                    context.resources.getIdentifier("date_time", "id", context.packageName)
                val dateTime: TextView = viewGroup.findViewById(dateTimeId)

                //创建新布局
                val mConstraintLayoutLp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).also {
                    it.topMargin = context.resources.getDimensionPixelSize(
                        context.resources.getIdentifier(
                            "qs_control_header_tiles_margin_top",
                            "dimen",
                            context.packageName
                        )
                    )
                }

                mConstraintLayout =
                    ConstraintLayout(context).also { it.layoutParams = mConstraintLayoutLp }

                (bigTime.parent as ViewGroup).addView(mConstraintLayout, 0)


                //从原布局中删除组件
                (bigTime.parent as ViewGroup).removeView(bigTime)
                (dateTime.parent as ViewGroup).removeView(dateTime)


                //添加组件至新布局
                mConstraintLayout!!.addView(bigTime)
                mConstraintLayout!!.addView(dateTime)

                //组件属性

                val dateTimeLp = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).also {
                    it.startToEnd = bigTimeId
                    it.bottomToBottom = 0
                    it.marginStart = context.resources.getDimensionPixelSize(
                        context.resources.getIdentifier(
                            "notification_panel_time_date_space",
                            "dimen",
                            context.packageName
                        )
                    )
                    it.bottomMargin = dp2px(context, 5f)
                }
                dateTime.layoutParams = dateTimeLp


                //创建天气组件
                mWeatherView = WeatherView(context, isDisplayCity).apply {
                    setTextAppearance(
                        context.resources.getIdentifier(
                            "TextAppearance.QSControl.Date",
                            "style",
                            context.packageName
                        )
                    )

                }
                mConstraintLayout!!.addView(mWeatherView)

                val mweatherviewLp = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).also {
                    it.startToEnd = bigTimeId
                    it.bottomToTop = dateTimeId
                    it.marginStart = context.resources.getDimensionPixelSize(
                        context.resources.getIdentifier(
                            "notification_panel_time_date_space",
                            "dimen",
                            context.packageName
                        )
                    )
                }

                (mWeatherView as WeatherView).layoutParams = mweatherviewLp

            } else {
                val layoutParam =
                    loadClass("androidx.constraintlayout.widget.ConstraintLayout\$LayoutParams").getConstructor(
                        Int::class.java,
                        Int::class.java
                    ).newInstance(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ) as ViewGroup.MarginLayoutParams
                layoutParam.putObject(
                    "bottomToTop",
                    context.resources.getIdentifier("date_time", "id", context.packageName)
                )
                layoutParam.putObject(
                    "startToEnd",
                    context.resources.getIdentifier("big_time", "id", context.packageName)
                )
                layoutParam.marginStart = context.resources.getDimensionPixelSize(
                    context.resources.getIdentifier(
                        "notification_panel_time_date_space",
                        "dimen",
                        context.packageName
                    )
                )
                mWeatherView = WeatherView(context, isDisplayCity).apply {
                    setTextAppearance(
                        context.resources.getIdentifier(
                            "TextAppearance.QSControl.Date",
                            "style",
                            context.packageName
                        )
                    )
                    layoutParams = layoutParam
                }
                viewGroup.addView(mWeatherView)
            }

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
                    Toast.makeText(context, "启动失败", Toast.LENGTH_LONG).show()
                }
            }
        }

        findMethod("com.android.systemui.qs.MiuiNotificationHeaderView") {
            name == "updateLayout"
        }.hookAfter {
            val viewGroup = it.thisObject as ViewGroup
            val context = viewGroup.context
            val mOrientation = viewGroup.getObject("mOrientation") as Int
            // MIUI编译时间大于 2022-03-12 00:00:00 且为内测版
            if (SystemProperties[context, "ro.build.date.utc"]!!.toInt() >= 1647014400 && !SystemProperties[context, "ro.build.version.incremental"]!!.endsWith(
                    "DEV"
                ) && !SystemProperties[context, "ro.build.version.incremental"]!!.endsWith("XM")
            ) {
                if (mOrientation == 1) {
                    mConstraintLayout!!.visibility = View.VISIBLE
                } else {
                    mConstraintLayout!!.visibility = View.GONE
                }
            } else {
                if (mOrientation == 1) {
                    mWeatherView!!.visibility = View.VISIBLE
                } else {
                    mWeatherView!!.visibility = View.GONE
                }
            }
        }
    }
}