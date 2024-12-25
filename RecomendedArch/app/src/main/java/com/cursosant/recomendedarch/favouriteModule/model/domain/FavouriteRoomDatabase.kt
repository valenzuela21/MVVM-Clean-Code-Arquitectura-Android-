package com.cursosant.recomendedarch.favouriteModule.model.domain

import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.dataAccess.room.WineDao
import com.cursosant.recomendedarch.common.entities.Wine

class FavouriteRoomDatabase(private val dao: WineDao) {
    fun getAllWines() = dao.getAllWines()

    fun updateFavorite(wine: Wine, callback: (Int) -> Unit){
        wine.isFavorite = !wine.isFavorite
        val result = if(wine.isFavorite){
            addWine(wine)
        }else{
            deleteWine(wine)
        }
        callback(result)
    }

    private fun addWine(wine: Wine): Int {
        val result = dao.addWine(wine)
        return if(result != -1L) R.string.room_save_success else R.string.room_save_fail
    }

    private fun deleteWine(wine: Wine): Int{
        val result =  dao.deleteWine(wine)
        return if(result == 0) R.string.room_save_fail else R.string.room_save_success
    }
}