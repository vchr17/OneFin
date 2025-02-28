package com.example.onefin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onefin.domain.model.Money

@Database (entities = [Money::class], version = 1 )
abstract class MainDb() : RoomDatabase() {
    abstract fun getDao() : MainDao
    companion object{
     fun getDb(context: Context) : MainDb{
         return Room.databaseBuilder(
             context.applicationContext,
             MainDb::class.java,
             "CurrencyTable.db"
         ).build()
     }
    }
}