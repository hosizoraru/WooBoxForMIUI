package com.lt2333.simplicitytools.hooks.rules.all.fileexplorer

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObject
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import android.widget.TextView
import com.lt2333.simplicitytools.utils.hasEnable
import com.lt2333.simplicitytools.utils.xposed.base.HookRegister
import com.lt2333.simplicitytools.utils.Yife.XSharedPreferences.getBoolean

object SelectName : HookRegister() {
    override fun init() {
        hasEnable("file_explorer_can_selectable") {
            hasEnable("file_explorer_is_single_line") {
                findMethod("com.android.fileexplorer.view.FileListItem") { name == "onFinishInflate" }.hookAfter {
                    (it.thisObject.getObject("mFileNameTextView") as TextView).apply {
                        setTextIsSelectable(getBoolean("file_explorer_can_selectable", false))
                        isSingleLine = getBoolean("file_explorer_is_single_line", false)
                    }
                }
            }
        }
    }
}