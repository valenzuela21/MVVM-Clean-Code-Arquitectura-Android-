package com.cursosant.recomendedarch.updateModule.model

import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.model.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UpdateRepository(private val db: UpdateRoomDatabase): BaseRepository() {
    suspend fun requestWine(id: Double, callback: (Wine) -> Unit) = withContext(Dispatchers.IO) {
        executeAction( MyException(Constants.EC_GET_WINE, R.string.home_no_data)){
            callback(db.getWineById(id))
        }
    }

    suspend fun updateWine(wine: Wine?, newRating: String, callback: () -> Unit) = withContext(Dispatchers.IO) {

        executeAction(MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)){
            db.updateWine(wine, newRating){
                callback()
            }
        }
    }
}