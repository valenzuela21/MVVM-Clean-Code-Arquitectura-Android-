package com.cursosant.mvvmarch.favoriteModule.model

import com.cursosant.mvvmarch.WineApplication
import com.cursosant.mvvmarch.common.dataAccess.room.WineDao
import com.cursosant.mvvmarch.common.entities.Wine

class RoomDatabase {

    private val dao: WineDao by lazy { WineApplication.database.wineDao() }
    fun getAllWines() = dao.getAllWines()
    fun addWine(wine: Wine) = dao.addWine(wine)
    fun deleteWine(wine: Wine) = dao.deleteWine(wine)
}