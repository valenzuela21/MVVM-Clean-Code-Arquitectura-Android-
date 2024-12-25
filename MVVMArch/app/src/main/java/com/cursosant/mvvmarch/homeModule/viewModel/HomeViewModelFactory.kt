package com.cursosant.mvvmarch.homeModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mvvmarch.homeModule.model.HomeRepository

class HomeViewModelFactory(private val repository: HomeRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
           return HomeViewModel(repository) as T
        }
        throw  IllegalArgumentException("Clase de viewModel desconocido")
    }
}