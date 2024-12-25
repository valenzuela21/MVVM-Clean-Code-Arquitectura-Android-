package com.cursosant.mvvmarch.homeModule.model
import com.cursosant.mvvmarch.R
import com.cursosant.mvvmarch.common.entities.MyExceptions
import com.cursosant.mvvmarch.common.entities.Wine
import com.cursosant.mvvmarch.common.utils.Constants
import kotlin.random.Random

class HomeRepository(private val db: RoomDatabase, private val service: WineService) {

    suspend fun getAllWines(callback: (List<Wine>) -> Unit){
        return try{
            val serverOk = if(Random.nextBoolean()) true else Random.nextBoolean()
            val wines = if(serverOk) service.getRedWines() else  listOf()
            callback(wines)
        }catch (e: Exception){
            throw MyExceptions(Constants.EC_REQUEST, R.string.common_general_fail)
        }
    }

    fun addWine(wine: Wine, callback: () -> Unit){
            val result = db.addWine(wine)
            return if(result == -1L){
                throw MyExceptions(Constants.EC_SAVE_WINE, R.string.common_general_fail)
            }else{
                callback()
            }
    }
}