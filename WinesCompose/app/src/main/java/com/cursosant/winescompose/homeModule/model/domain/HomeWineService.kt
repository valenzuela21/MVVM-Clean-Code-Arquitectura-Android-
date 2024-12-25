package com.cursosant.winescompose.homeModule.model.domain

import com.cursosant.winescompose.common.dataAccess.retrofit.WineService

class HomeWineService(private val service: WineService) {
    suspend fun getRedWines() = service.getRedWines()
}