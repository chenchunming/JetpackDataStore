package com.example.jepcaktestapp.sp

interface ISharePreferences {

    fun putString(key:String,value:String)
    fun getString(key:String):String

    fun putBoolean(key:String,value:Boolean)
    fun getBoolean(key:String):Boolean

    fun putInt(key:String,value:Int)
    fun getInt(key:String):Int

    fun putLong(key:String,value:Long)
    fun getLong(key:String):Long

    fun putFloat(key:String,value:Float)
    fun getFloat(key:String):Float

}