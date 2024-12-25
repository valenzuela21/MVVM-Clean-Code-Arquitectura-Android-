package com.cursosant.clean.mainModule.model

import com.cursosant.clean.common.SportEvent
import com.cursosant.clean.common.getAdEventsInRealTime
import com.cursosant.clean.common.getResultEventsInRealTime


class DataSourceImpl: DataSource {
    override fun save(result: SportEvent.ResultSuccess): SportEvent {
        return if(result.isWarning)
            SportEvent.ResultError(30, "Error al guardar.")
        else SportEvent.SaveEvent
    }

    override fun getAllEvents(): List<SportEvent> {
       return getResultEventsInRealTime()
    }

    override fun registerAd(): SportEvent.AdEvent {
        return getAdEventsInRealTime().first()
    }

}