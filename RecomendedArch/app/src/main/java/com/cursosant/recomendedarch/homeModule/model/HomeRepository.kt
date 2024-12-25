package com.cursosant.recomendedarch.homeModule.model

import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.model.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random


class HomeRepository(private val db: HomeRoomDatabase, private val service: HomeWineService): BaseRepository() {
    suspend fun getAllWines(callback: (List<Wine>) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.common_general_fail)){
            val serverOk = if (Random.nextBoolean()) true else Random.nextBoolean()
            val wines = if (serverOk) service.getRedWines() else listOf()
            callback(wines)
        }
    }

    suspend fun addWine(wine: Wine, callback: () -> Unit) = withContext(Dispatchers.IO) {
        executeAction{
            val result = db.addWine(wine)
            if (result == -1L) {
                throw MyException(Constants.EC_SAVE_WINE, R.string.room_save_fail)
            } else {
                callback()
            }
        }

    }
}