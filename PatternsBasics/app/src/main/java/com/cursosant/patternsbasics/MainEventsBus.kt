package com.cursosant.patternsbasics

import com.cursosant.patternsbasics.eventBus.EventBus
import com.cursosant.patternsbasics.eventBus.Result
import com.cursosant.patternsbasics.eventBus.SportEvent
import com.cursosant.patternsbasics.singleton.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

private lateinit var  eventBus: EventBus
private  val job = Job()
private val scope = CoroutineScope(Dispatchers.IO + job)

fun main(){
    initEventBus()
    runBlocking {
        /*setupSubscriber(scope)
        setupSubscriberTwo(scope)
        setupPublisher()*/
        setupSubscriberResults(scope)
        setupSubscriberError(scope)
        setupSubscriberAnalytics(scope)
        setupPublishers()
    }
}


fun initEventBus() {
    eventBus = Singleton.eventBusInstance() //EventBus()
}

suspend fun setupSubscriber(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        // Subscribing to events wrapped in Result<String> (you can use other types based on your needs)
        eventBus.subscribe<Result> { result ->
            println(result.sportName) // Handle the event
        }
    }
}

suspend fun setupSubscriberTwo(coroutineScope: CoroutineScope){
    coroutineScope.launch {
        eventBus.subscribe<Result> { result ->
            if(result.isWarning) println("WARNING: ${result.sportName}")
        }
    }
}

suspend fun setupPublisher(){
    getEventsInRealTime().forEach{
        delay(500)
        eventBus.publish(it)
    }

}

suspend fun setupPublishers() {

    scope.launch {
        getAdEventsInRealTime().forEach { adEvent ->
            delay(someTime() * 2)
            eventBus.publish(adEvent)
        }
    }

    getResultEventsInRealTime().forEach { resultEvent ->
        delay(someTime())
        eventBus.publish(resultEvent)
    }
}

fun setupSubscriberResults(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.ResultSuccess> { event ->
            println("Result: ${event.sportName}")
        }
    }
}

fun setupSubscriberError(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.ResultError> { event ->
            println("Code: ${event.code} Message: ${event.msg}")
        }
    }
}

fun setupSubscriberAnalytics(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.AdEvent> { event ->
            println("Add Click. Send data to server...")
        }
    }
}



fun someTime(): Long = Random.nextLong(500, 2_000)