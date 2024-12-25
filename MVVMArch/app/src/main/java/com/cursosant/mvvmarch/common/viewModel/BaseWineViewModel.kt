package com.cursosant.mvvmarch.common.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.mvvmarch.common.entities.Wine

open class BaseWineViewModel: ViewModel() {
    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _snackBarMsg = MutableLiveData<Int>()
    val snackBarMsg: LiveData<Int> = _snackBarMsg

    private val _wines = MutableLiveData<List<Wine>>()
    val wines: LiveData<List<Wine>> = _wines


    protected fun setInProgress(value: Boolean){
        _inProgress.postValue(value)
    }

    protected fun setSnackBarMsg(value: Int){
        _snackBarMsg.postValue(value)
    }

    protected fun setWines(value: List<Wine>){
        _wines.postValue(value)
    }

    open fun getAllWines() {}

    open fun addWine(wine: Wine){}

}