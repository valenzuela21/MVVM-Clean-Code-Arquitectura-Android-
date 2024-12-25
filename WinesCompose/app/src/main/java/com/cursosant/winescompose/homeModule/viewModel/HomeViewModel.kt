package com.cursosant.winescompose.homeModule.viewModel

import com.cursosant.winescompose.R
import com.cursosant.winescompose.common.entities.Wine
import com.cursosant.winescompose.common.viewModel.BaseWineViewModel
import com.cursosant.winescompose.homeModule.model.HomeRepository


class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel(){
    init {
        getAllWines()
    }

    override fun onPause() = clearValues()

    override fun getAllWines() {
        executeAction {
            repository.getAllWines { wines ->
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