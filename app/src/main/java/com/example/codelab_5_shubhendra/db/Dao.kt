package com.example.codelab_5_shubhendra.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert
    suspend fun insert(dataStoreTable: DataStoreTable)

    @Update
    suspend fun update(dataStoreTable: DataStoreTable)



    @Query("select * from CricketPlayers")
    fun getAllUser() : LiveData<List<DataStoreTable>>
}