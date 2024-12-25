package com.cursosant.recomendedarch.common.di

import com.cursosant.mvvmarch.loginModule.viewModel.LoginViewModel
import com.cursosant.recomendedarch.common.viewModel.ShareViewModel
import com.cursosant.recomendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.cursosant.recomendedarch.homeModule.viewModel.HomeViewModel
import com.cursosant.recomendedarch.updateModule.viewModel.UpdateViewModel
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { UpdateViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ShareViewModel() }
}