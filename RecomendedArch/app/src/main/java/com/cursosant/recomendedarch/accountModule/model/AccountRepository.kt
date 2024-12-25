package com.cursosant.mvvmarch.accountModule.model

import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.recomendedarch.common.entities.FirebaseUser
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.model.BaseRepository
import com.cursosant.recomendedarch.common.utils.Constants

class AccountRepository(private val auth: FakeFirebaseAuth): BaseRepository() {
    suspend fun signOut(callback: (Boolean) -> Unit) {
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)) {
           callback(auth.signOut())
        }
    }

    suspend fun getCurrentUser(callback: (FirebaseUser?) -> Unit) {
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)){
            callback(auth.getCurrentUser())
        }
    }
}