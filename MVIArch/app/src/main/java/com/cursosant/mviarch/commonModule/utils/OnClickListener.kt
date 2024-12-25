package com.cursosant.mviarch.commonModule.utils

import com.cursosant.mviarch.commonModule.entries.Wine

interface OnClickListener {
    fun onFavorite(wine: Wine)
    fun onLongClick(wine: Wine)
}