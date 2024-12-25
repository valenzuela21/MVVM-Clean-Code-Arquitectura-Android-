package com.cursosant.winescompose.homeModule.model.domain

import com.cursosant.winescompose.common.dataAccess.room.WineDao
import com.cursosant.winescompose.common.entities.Wine


class HomeRoomDatabase(private val dao: WineDao) {
    fun addWine(wine: Wine) = dao.addWine(wine)
}