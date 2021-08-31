package com.example.justorder.roomdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CartItem::class), version = 1)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): CartDao


    companion object {
        var INSTANCE :AppDatabase?=null
        fun getDbInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                Log.e("ASHISHDB", "INSTANCE NULL " )
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "CartDatabase"
                ).allowMainThreadQueries().build()
            }
            Log.e("ASHISHDB", "INSTANCE " )
            return INSTANCE!!
        }
    }
}