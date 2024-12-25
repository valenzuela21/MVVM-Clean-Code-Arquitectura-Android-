package com.cursosant.recomendedarch.common.dataAccess.retrofit

import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.utils.Constants
import retrofit2.http.GET


interface WineService {

    @GET(Constants.PATH_WINES)
    suspend fun getRedWines() : List<Wine>
}