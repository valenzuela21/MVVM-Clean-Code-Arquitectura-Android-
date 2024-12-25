package com.cursosant.winescompose.common.model

import com.cursosant.winescompose.R
import com.cursosant.winescompose.common.entities.MyException
import com.cursosant.winescompose.common.utils.Constants

open class BaseRepository {
    protected suspend fun executeAction(
        myException: MyException =
            MyException(Constants.EC_UNKNOWN, R.string.common_unknown_error),
        block: suspend () -> Unit
    ) {
        try {
            block()
        } catch (e: Exception) {
            throw myException
        }
    }
}