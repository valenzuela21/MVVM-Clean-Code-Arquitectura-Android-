package com.cursosant.recomendedarch.common.di

import com.cursosant.recomendedarch.common.utils.OnClickListener
import com.cursosant.recomendedarch.favouriteModule.view.WineFavListAdapter
import com.cursosant.recomendedarch.homeModule.view.WineDiff
import com.cursosant.recomendedarch.homeModule.view.WineListAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory {WineDiff()}
    factory<WineListAdapter>{ (listener: OnClickListener) -> WineListAdapter(listener, get()) }
    factory<WineFavListAdapter>{ (listener: OnClickListener) -> WineFavListAdapter(listener, get()) }
}