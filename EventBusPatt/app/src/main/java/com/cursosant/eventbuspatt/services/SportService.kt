package com.cursosant.eventbuspatt.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.cursosant.eventbuspatt.eventBus.EventBus
import com.cursosant.eventbuspatt.eventBus.SportEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SportService: Service() {

    private  val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)


    override fun onCreate() {
        super.onCreate()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    fun saveResult(result: SportEvent.ResultSuccess) {
        scope.launch {
            val response = if(result.isWarning)
                SportEvent.ResultError(30, "Error al guargar")
                else SportEvent.SaveEvent
                EventBus.instance().publish(response)
        }
    }

    fun setupSubscribers(viewScope: CoroutineScope){
        viewScope.launch {
            EventBus.instance().subscribe<SportEvent> { event ->
                when(event){
                    is SportEvent.ClosedAdEvent ->
                        Log.i("CursosAntag", "Ad was closed. send data to server.")
                        else -> {}
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    // Singleton
    companion object {
        private val _service: SportService by lazy {SportService()}
        fun instance() = _service
    }

}