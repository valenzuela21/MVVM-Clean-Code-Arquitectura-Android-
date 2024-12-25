package com.cursosant.mvvmarch.homeModule.model

import com.cursosant.mvvmarch.WineApplication
import com.cursosant.mvvmarch.common.dataAccess.room.WineDao
import com.cursosant.mvvmarch.common.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy {WineApplication.database.wineDao()}
    fun addWine(wine: Wine) = dao.addWine(wine)
}