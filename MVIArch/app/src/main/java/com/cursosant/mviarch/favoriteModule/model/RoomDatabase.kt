package com.cursosant.mviarch.favoriteModule.model

import com.cursosant.mviarch.WineApplication
import com.cursosant.mviarch.commonModule.dataAccess.room.WineDao
import com.cursosant.mviarch.commonModule.dataAccess.room.WineDatabase
import com.cursosant.mviarch.commonModule.entries.Wine

class RoomDatabase {
    private val dao: WineDao by lazy {WineApplication.database.wineDao()}
    fun getAllWines() = dao.getAllWines()
    fun addWine(wine: Wine) = dao.addWine(wine)
    fun deletteWine(wine: Wine) = dao.deleteWine(wine)
}