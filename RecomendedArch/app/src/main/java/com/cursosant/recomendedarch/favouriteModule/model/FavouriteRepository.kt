package com.cursosant.recomendedarch.favouriteModule.model

import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.model.BaseRepository
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.favouriteModule.model.domain.FavouriteRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepository(private val db: FavouriteRoomDatabase): BaseRepository() {
    suspend  fun getAllWines(callback: (List<Wine>) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.home_no_wines)) {
            callback(db.getAllWines())
        }
    }

    suspend fun  updateFavourite(wine: Wine, callback: (Int) -> Unit) = withContext(Dispatchers.IO){
        executeAction(MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)){
            db.updateFavorite(wine){ result ->
                callback(result)
            }
        }
    }


}