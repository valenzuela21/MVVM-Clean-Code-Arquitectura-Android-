package com.cursosant.mvvmarch.common.dataAccess.local

import com.cursosant.mvvmarch.common.entities.Promo

fun getAllPromos() = listOf(
    Promo(321, "Cyber Monday",
        "https://i.ytimg.com/vi/JdtZyK05gaA/sddefault.jpg"),
    Promo(353, "Black Friday",
        "https://i.ytimg.com/vi/QlH4-3bekH4/hqdefault.jpg"),
    Promo(712, "Hot Sale",
        "https://i.ytimg.com/vi/AOrWKmiwZSg/hqdefault.jpg"),
    Promo(238, "Back to School",
        "https://i.ytimg.com/vi/tiGqEgCo9Fk/hqdefault.jpg")
)