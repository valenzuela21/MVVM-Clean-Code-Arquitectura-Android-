package com.cursosant.mvparch.mainModule.presenter

import android.util.Log
import com.cursosant.mvparch.common.EventBus
import com.cursosant.mvparch.common.SportEvent
import com.cursosant.mvparch.mainModule.model.MainRepository
import com.cursosant.mvparch.mainModule.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainActivity) {

    private val repository = MainRepository()
    private lateinit var viewScope: CoroutineScope

    fun onCreate(){
        viewScope = CoroutineScope(Dispatchers.IO + Job())
        onEvent()
    }

    fun onDestroy(){
        viewScope.cancel()
    }

    suspend  fun refresh(){
        view.clearAdapter()
        view.showAdUI(true)
        getEvents()
    }

    suspend fun getEvents() {
        view.showProgress(true)
        repository.getEvents()
    }

    suspend fun registerAd(){
        repository.registerAd()
    }

    suspend fun closeAd(){
        repository.closeAd()
    }

    suspend fun saveResult(result: SportEvent.ResultSuccess){
        view.showProgress(true)
        repository.saveResult(result)
    }

    private fun onEvent() {
        viewScope.launch {
            EventBus.instance().subscribe<SportEvent> { event ->
                this.launch {
                    when (event) {
                        is SportEvent.ResultSuccess -> {
                            view.add(event)
                            view.showProgress(false)
                        }

                        is SportEvent.ResultError -> {
                            view.showSnackBar("Code: ${event.code}, Message: ${event.msg}")
                            view.showProgress(false)
                        }

                        is SportEvent.AdEvent -> view.showToast("Add Click, send data to server...")
                        is SportEvent.ClosedAdEvent -> {
                            Log.i("CursosAntag", "Ad was closed. send data to server.")
                            view.showAdUI(false)
                        }

                        is SportEvent.SaveEvent -> {
                            view.showToast("Guardado")
                            view.showProgress(false)
                        }

                        else -> {}
                    }
                }
            }
        }
    }


}