package com.cursosant.mviarch.commonModule.dataAccess.retrofit

import com.cursosant.mviarch.commonModule.utils.Constants
import com.cursosant.mviarch.commonModule.entries.Wine
import retrofit2.http.GET

interface WineService {
    // https://sampleapis.com/api-list/wines
    @GET(Constants.PATH_WINES)
    suspend fun getRedWines() : List<Wine>
}