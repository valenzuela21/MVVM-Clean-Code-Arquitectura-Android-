package com.cursosant.mviarch.commonModule.dataAccess.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cursosant.mviarch.commonModule.entries.Wine


@Dao
interface WineDao {
    @Query("SELECT * FROM WineEntity")
    fun getAllWines(): MutableList<Wine>

    @Query("SELECT * FROM WineEntity WHERE id == :id")
    fun getWineById(id: Double): Wine

    @Insert
    fun addWine(wine: Wine): Long

    @Update
    fun updateWine(wine: Wine): Int

    @Delete
    fun deleteWine(wine: Wine): Int
}