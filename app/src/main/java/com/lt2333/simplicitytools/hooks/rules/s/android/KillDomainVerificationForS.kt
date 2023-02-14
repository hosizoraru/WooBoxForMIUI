package com.lt2333.simplicitytools.hooks.rules.s.android

import android.os.Build
import android.content.ContentResolver
import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object KillDomainVerificationForS : HookRegister() {
    override fun init() = hasEnable("kill_domain_verification") {

        findMethod("com.android.server.pm.verify.domain.DomainVerificationUtils") {
            name == "isDomainVerificationIntent" &&
                    paramCount == 2 &&
                    parameterTypes[0] == Intent::class.java &&
                    parameterTypes[1] == Int::class.java
        }.hookReturnConstant(false)
    }
}