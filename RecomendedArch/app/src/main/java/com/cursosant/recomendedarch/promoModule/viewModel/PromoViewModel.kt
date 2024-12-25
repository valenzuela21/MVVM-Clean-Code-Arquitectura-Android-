package com.cursosant.recomendedarch.promoModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cursosant.recomendedarch.common.entities.Promo
import com.cursosant.recomendedarch.common.viewModel.BaseViewModel
import com.cursosant.recomendedarch.promoModule.model.PromoRepository

class PromoViewModel(private val repository: PromoRepository): BaseViewModel() {
    private val _promos = MutableLiveData<List<Promo>>()
    val promos: LiveData<List<Promo>> = _promos

    init {
        getPromos()
    }

    private fun getPromos() {
        executeAction {
            repository.getPromos { result ->
                _promos.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}