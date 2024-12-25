package com.cursosant.mviarch.promoModule.model

import com.cursosant.mviarch.commonModule.entries.Promo

sealed class PromoState {
    data object Init: PromoState()
    data class Fail(val code: Int, val msgRes: Int): PromoState()
    data class RequestPromoSuccess(val list: List<Promo>): PromoState()
}