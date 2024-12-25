package com.cursosant.patternsbasics.eventBus

import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlin.coroutines.coroutineContext

class EventBus {

    private val _events = MutableSharedFlow<Any>()
    val events: SharedFlow<Any> = _events

    suspend fun publish(event: Any){
        _events.emit(event);
    }

    suspend inline fun <reified T> subscribe(crossinline onEvent: (T) -> Unit){
        events.filterIsInstance<T>()
            .collectLatest { event ->
                coroutineContext.ensureActive()
                onEvent(event)
            }
    }
}