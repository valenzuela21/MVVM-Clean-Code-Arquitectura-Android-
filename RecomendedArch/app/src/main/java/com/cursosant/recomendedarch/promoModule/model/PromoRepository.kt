package com.cursosant.recomendedarch.promoModule.model

import com.cursosant.recomendedarch.common.entities.MyException
import com.cursosant.recomendedarch.common.entities.Promo
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.model.BaseRepository
import com.cursosant.recomendedarch.promoModule.model.domain.PromoDatabase

class PromoRepository(private val db: PromoDatabase): BaseRepository() {
    suspend fun getPromos(callback: (List<Promo>) -> Unit) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.promo_request_fail)){
            callback(db.getPromos())
        }
    }
}