package com.cursosant.clean.mainModule.presenter

import android.util.Log
import com.cursosant.clean.common.EventBus
import com.cursosant.clean.common.SportEvent

import com.cursosant.clean.mainModule.model.Mainrepository
import com.cursosant.clean.mainModule.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenterImpl(private val view: MainView, private val repository: Mainrepository): MainPresenter {

    //private val repository = MainRepositoryImpl()
    private lateinit var viewScope: CoroutineScope

    override fun onCreate(){
        viewScope = CoroutineScope(Dispatchers.IO + Job())
        onEvent()
    }

    override fun onDestroy(){
        viewScope.cancel()
    }

    override suspend  fun refresh(){
        view.clearAdapter()
        view.showAdUI(true)
        getEvents()
    }

    override suspend fun getEvents() {
        view.showProgress(true)
        repository.getEvents()
    }

    override suspend fun registerAd(){
        repository.registerAd()
    }

    override suspend fun closeAd(){
        Log.i("CursosAntag", "Ad was closed. send data to server.")
        view.showAdUI(false)
    }

    override suspend fun saveResult(result: SportEvent.ResultSuccess){
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

                        is SportEvent.SaveEvent -> {
                            view.showToast("Guardado")
                            view.showProgress(false)
                        }

                    }
                }
            }
        }
    }


}