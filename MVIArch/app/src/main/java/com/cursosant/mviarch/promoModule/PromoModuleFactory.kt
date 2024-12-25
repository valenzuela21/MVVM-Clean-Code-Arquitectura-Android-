package com.cursosant.mviarch.promoModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mviarch.promoModule.model.PromoRepository

class PromoModuleFactory(private val repository: PromoRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PromoViewModel::class.java)){
            return  PromoViewModel(repository) as T
        }
        throw  IllegalArgumentException("Clase de viewmodel desconocido")
    }

}