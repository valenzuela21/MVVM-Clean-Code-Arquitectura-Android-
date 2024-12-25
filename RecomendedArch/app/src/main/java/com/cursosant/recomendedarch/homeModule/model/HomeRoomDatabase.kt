package com.cursosant.recomendedarch.homeModule.model

import com.cursosant.recomendedarch.common.dataAccess.room.WineDao
import com.cursosant.recomendedarch.common.entities.Wine


class HomeRoomDatabase(private val dao: WineDao) {
    fun addWine(wine: Wine) = dao.addWine(wine)
}