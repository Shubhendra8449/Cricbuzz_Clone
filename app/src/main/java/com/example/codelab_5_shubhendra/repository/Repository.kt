package com.example.codelab_5_shubhendra.repository

import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val database: DataBase) {

    val userData : LiveData<List<DataStoreTable>>
        get() = database.dataDao().getAllUser()

    fun insertData(datastore: DataStoreTable){
        CoroutineScope(Dispatchers.IO).launch {
            database.dataDao().insert(datastore)
        }
    }

    fun updateData(datastore: DataStoreTable){
        CoroutineScope(Dispatchers.IO).launch {
            database.dataDao().update(datastore)
        }
    }
}