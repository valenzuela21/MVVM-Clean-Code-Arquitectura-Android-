package com.cursosant.recomendedarch.homeModule.viewModel


import android.util.Log
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.viewModel.BaseWineViewModel
import com.cursosant.recomendedarch.homeModule.model.HomeRepository


class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel(){
    init {
        getAllWines()
    }

    override fun onPause() = clearValues()

    override fun getAllWines() {

        executeAction {
                repository.getAllWines { wines ->
                    Log.d("DEV", "eJECUTADO")
                    setWines(wines)
                }
        }
    }

    fun addWine(wine: Wine) {
        executeAction {
                repository.addWine(wine) {
                    setSnackbarMsg(R.string.room_save_success)
                }
        }

    }
}