package com.cursosant.mvvmarch.loginModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.mvvmarch.common.entities.MyExceptions
import com.cursosant.mvvmarch.common.utils.Constants
import com.cursosant.mvvmarch.loginModule.model.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel() {

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _snackBarMsg = MutableLiveData<Int>()
    val snackBarMsg: LiveData<Int> = _snackBarMsg

    private val _isAuthValid = MutableLiveData<Boolean>()
    val isAuthValid: LiveData<Boolean> =  _isAuthValid

    init{
        checkAuth()
    }

    private fun checkAuth(){
        viewModelScope.launch {
            _inProgress.value = Constants.SHOW
            try{
                _isAuthValid.value = repository.checkAuth()
            } finally {
                _isAuthValid.value = Constants.HIDE
            }
        }
    }

    fun login(username: String, pin: String){
        viewModelScope.launch {
            _inProgress.value =  Constants.SHOW
            try{
                _isAuthValid.value =  repository.login(username, pin)
            } catch (e: MyExceptions){
                _snackBarMsg.value = e.resMsg
            } finally {
                _inProgress.value = Constants.HIDE
            }
        }
    }
}