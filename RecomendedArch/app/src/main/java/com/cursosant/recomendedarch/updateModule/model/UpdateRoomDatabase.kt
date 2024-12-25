package com.cursosant.recomendedarch.updateModule.model

import com.cursosant.mvvmarch.WineApplication
import com.cursosant.recomendedarch.common.dataAccess.room.WineDao
import com.cursosant.recomendedarch.common.entities.Wine

class UpdateRoomDatabase(private val dao: WineDao) {

    fun getWineById(id: Double) = dao.getWineById(id)

    fun updateWine(wine: Wine?, newRating: String, callback: () -> Unit) {
        wine?.let{
            wine.rating.average = newRating
            val result = dao.updateWine(wine)
            if(result == 0) throw Exception() else callback()
        }
    }
}