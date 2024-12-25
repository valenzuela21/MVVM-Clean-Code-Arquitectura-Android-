package com.cursosant.mviarch.promoModule.intent

sealed class PromoIntent {
    data object  RequestPromos: PromoIntent()
}