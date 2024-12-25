package com.cursosant.mvvmarch.loginModule.model

import com.cursosant.mvvmarch.R
import com.cursosant.mvvmarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.mvvmarch.common.entities.MyExceptions
import com.cursosant.mvvmarch.common.utils.Constants

class LoginRepository(private val auth: FakeFirebaseAuth) {

    suspend fun checkAuth(): Boolean {
        return auth.isValidAuth()
    }

    suspend fun login(username: String, pin: String): Boolean {
        val result = auth.login(username, pin)
        if(!result) throw MyExceptions(Constants.EC_LOGIN, R.string.account_request_user_fail)
        return true
    }

}