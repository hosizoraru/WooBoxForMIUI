package com.lt2333.simplicitytools.hooks.rules.all.securitycenter

import android.view.View
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.utils.chsbuffer.Hook
import com.lt2333.simplicitytools.utils.hasEnable
import de.robv.android.xposed.XC_MethodReplacement
import io.luckypray.dexkit.DexKitBridge
import io.luckypray.dexkit.enums.MatchType
import de.robv.android.xposed.XposedBridge

class LockOneHundredForAll(val dexKit: DexKitBridge) : Hook() {

    override fun init(classLoader: ClassLoader) = hasEnable("lock_one_hundred") {
        //防止点击重新检测
        findMethod("com.miui.securityscan.ui.main.MainContentFrame") {
            name == "onClick" && parameterTypes[0] == View::class.java
        }.hookBefore {
            it.result = null
        }

//        //锁定100分
//        findMethod("com.miui.securityscan.scanner.ScoreManager") {
//            name == "B"
//        }.hookBefore {
//            it.result = 0
//        }

        //锁定100分
        val minusScoreMethod = dexKit.findMethodUsingString {
            usingString = "getMinusPredictScore"
            matchType = MatchType.CONTAINS
            methodDeclareClass = "com.miui.securityscan.scanner.ScoreManager"
        }.single()

        XposedBridge.hookMethod(
            minusScoreMethod.getMethodInstance(classLoader),
            XC_MethodReplacement.returnConstant(0)
        )
    }

}