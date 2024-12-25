package com.cursosant.mvvmarch.favoriteModule.viewModel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.mvvmarch.R
import com.cursosant.mvvmarch.common.entities.Wine
import com.cursosant.mvvmarch.common.utils.Constants
import com.cursosant.mvvmarch.common.viewModel.BaseWineViewModel
import com.cursosant.mvvmarch.favoriteModule.model.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(private val repository: FavouriteRepository): BaseWineViewModel() {


    init {
        getAllWines()
    }

    override fun getAllWines(){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.IO) {
                    val result = repository.getAllWines();
                    result?.let { setWines(it) }
                    if(result == null){
                        setSnackBarMsg(R.string.account_error_no_resolve)
                    }
                }

            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }

    override fun addWine(wine: Wine){
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try{
                withContext(Dispatchers.IO){
                    val result = repository.addWine(wine)
                    if(result != -1L){
                        setSnackBarMsg(R.string.room_save_fail)
                    }else{
                        setSnackBarMsg(R.string.room_save_success)
                    }
                }
            }finally{
                setInProgress(Constants.HIDE)
            }
        }
    }

    fun deleteWine(wine: Wine){
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try{
                withContext(Dispatchers.IO) {
                    val result = repository.deleteWine(wine)
                    if (result == 0) {
                        setSnackBarMsg(R.string.room_save_fail)
                    } else {
                        setSnackBarMsg(R.string.room_save_success)
                    }
                }
            }finally{
                setInProgress(Constants.HIDE)
            }
        }
    }
}