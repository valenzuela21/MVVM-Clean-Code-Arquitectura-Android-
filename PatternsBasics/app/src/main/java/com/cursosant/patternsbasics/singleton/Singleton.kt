package com.cursosant.patternsbasics.singleton

import com.cursosant.patternsbasics.eventBus.EventBus

object Singleton {
    private val eventBusInstance: EventBus by lazy { EventBus() }
    fun eventBusInstance() = eventBusInstance

}