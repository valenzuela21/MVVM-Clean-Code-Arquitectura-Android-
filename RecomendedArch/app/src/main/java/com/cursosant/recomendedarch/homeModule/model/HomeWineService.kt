package com.cursosant.recomendedarch.homeModule.model

import com.cursosant.recomendedarch.common.dataAccess.retrofit.WineService

class HomeWineService(private val service: WineService){
    suspend fun getRedWines() = service.getRedWines()
}