package com.cursosant.mvvmarch.favoriteModule.model

import com.cursosant.mvvmarch.common.entities.Wine

class FavouriteRepository(private  val db: RoomDatabase) {
    fun getAllWines(): List<Wine>? {
        return try {
            db.getAllWines() // Se espera que este método devuelva la lista de vinos
        } catch (e: Exception) {
            null // Retorna null si ocurre una excepción
        }
    }

    fun addWine(wine:Wine): Long {
        return  db.addWine(wine)
    }


    fun deleteWine(wine: Wine): Int{
        return db.deleteWine(wine)
    }
}