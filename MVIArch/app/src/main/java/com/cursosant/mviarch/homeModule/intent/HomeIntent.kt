package com.cursosant.mviarch.homeModule.intent

import com.cursosant.mviarch.commonModule.entries.Wine

sealed class HomeIntent {
    data object RequestWines: HomeIntent()
    data class AddWire(val wine: Wine) : HomeIntent()
}