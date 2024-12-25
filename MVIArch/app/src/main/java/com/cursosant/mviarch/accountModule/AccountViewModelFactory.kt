package com.cursosant.mviarch.accountModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursosant.mviarch.accountModule.module.AccountRepository

class AccountViewModelFactory(private val respository: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(respository) as T
        }

        throw  IllegalArgumentException("Case")
    }

}