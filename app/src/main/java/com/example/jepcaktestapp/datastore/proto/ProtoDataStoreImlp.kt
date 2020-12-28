package com.example.jepcaktestapp.datastore.proto

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import com.example.jepcaktestapp.sp.SpKeys
import com.example.jepcaktestapp.sp.SpUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ProtoDataStoreImpl(val context: Application){

    // 指定名字
    private val PROTO_NAME = "proto_datastore.pb"

    var dataStore: DataStore<PersonProtos.Person> = context.createDataStore(
        fileName = PROTO_NAME,
        serializer = PersonSerializer
    )

    private val shardPrefsMigration = SharedPreferencesMigration<PersonProtos.Person>(context, SpUtils.SHARE_PREFERENCES_NAME) {
            sharedPreferencesView, person ->
            // 获取 SharedPreferences 的数据
            val count = sharedPreferencesView.getInt(SpKeys.KEY_COUNT,0)
            val name = sharedPreferencesView.getString(SpKeys.KEY_NAME,"")
            val flag = sharedPreferencesView.getBoolean(SpKeys.KEY_FLAG, false)
            val price = sharedPreferencesView.getFloat(SpKeys.KEY_PRICE,0f)
            val time = sharedPreferencesView.getLong(SpKeys.KEY_TIME,0L)
            // 存储到dataStore中
            person.toBuilder().setCount(count).setName(name).setFlag(flag).setPrice(price).setTime(time).build()
        }

    suspend fun putCount(count:Int) {
        dataStore.updateData { it.toBuilder().setCount(count).build() }
    }
    suspend fun getCount():Int =  dataStore.data.map { it.count }.first()

    suspend fun putName(name:String) {
        dataStore.updateData { it.toBuilder().setName(name).build()}
    }
    suspend fun getName():String = dataStore.data.map { it.name }.first()

    suspend fun putFlag(flag:Boolean) {
        dataStore.updateData { it.toBuilder().setFlag(flag).build() }
    }
    suspend fun getFlag():Boolean = dataStore.data.map { it.flag }.first()

    suspend fun putPrice(price:Float) {
        dataStore.updateData { it.toBuilder().setPrice(price).build() }
    }
    suspend fun getPrice():Float = dataStore.data.map { it.price }.first()

    suspend fun putTime(time:Long) {
        dataStore.updateData { it.toBuilder().setTime(time).build() }
    }
    suspend fun getTime():Long =  dataStore.data.map { it.time }.first()

    suspend fun putMoney(money:Double) {
        dataStore.updateData { it.toBuilder().setMoney(money).build() }
    }
    suspend fun getMoney():Double = dataStore.data.map { it.money }.first()

    fun getAddress():Flow<PersonProtos.Address> =  dataStore.data.map { it.address }

    fun getPhone():Flow<List<String>> = dataStore.data.map { it.phoneList }

    fun getWeekday():Flow<PersonProtos.Person.Weekday> = dataStore.data.map { it.weekday }

    fun spToDataStore(){
        dataStore = context.createDataStore(
            fileName = PROTO_NAME,
            serializer = PersonSerializer,
            migrations = listOf(shardPrefsMigration)
        )
    }


}