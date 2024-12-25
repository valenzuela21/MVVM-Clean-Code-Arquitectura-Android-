package com.cursosant.mviarch.homeModule.model

import com.cursosant.mviarch.WineApplication
import com.cursosant.mviarch.commonModule.dataAccess.room.WineDao
import com.cursosant.mviarch.commonModule.entries.Wine

class RoomDatabase {
    private val dao: WineDao by lazy {WineApplication.database.wineDao()}
    fun addWine(wine: Wine) = dao.addWine(wine)
}