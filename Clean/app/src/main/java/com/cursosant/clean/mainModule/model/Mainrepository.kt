package com.cursosant.clean.mainModule.model

import com.cursosant.clean.common.SportEvent

interface  Mainrepository {
    suspend fun getEvents()
    suspend fun saveResult(result: SportEvent.ResultSuccess)
    suspend fun registerAd()
}