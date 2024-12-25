package com.cursosant.clean.mainModule.model

import com.cursosant.clean.common.SportEvent

interface DataSource {
    fun save(result: SportEvent.ResultSuccess): SportEvent
    fun getAllEvents(): List<SportEvent>
    fun registerAd(): SportEvent.AdEvent
}