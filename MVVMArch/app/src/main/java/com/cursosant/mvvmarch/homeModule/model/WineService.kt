package com.cursosant.mvvmarch.homeModule.model

import com.cursosant.mvvmarch.common.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.cursosant.mvvmarch.common.dataAccess.retrofit.WineService

class WineService {

    suspend fun getRedWines() = getService().getRedWines()

    private fun getService(): WineService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WineService::class.java)
    }
}