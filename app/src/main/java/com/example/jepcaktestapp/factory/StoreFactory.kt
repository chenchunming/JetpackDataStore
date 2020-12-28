package com.example.jepcaktestapp.factory

import android.app.Application
import com.example.jepcaktestapp.datastore.IDataStore
import com.example.jepcaktestapp.datastore.preferences.PreferencesDataStore
import com.example.jepcaktestapp.datastore.proto.ProtoDataStoreImpl
import com.example.jepcaktestapp.sp.ISharePreferences
import com.example.jepcaktestapp.sp.SpUtils

object StoreFactory {

    @JvmStatic
    fun provideSharePreferences(context: Application):ISharePreferences{
        return SpUtils(context)
    }

    @JvmStatic
    fun providePreferencesDataStore(context: Application):IDataStore{
        return PreferencesDataStore(context)
    }

    @JvmStatic
    fun provideProtoDataStore(context: Application):ProtoDataStoreImpl{
        return ProtoDataStoreImpl(context)
    }

}