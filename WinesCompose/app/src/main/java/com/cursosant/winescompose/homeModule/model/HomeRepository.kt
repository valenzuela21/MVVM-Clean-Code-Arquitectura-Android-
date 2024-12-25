package com.cursosant.winescompose.homeModule.model

import com.cursosant.winescompose.R
import com.cursosant.winescompose.common.entities.MyException
import com.cursosant.winescompose.common.entities.Wine
import com.cursosant.winescompose.common.model.BaseRepository
import com.cursosant.winescompose.common.utils.Constants
import com.cursosant.winescompose.homeModule.model.domain.HomeRoomDatabase
import com.cursosant.winescompose.homeModule.model.domain.HomeWineService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class HomeRepository(private val db: HomeRoomDatabase, private val service: HomeWineService) : BaseRepository() {
    suspend fun getAllWines(callback: (List<Wine>) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.common_general_fail)) {
            val serverOk = true// if (Random.nextBoolean()) true else Random.nextBoolean()
            val wines = if (serverOk) service.getRedWines() else listOf()
            callback(wines)
        }
    }

    suspend fun addWine(wine: Wine, callback: () -> Unit) = withContext(Dispatchers.IO) {
        executeAction {
            val result = db.addWine(wine)
            if (result == -1L) {
                throw MyException(Constants.EC_SAVE_WINE, R.string.room_save_fail)
            } else {
                callback()
            }
        }
    }
}