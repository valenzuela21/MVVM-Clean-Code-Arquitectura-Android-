package com.cursosant.mvvmarch.accountModule.model

import com.cursosant.mvvmarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.mvvmarch.common.entities.FirebaseUser

class AccountRepository(private val auth: FakeFirebaseAuth) {
    suspend fun signOut() : Boolean {
        return  auth.signOut()
    }

    suspend fun  getCurrentUser() : FirebaseUser? {
        return  auth.getCurrentUser()
    }
}