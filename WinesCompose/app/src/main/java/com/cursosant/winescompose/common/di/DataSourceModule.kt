package com.cursosant.winescompose.common.di

import android.app.Application
import androidx.room.Room
import com.cursosant.winescompose.common.dataAccess.retrofit.WineService
import com.cursosant.winescompose.common.dataAccess.room.WineDao
import com.cursosant.winescompose.common.dataAccess.room.WineDatabase
import com.cursosant.winescompose.common.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Room
fun provideDatabase(application: Application): WineDatabase {
    return Room.databaseBuilder(application,
        WineDatabase::class.java,
        "WineDatabase")
        .build()
}

fun provideDao(database: WineDatabase): WineDao = database.wineDao()

// Retrofit
fun provideGsonConverterFactory():GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideWineService(retrofit: Retrofit): WineService = retrofit.create(WineService::class.java)

val dataSourceModule = module {
    // Room
    single { provideDatabase(get()) }
    single { provideDao(get()) }
    // Retrofit
    single { provideGsonConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideWineService(get()) }
}