package com.cursosant.recomendedarch.common.utils

import com.cursosant.recomendedarch.common.entities.Wine


interface OnClickListener {
    fun onFavorite(wine: Wine)
    fun onLongClick(wine: Wine)
}