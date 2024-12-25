package com.cursosant.mvparch.mainModule.model

import com.cursosant.mvparch.common.EventBus
import com.cursosant.mvparch.common.SportEvent
import com.cursosant.mvparch.common.getAdEventsInRealTime
import com.cursosant.mvparch.common.getResultEventsInRealTime
import com.cursosant.mvparch.common.someTime
import kotlinx.coroutines.delay

class MainRepository {

    suspend fun getEvents(){
            val events = getResultEventsInRealTime()
            events.forEach(){ event ->
                delay(someTime())
                publishEvent(event)
            }
    }

    suspend fun saveResult(result: SportEvent.ResultSuccess){
        val response =  if(result.isWarning)
            SportEvent.ResultError(30, "Error al guardar.")
        else SportEvent.SaveEvent
       publishEvent(response)
    }

    suspend fun registerAd(){
        val events = getAdEventsInRealTime()
        publishEvent(events.first())
    }

    suspend fun closeAd(){
        publishEvent(SportEvent.ClosedAdEvent)
    }

    private suspend fun publishEvent(event: SportEvent){
        EventBus.instance().publish(event)
    }
}