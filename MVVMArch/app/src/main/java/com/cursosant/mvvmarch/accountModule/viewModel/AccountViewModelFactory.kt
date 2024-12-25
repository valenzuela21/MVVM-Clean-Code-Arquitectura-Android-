package com.cursosant.mvvmarch.accountModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mvvmarch.accountModule.model.AccountRepository

class AccountViewModelFactory (private val repository: AccountRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(repository) as T
        }
     throw IllegalArgumentException("Clase de ViewModel desconocido.")
    }


}