package com.cursosant.recomendedarch.accountModule.model.domain

import com.cursosant.recomendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.cursosant.recomendedarch.common.entities.FirebaseUser

class AccountAuth(private val auth: FakeFirebaseAuth) {
    suspend fun  signOut(): Boolean = auth.signOut()
    suspend fun  getCurrentUser(): FirebaseUser?  = auth.getCurrentUser()
}