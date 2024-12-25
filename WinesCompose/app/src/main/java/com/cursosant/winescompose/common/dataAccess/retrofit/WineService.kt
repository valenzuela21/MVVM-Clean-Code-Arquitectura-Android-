package com.cursosant.winescompose.common.dataAccess.retrofit

import com.cursosant.winescompose.common.entities.Wine
import com.cursosant.winescompose.common.utils.Constants
import retrofit2.http.GET


interface WineService {
    // https://sampleapis.com/api-list/wines
    @GET(Constants.PATH_WINES)
    suspend fun getRedWines() : List<Wine>
}