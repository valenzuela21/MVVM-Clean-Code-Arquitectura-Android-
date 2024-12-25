package com.cursosant.mviarch.accountModule.module

import com.cursosant.mviarch.R
import com.cursosant.mviarch.commonModule.dataAccess.local.FakeFirebaseAuth
import com.cursosant.mviarch.commonModule.utils.Constants

class AccountRepository(private  val auth: FakeFirebaseAuth) {
    suspend fun singOut(): AccountState {
        val result = auth.signOut()
        return  if(result){
            AccountState.SignOutSuccess
        } else {
            AccountState.Fail(Constants.EC_LOGIN, R.string.account_sign_out_fail)
        }
    }

    suspend fun getCurrentUser(): AccountState {
        auth.getCurrentUser()?.let { user ->
            return AccountState.RequestUserSuccess(user)
        }
        return AccountState.Fail(Constants.EC_REQUEST_USER, R.string.account_request_user_fail)
    }


}