package com.example.jepcaktestapp.sp

import android.app.Application
import android.content.Context
import androidx.core.content.edit

class SpUtils(context: Application) : ISharePreferences {

    companion object {
        val SHARE_PREFERENCES_NAME = "share_preferences_name"
    }

    val sp = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putString(key: String, value: String) {
        sp.edit { putString(key, value) }
    }

    override fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }

    override fun putInt(key: String, value: Int) {
        sp.edit { putInt(key, value) }
    }

    override fun getInt(key: String): Int {
        return sp.getInt(key,0)
    }

    override fun putLong(key: String, value: Long) {
        sp.edit{putLong(key,value)}
    }

    override fun getLong(key: String): Long {
       return sp.getLong(key,0L)
    }

    override fun putFloat(key: String, value: Float) {
        sp.edit{putFloat(key,value)}
    }

    override fun getFloat(key: String): Float {
        return sp.getFloat(key,0F)
    }

    override fun putBoolean(key: String, value: Boolean) {
        sp.edit{putBoolean(key,value)}
    }

    override fun getBoolean(key: String): Boolean {
        return sp.getBoolean(key,false)
    }

}