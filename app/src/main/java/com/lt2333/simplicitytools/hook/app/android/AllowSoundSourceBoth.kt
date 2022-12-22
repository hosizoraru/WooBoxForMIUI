package com.lt2333.simplicitytools.hook.app.android

import com.lt2333.simplicitytools.util.XSPUtils
import com.lt2333.simplicitytools.util.xposed.base.HookRegister

object AllowSoundSourceBoth : HookRegister() {
    override fun init() {
        {
          if (it.args[0] == "ro.vendor.audio.playbackcapture.screen") it.result = true
        }
    }
}