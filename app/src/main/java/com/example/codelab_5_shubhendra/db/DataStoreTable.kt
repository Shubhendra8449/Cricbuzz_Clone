package com.example.codelab_5_shubhendra.db

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "CricketPlayers")

@Parcelize
data class DataStoreTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "img") val img:ByteArray?=null,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "team") val team:String,
    @ColumnInfo(name = "country") val country:String,
    @ColumnInfo(name = "matches") val matches:String,
    @ColumnInfo(name = "runs") val runs:String,
    @ColumnInfo(name = "wickets") val wickets:String,
    @ColumnInfo(name = "dob") val dob:String,
    @ColumnInfo(name = "batsman") val batsman:Boolean,
    @ColumnInfo(name = "bowler") val Bowler:Boolean,
    @ColumnInfo(name = "favourite") val favourite:Boolean
):Parcelable


