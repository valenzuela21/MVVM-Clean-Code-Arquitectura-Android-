package com.cursosant.mviarch.favoriteModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mviarch.favoriteModule.model.FavoriteRepository

class FavouriteViewModelFactory (private val repository: FavoriteRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavouriteViewModel::class.java)){
            return  FavouriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Class de ViewModel desconocido")
    }

}