package com.cursosant.recomendedarch.accountModule.di

import com.cursosant.mvvmarch.accountModule.model.AccountRepository
import com.cursosant.recomendedarch.accountModule.model.domain.AccountAuth
import com.cursosant.recomendedarch.accountModule.viewModel.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {
    single { AccountAuth(get())}
    factory { AccountRepository(get()) }
    viewModel { AccountViewModel(get()) }
}