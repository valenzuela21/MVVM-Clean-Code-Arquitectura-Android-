package com.cursosant.winescompose.homeModule.di

import com.cursosant.winescompose.homeModule.model.HomeRepository
import com.cursosant.winescompose.homeModule.model.domain.HomeRoomDatabase
import com.cursosant.winescompose.homeModule.model.domain.HomeWineService
import com.cursosant.winescompose.homeModule.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeRoomDatabase(get()) }
    factory { HomeWineService(get()) }
    factory { HomeRepository(get(), get()) }
    viewModel { HomeViewModel(get()) }
}