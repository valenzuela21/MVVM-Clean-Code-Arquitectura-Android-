package com.cursosant.mviarch.favoriteModule.model

import com.cursosant.mviarch.R
import com.cursosant.mviarch.commonModule.entries.Wine
import com.cursosant.mviarch.commonModule.utils.Constants

class FavoriteRepository(private val db: RoomDatabase) {
    fun getAllWines(): FavouriteState{
        val result = db.getAllWines()
        return FavouriteState.RequestWinesSuccess(result)
    }

    fun addWine(wine: Wine): FavouriteState{
        val result = db.addWine(wine)
        return if(result == -1L){
            FavouriteState.Fail(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
          FavouriteState.AddWineSuccess(R.string.room_save_success)
        }
    }

    fun deleteWine(wine: Wine): FavouriteState{
        val result = db.deletteWine(wine)
        return if(result == 0){
            FavouriteState.Fail(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
            FavouriteState.DeleteWineSuccess(R.string.room_save_success)
        }
    }
}