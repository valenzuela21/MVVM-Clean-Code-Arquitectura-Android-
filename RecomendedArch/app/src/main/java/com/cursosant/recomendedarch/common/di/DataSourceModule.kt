package com.cursosant.recomendedarch.common.di

import android.app.Application
import androidx.room.Room
import com.cursosant.recomendedarch.common.dataAccess.retrofit.WineService

import com.cursosant.recomendedarch.common.dataAccess.room.WineDao
import com.cursosant.recomendedarch.common.dataAccess.room.WineDatabase
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.homeModule.model.HomeWineService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Room
fun providerDatabase(application: Application): WineDatabase {
    return Room.databaseBuilder(application,
        WineDatabase::class.java,
        "WineDatabase")
        .build()
}

fun provideDao(database: WineDatabase): WineDao = database.wineDao()

//Retrofit

fun providerGsonConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun providerRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun providerWineService(retrofit: Retrofit): WineService = retrofit.create(WineService::class.java)

val dataSourceModule = module {
    // Room
    single { providerDatabase(get()) }
    single { provideDao(get()) }

    // Retrofit
    single { providerGsonConvertFactory() }
    single { providerRetrofit(get()) }
    single { providerWineService(get()) }
}