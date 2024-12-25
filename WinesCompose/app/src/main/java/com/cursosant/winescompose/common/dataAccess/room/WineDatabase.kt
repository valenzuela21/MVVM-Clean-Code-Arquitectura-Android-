package com.cursosant.winescompose.common.dataAccess.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cursosant.winescompose.common.entities.Wine


@Database(entities = [Wine::class], version = 1)
@TypeConverters(WineConverters::class)
abstract class WineDatabase : RoomDatabase(){
    abstract fun wineDao(): WineDao
}