package com.cursosant.mviarch.favoriteModule.intent

import com.cursosant.mviarch.commonModule.entries.Wine

sealed class FavouriteIntent {
    data object RequestWines: FavouriteIntent()
    data class AddWine(val wine: Wine): FavouriteIntent()
    data class DeleteWine(val wine: Wine): FavouriteIntent()
}