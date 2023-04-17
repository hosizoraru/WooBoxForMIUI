package com.lt2333.simplicitytools.utils.Yife

object SharedPreferences {
    fun android.content.SharedPreferences.putStringSet(
        key: String,
        value: Set<String>
    ) {
        val editor = edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

}