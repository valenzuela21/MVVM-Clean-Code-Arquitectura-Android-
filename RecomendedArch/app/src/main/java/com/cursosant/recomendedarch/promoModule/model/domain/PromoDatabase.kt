package com.cursosant.recomendedarch.promoModule.model.domain

import com.cursosant.recomendedarch.common.dataAccess.local.getAllPromos
import com.cursosant.recomendedarch.common.entities.Promo


class PromoDatabase {
    fun getPromos(): List<Promo> {
        val result = getAllPromos()
        return result.ifEmpty{
            throw Exception()
        }
    }
}