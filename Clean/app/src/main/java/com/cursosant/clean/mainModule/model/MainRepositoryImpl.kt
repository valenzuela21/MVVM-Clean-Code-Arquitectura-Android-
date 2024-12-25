package com.cursosant.clean.mainModule.model

import com.cursosant.clean.common.EventBus
import com.cursosant.clean.common.SportEvent
import com.cursosant.clean.common.getAdEventsInRealTime
import com.cursosant.clean.common.getResultEventsInRealTime
import com.cursosant.clean.common.someTime
import com.cursosant.clean.mainModule.view.MainActivity
import kotlinx.coroutines.delay

class MainRepositoryImpl(private val ds: DataSource): Mainrepository {

    override suspend fun getEvents(){
            val events = ds.getAllEvents()
            events.forEach(){ event ->
                delay(someTime())
                publishEvent(event)
            }
    }

    override suspend fun saveResult(result: SportEvent.ResultSuccess){
        val response = ds.save(result)
        publishEvent(response)
    }

    override suspend fun registerAd(){
        val event = ds.registerAd()
        publishEvent(event)
    }


    private suspend fun publishEvent(event: SportEvent){
        EventBus.instance().publish(event)
    }
}