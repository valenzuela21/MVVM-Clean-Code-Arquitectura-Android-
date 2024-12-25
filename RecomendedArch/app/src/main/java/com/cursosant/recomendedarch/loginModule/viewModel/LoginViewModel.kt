package com.cursosant.mvvmarch.loginModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.common.viewModel.BaseViewModel
import com.cursosant.recomendedarch.loginModule.model.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): BaseViewModel(){

    val username = MutableLiveData("cursosant")
    val pin = MutableLiveData("1234")

    private val _isAuthValid = MutableLiveData<Boolean>()
    val isAuthValid: LiveData<Boolean> = _isAuthValid

    init {
        checkAuth()
    }

    private fun checkAuth() {
        executeAction {
            repository.checkAuth { result -> _isAuthValid.value = result }
        }
    }

    fun login() {
        executeAction {
            repository.login(username.value, pin.value){ result ->
                _isAuthValid.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}