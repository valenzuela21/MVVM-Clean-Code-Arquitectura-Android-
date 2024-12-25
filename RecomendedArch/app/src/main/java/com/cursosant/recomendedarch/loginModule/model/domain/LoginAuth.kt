package com.cursosant.recomendedarch.loginModule.model.domain

import com.cursosant.recomendedarch.common.dataAccess.local.FakeFirebaseAuth

class LoginAuth(private val auth: FakeFirebaseAuth){
    suspend fun checkAuth(): Boolean = auth.isValidAuth()

    suspend fun login(username: String?, pin: String?): Boolean{
        if(!username.isNullOrEmpty() && !pin.isNullOrEmpty()){
          val result = auth.login(username, pin)
          if(result) return true else throw Exception()
        }
        throw Exception()
    }
}