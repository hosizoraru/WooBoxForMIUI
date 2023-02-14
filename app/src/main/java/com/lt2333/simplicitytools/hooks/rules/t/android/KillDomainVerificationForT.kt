package com.lt2333.simplicitytools.hooks.rules.t.android

import android.os.Build
import android.content.ContentResolver
import android.content.Intent
import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister

object KillDomainVerificationForT : HookRegister() {
    override fun init() = hasEnable("kill_domain_verification") {

        findMethod("com.android.server.pm.verify.domain.DomainVerificationUtils") {
            name == "isDomainVerificationIntent" &&
                    paramCount == 2 &&
                    parameterTypes[0] == Intent::class.java &&
                    parameterTypes[1] == Long::class.java
        }.hookReturnConstant(false)
    }
}
