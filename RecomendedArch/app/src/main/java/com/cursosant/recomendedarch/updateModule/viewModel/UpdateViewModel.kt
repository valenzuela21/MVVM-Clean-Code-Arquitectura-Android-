package com.cursosant.recomendedarch.updateModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.viewModel.BaseViewModel
import com.cursosant.recomendedarch.updateModule.model.UpdateRepository



class UpdateViewModel(private val repository: UpdateRepository): BaseViewModel() {
    private val _wine = MutableLiveData<Wine>()
    val wine: LiveData<Wine> = _wine

    fun requestWine(id: Double) {
        executeAction{
            repository.requestWine(id){ wine ->
                _wine.postValue(wine)
            }
        }
    }

    fun updateWine(newRating: String) {
        executeAction {
            _wine.value?.let{
                repository.updateWine(_wine.value, newRating) {
                    setSnackbarMsg(R.string.room_save_success)
                }
            }
        }

    }

    override fun onPause() = clearValues()
}