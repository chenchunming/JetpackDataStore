package com.example.jepcaktestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.jepcaktestapp.datastore.preferences.PreferencesKeys
import com.example.jepcaktestapp.factory.StoreFactory
import com.example.jepcaktestapp.sp.SpKeys
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() ,View.OnClickListener ,CoroutineScope by MainScope(){

    private var mSpText = ""
    private var mPrefsDataStoreText = ""
    private var mProtoDataStoreText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_sp.setOnClickListener(this)
        tv_preferences_data_store.setOnClickListener(this)
        tv_proto_data_store.setOnClickListener(this)
        tv_sp_to_prefs_datastore.setOnClickListener(this)
        tv_sp_to_proto_datastore.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            tv_sp->{
                StoreFactory.provideSharePreferences(application).putInt(SpKeys.KEY_COUNT,1)
                StoreFactory.provideSharePreferences(application).putFloat(SpKeys.KEY_PRICE,20f)
                StoreFactory.provideSharePreferences(application).putBoolean(SpKeys.KEY_FLAG,true)
                StoreFactory.provideSharePreferences(application).putString(SpKeys.KEY_NAME,"ccm")
                StoreFactory.provideSharePreferences(application).putLong(SpKeys.KEY_TIME,System.currentTimeMillis())

                mSpText = "count="+StoreFactory.provideSharePreferences(application).getInt(SpKeys.KEY_COUNT)+
                        "price="+StoreFactory.provideSharePreferences(application).getFloat(SpKeys.KEY_PRICE)+
                        "flag="+StoreFactory.provideSharePreferences(application).getBoolean(SpKeys.KEY_FLAG)+
                        "name="+StoreFactory.provideSharePreferences(application).getString(SpKeys.KEY_NAME)+
                        "time="+StoreFactory.provideSharePreferences(application).getLong(SpKeys.KEY_TIME)

                setContentText()
            }
            tv_preferences_data_store->{
                launch (Dispatchers.Main){
                    StoreFactory.providePreferencesDataStore(application).putInt(PreferencesKeys.KEY_COUNT,1)
                    StoreFactory.providePreferencesDataStore(application).putFloat(PreferencesKeys.KEY_PRICE,20F)
                    StoreFactory.providePreferencesDataStore(application).putBoolean(PreferencesKeys.KEY_FLAG,true)
                    StoreFactory.providePreferencesDataStore(application).putString(PreferencesKeys.KEY_NAME,"ccm")
                    StoreFactory.providePreferencesDataStore(application).putLong(PreferencesKeys.KEY_TIME,System.currentTimeMillis())

                    mPrefsDataStoreText =
                            "count="+StoreFactory.providePreferencesDataStore(application).getInt(PreferencesKeys.KEY_COUNT)+
                            "price="+StoreFactory.providePreferencesDataStore(application).getFloat(PreferencesKeys.KEY_PRICE)+
                            "flag="+StoreFactory.providePreferencesDataStore(application).getBoolean(PreferencesKeys.KEY_FLAG)+
                            "name="+StoreFactory.providePreferencesDataStore(application).getString(PreferencesKeys.KEY_NAME)+
                            "time="+StoreFactory.providePreferencesDataStore(application).getLong(PreferencesKeys.KEY_TIME)

                    setContentText()
                }
            }
            tv_proto_data_store->{
                launch (Dispatchers.Main){
                    StoreFactory.provideProtoDataStore(application).putCount(1)
                    StoreFactory.provideProtoDataStore(application).putPrice(20f)
                    StoreFactory.provideProtoDataStore(application).putFlag(true)
                    StoreFactory.provideProtoDataStore(application).putName("ccm")
                    StoreFactory.provideProtoDataStore(application).putTime(System.currentTimeMillis())

                    mProtoDataStoreText = "count="+StoreFactory.provideProtoDataStore(application).getCount()+
                            "price="+StoreFactory.provideProtoDataStore(application).getPrice()+
                            "flag="+StoreFactory.provideProtoDataStore(application).getFlag()+
                            "name="+StoreFactory.provideProtoDataStore(application).getName()+
                            "time="+StoreFactory.provideProtoDataStore(application).getTime()

                    setContentText()
                }
            }
            tv_sp_to_prefs_datastore->{
                launch (Dispatchers.Main){
                    StoreFactory.providePreferencesDataStore(application).spToDataStore()
                }
            }
            tv_sp_to_proto_datastore->{
                launch (Dispatchers.Main){
                    StoreFactory.provideProtoDataStore(application).spToDataStore()
                }
            }
        }
    }

    fun setContentText(){
        tv_content.text = "sp==$mSpText\nprefs_datastore====$mPrefsDataStoreText\nproto_datastore====$mProtoDataStoreText"
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

}
