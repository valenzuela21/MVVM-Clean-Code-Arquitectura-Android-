package com.cursosant.recomendedarch.common.di

import com.cursosant.recomendedarch.favouriteModule.model.FavouriteRepository
import com.cursosant.recomendedarch.homeModule.model.HomeRepository
import com.cursosant.recomendedarch.loginModule.model.LoginRepository
import com.cursosant.recomendedarch.updateModule.model.UpdateRepository
import org.koin.dsl.module

val modelModule = module {
    factory { HomeRepository(get(), get()) }
    factory { FavouriteRepository(get()) }
    factory { UpdateRepository(get()) }
    factory { LoginRepository(get()) }
}