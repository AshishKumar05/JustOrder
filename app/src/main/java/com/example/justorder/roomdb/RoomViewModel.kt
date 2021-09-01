package com.example.justorder.roomdb

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RoomViewModel : AndroidViewModel {

    private lateinit var list: MutableLiveData<List<CartItem>>
    private lateinit var appDatabase : AppDatabase

    val TAG="ROOMMODEL"
    constructor(application: Application):super(application){
        list = MutableLiveData()
        appDatabase = AppDatabase.getDbInstance(application.applicationContext)
    }

    fun getAll(): List<CartItem>{
        var list= appDatabase.userDao().getallItem()
        if(list==null)
            Log.e("MODEL", "getAll: NULLLLLL" )
        else
            Log.e("MODEL", "getAll: DATA"+list.size )

        return list
    }
    fun insertItem(cartItem: CartItem){
        if(cartItem!=null){
            Log.e(TAG, "insertItem: "+cartItem.toString() )
        } else {
            Log.e(TAG, "insertItem: NULLLLLLLLLL" )
        }
       var itemFromDb:CartItem = appDatabase.userDao().getItem(cartItem.id)
        Log.e(TAG, "DATA already there"+itemFromDb )
        if (itemFromDb==null){
            appDatabase.userDao().insert(cartItem)
        } else{
            Log.e(TAG, "Alredayinserted"+itemFromDb.toString())

            if(cartItem.orderquantity!=0){
                Log.e(TAG, "UPDATE" )
                update(cartItem)
            } else {
                delete(cartItem)
            }
        }
    }

    fun delete(cartItem: CartItem){
        appDatabase.userDao().delete(cartItem.id)
    }

    fun update(cartItem: CartItem){
        appDatabase.userDao().update(cartItem.id,cartItem.orderquantity)
    }
}