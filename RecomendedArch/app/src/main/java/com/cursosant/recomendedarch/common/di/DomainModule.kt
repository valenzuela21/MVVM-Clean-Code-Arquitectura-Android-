package com.cursosant.recomendedarch.common.di


import com.cursosant.recomendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.recomendedarch.favouriteModule.model.domain.FavouriteRoomDatabase
import com.cursosant.recomendedarch.homeModule.model.HomeRoomDatabase
import com.cursosant.recomendedarch.homeModule.model.HomeWineService
import com.cursosant.recomendedarch.loginModule.model.domain.LoginAuth
import com.cursosant.recomendedarch.updateModule.model.UpdateRoomDatabase


import org.koin.dsl.module

val domainModule =  module {
    factory { HomeRoomDatabase(get()) }
    factory { HomeWineService(get()) }
    factory { FavouriteRoomDatabase(get()) }
    factory { UpdateRoomDatabase(get()) }
    single { FakeFirebaseAuth() }
    factory { LoginAuth(get()) }
}