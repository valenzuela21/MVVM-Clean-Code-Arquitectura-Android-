package com.cursosant.mvvmarch.homeModule.viewModel

import androidx.lifecycle.viewModelScope
import com.cursosant.mvvmarch.R
import com.cursosant.mvvmarch.common.entities.MyExceptions
import com.cursosant.mvvmarch.common.entities.Wine
import com.cursosant.mvvmarch.common.utils.Constants
import com.cursosant.mvvmarch.common.viewModel.BaseWineViewModel
import com.cursosant.mvvmarch.homeModule.model.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel() {

    init {
        getAllWines()
    }

    override fun getAllWines() {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.getAllWines{ wines ->
                        setWines(wines)
                    }
                }
            } catch (e: MyExceptions){
                setSnackBarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }

    override fun addWine(wine: Wine) {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.addWine(wine) {
                        setSnackBarMsg(R.string.room_save_success)
                    }
                }
            } catch (e: MyExceptions){
                setSnackBarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }
}