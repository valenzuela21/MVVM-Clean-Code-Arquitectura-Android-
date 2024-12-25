package com.cursosant.mvvmarch.favoriteModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mvvmarch.favoriteModule.model.FavouriteRepository

class FavoriteViewModelFactory(private val repository: FavouriteRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase de Viewmodel desconocido.")
    }
}