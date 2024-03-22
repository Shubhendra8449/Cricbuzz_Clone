package com.example.codelab_5_shubhendra.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataStoreTable::class], version = 6)
abstract class DataBase:RoomDatabase() {

    abstract fun dataDao(): Dao


    companion object {
        @Volatile
        private var instance: DataBase? = null

        fun getFun(context: Context): DataBase {
            if (instance == null) {
                synchronized(this) {}
                instance = Room.databaseBuilder(context.applicationContext,
                    DataBase::class.java, "Database"
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }


    }

}