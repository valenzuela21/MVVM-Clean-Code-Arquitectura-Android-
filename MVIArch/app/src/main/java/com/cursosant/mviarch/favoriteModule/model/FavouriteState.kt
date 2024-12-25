package com.cursosant.mviarch.favoriteModule.model
import com.cursosant.mviarch.commonModule.entries.Wine

sealed class FavouriteState {
    data object Init: FavouriteState()
    data object ShowProgress: FavouriteState()
    data object HideProgress: FavouriteState()
    data class RequestWinesSuccess(val list: List<Wine>): FavouriteState()
    data class AddWineSuccess(val msgRes:  Int): FavouriteState()
    data class DeleteWineSuccess(val msgRes:  Int): FavouriteState()
    data class Fail(val code: Int, val msgRes: Int): FavouriteState()
}