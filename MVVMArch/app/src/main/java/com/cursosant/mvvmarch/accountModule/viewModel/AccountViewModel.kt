package com.cursosant.mvvmarch.accountModule.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.mvvmarch.R
import com.cursosant.mvvmarch.accountModule.model.AccountRepository
import com.cursosant.mvvmarch.common.entities.FirebaseUser
import com.cursosant.mvvmarch.common.utils.Constants
import kotlinx.coroutines.launch

class AccountViewModel (private val repository: AccountRepository) : ViewModel(){
    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _snackBarMsg =  MutableLiveData<Int>()
    var snackBarMsg: LiveData<Int> = _snackBarMsg

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _isSignOut =  MutableLiveData<Boolean>()
    val isSignOut: LiveData<Boolean> = _isSignOut

    val signOutClickListener = View.OnClickListener {
        signOut()
    }

    init {
        getCurrentUser()
    }

    private fun getCurrentUser(){
      viewModelScope.launch {
          _inProgress.value = Constants.SHOW
          try{
              _user.value = repository.getCurrentUser()
          }catch (e: Exception){
              _snackBarMsg.value = R.string.account_sign_out_fail
          }finally {
              _inProgress.value = Constants.HIDE
          }
      }
    }

    fun signOut(){
       viewModelScope.launch {
           _inProgress.value = Constants.SHOW
           try{
               _isSignOut.value = repository.signOut()
           }catch (e: Exception){
               _snackBarMsg.value = R.string.account_request_user_fail
           }finally {
               _inProgress.value = Constants.HIDE
           }
       }
    }
}