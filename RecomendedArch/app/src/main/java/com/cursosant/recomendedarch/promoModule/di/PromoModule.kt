package com.cursosant.recomendedarch.promoModule.di

import com.cursosant.recomendedarch.promoModule.model.PromoRepository
import com.cursosant.recomendedarch.promoModule.model.domain.PromoDatabase
import com.cursosant.recomendedarch.promoModule.view.adapaters.PromoDiff
import com.cursosant.recomendedarch.promoModule.view.adapaters.PromoListAdapter
import com.cursosant.recomendedarch.promoModule.viewModel.PromoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val promoModule = module {
    factory {PromoDatabase()}
    factory { PromoRepository(get()) }
    factory { PromoDiff() }
    factory { PromoListAdapter(get()) }
    viewModel { PromoViewModel(get()) }

}