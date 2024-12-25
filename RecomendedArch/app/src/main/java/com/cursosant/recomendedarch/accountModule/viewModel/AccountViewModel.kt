package com.cursosant.recomendedarch.accountModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.mvvmarch.accountModule.model.AccountRepository
import com.cursosant.recomendedarch.common.entities.FirebaseUser
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository): BaseViewModel() {

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _isSignOut = MutableLiveData<Boolean>()
    val isSignOut: LiveData<Boolean> = _isSignOut

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        executeAction {
            repository.getCurrentUser { result ->
                _user.value = result
            }
        }
    }

    fun signOut() {
        executeAction {
            repository.signOut { result ->
                _isSignOut.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}