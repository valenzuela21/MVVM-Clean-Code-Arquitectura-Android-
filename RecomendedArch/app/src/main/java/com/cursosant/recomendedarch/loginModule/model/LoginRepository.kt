package com.cursosant.recomendedarch.loginModule.model

import com.cursosant.recomendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.model.BaseRepository
import com.cursosant.recomendedarch.loginModule.model.domain.LoginAuth

class LoginRepository(private val auth: LoginAuth): BaseRepository() {
    suspend fun checkAuth(callback: (Boolean) -> Unit){
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)) {
            callback(auth.checkAuth())
        }
    }

    suspend fun login(username: String?, pin: String?, callback: (Boolean) -> Unit){
        executeAction(MyException(Constants.EC_LOGIN, R.string.login_login_fail)) {
            callback(auth.login(username, pin))
        }
    }
}