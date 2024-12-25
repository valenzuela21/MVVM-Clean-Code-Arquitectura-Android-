package com.cursosant.clean.mainModule.view

import com.cursosant.clean.common.SportEvent

interface MainView {
    fun add(event: SportEvent.ResultSuccess)
    fun clearAdapter()
    fun showProgress(isVisible: Boolean)
    fun showSnackBar(msg: String)
    suspend fun showAdUI(isVisible: Boolean)
    suspend fun showToast(msg: String)
}