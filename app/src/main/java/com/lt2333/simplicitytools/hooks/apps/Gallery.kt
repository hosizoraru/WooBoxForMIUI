package com.lt2333.simplicitytools.hooks.apps

import android.os.Build
import com.lt2333.simplicitytools.hooks.rules.all.gallery.*
import com.lt2333.simplicitytools.utils.xposed.base.AppRegister
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Gallery : AppRegister() {
    override val packageName: String = "com.miui.gallery"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.TIRAMISU -> {
                autoInitHooks(
                    lpparam,
                    EnableHDREnhanceForAll, // 超动态显示
                    EnableOcrForAll, // 解锁提取文字
                    EnableOcrFormForAll, // 解锁提取表格
                    EnablePdfForAll, // 解锁生成pdf
                    EnablePhotoMovieForAll, // 解锁照片电影
                    EnableIdPhotoForAll, // 解锁证件照
                    EnableVideoPostForAll, // 解锁视频特效
                    EnableVideoEditorForAll, // 解锁Mi剪辑
                    EnableTextYanhuaForAll, // 解锁文字烟花
                    EnableRemover2ForAll, // 解锁魔法消除
                    EnableMagicMattingForAll, // 解锁魔法抠图
                    EnableMagicSkyForAll, // 解锁魔法换天
                )
            }

            Build.VERSION_CODES.S -> {
                autoInitHooks(
                    lpparam,
                    EnableHDREnhanceForAll, // 超动态显示
                    EnableOcrForAll, // 解锁提取文字
                    EnableOcrFormForAll, // 解锁提取表格
                    EnablePdfForAll, // 解锁生成pdf
                    EnablePhotoMovieForAll, // 解锁照片电影
                    EnableIdPhotoForAll, // 解锁证件照
                    EnableVideoPostForAll, // 解锁视频特效
                    EnableVideoEditorForAll, // 解锁Mi剪辑
                    EnableTextYanhuaForAll, // 解锁文字烟花
                    EnableRemover2ForAll, // 解锁魔法消除
                    EnableMagicMattingForAll, // 解锁魔法抠图
                    EnableMagicSkyForAll, // 解锁魔法换天
                )
            }
        }
    }
}