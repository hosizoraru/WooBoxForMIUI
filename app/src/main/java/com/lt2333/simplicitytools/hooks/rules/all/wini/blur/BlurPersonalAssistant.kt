package com.lt2333.simplicitytools.hooks.rules.all.wini.blur

import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.lt2333.simplicitytools.utils.wini.HookUtils
import com.lt2333.simplicitytools.hooks.rules.all.wini.model.ConfigModel
import com.lt2333.simplicitytools.utils.wini.ColorUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import kotlin.math.abs

class BlurPersonalAssistant(private val classLoader: ClassLoader, config: ConfigModel) {
    val blurRadius = config.BlurPersonalAssistant.background.blurRadius
    val backgroundColor = ColorUtils.hexToColor(config.BlurPersonalAssistant.background.backgroundColor)

    fun addBlurEffectToPersonalAssistant() {
        val AssistantOverlayWindowClass = HookUtils.getClass(
            "com.miui.personalassistant.core.overlay.AssistantOverlayWindow",
            classLoader
        ) ?: return

        var lastBlurRadius = -1

        XposedHelpers.findAndHookMethod(
            AssistantOverlayWindowClass,
            "a",
            Float::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    val scrollX = param.args[0] as Float
                    val window = HookUtils.getValueByField(param.thisObject, "b") ?: return
                    if (window.javaClass.name.contains("Window")) {
                        try {
                            window as Window
                            val blurRadius = (scrollX * blurRadius).toInt()
                            if (abs(blurRadius - lastBlurRadius) > 2) {
                                window.setBackgroundBlurRadius(blurRadius)
                                lastBlurRadius = blurRadius
                            }
                            val backgroundColorDrawable = ColorDrawable(backgroundColor)
                            backgroundColorDrawable.alpha = (scrollX * 255).toInt()
                            window.setBackgroundDrawable(backgroundColorDrawable)
                        } catch (e: Throwable) {
                            // 重复报错会污染日志
                            //  HookUtils.log(e.message)
                        }
                    }
                }
            })
    }
}