package com.cursosant.mviarch.loginModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mviarch.loginModule.model.LoginRepository

class LoginViewModelFactory(private val repository: LoginRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewmodel::class.java)){
            return LoginViewmodel(repository) as T
        }
        throw IllegalArgumentException("Clase de ViewModel desconocido")
    }
}