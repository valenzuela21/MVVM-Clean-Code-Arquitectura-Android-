package com.cursosant.recomendedarch.common.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.utils.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _snackbarMsg = MutableLiveData<Int?>()
    val snackbarMsg: LiveData<Int?> = _snackbarMsg


    protected fun setInProgress(value: Boolean) {
        _inProgress.postValue(value)
    }

    protected fun setSnackbarMsg(value: Int?) {
        _snackbarMsg.postValue(value)
    }

    open fun onPause(){}

    protected fun clearValues(){
        setSnackbarMsg(null)
    }

    protected fun executeAction(block: suspend() ->  Unit): Job{
        return viewModelScope.launch {
            _inProgress.value = Constants.SHOW
            try {
              block()
            } catch (e: MyException){
                _snackbarMsg.value = e.resMsg
            } finally {
                _inProgress.value = Constants.HIDE
            }
        }
    }

}