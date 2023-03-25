package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import com.lt2333.simplicitytools.hooks.rules.all.scanner.*
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Scanner : AppRegister() {
    override val packageName: String = "com.xiaomi.scanner"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    EnableCard, // 解锁扫名片
                    EnableDocPpt, // 解锁扫文件
                    EnableDocument, // 解锁扫文档
                    EnableExcel, // 解锁扫提取表格
                    EnableOcr, // 解锁识文字
                    EnablePpt, // 解锁提取ppt
                    EnableTranslation, // 解锁翻译
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    EnableCard, // 解锁扫名片
                    EnableDocPpt, // 解锁扫文件
                    EnableDocument, // 解锁扫文档
                    EnableExcel, // 解锁扫提取表格
                    EnableOcr, // 解锁识文字
                    EnablePpt, // 解锁提取ppt
                    EnableTranslation, // 解锁翻译
                )
            }
        }
    }
}