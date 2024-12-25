package com.cursosant.recomendedarch.common.model

import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.utils.Constants

open class BaseRepository {
    protected suspend fun executeAction(myException: MyException =
                                  MyException(Constants.EC_UNKNOWN, R.string.common_unknown_error), block: suspend () -> Unit){
        try {
            block()
        }catch (e:Exception){
            throw myException
        }
    }
}