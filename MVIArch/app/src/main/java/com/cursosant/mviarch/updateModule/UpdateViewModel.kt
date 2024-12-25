package com.cursosant.mviarch.updateModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.mviarch.commonModule.entries.Wine
import com.cursosant.mviarch.updateModule.intent.UpdateIntent
import com.cursosant.mviarch.updateModule.model.UpdateRepository
import com.cursosant.mviarch.updateModule.model.UpdateState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateViewModel(private val respository: UpdateRepository) : ViewModel() {

    private val _state = MutableStateFlow<UpdateState>(UpdateState.Init)
    val state: StateFlow<UpdateState> = _state

    val channel = Channel<UpdateIntent>(Channel.UNLIMITED)

    init {
        setupInit()
    }

    private fun setupInit() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { i ->
                when (i) {
                    is UpdateIntent.RequestWine -> requestWine(i.id)
                    is UpdateIntent.UpdateWine -> updateWine(i.wine)
                }
            }
        }
    }

    private suspend fun updateWine(wine: Wine) {
        _state.value = UpdateState.ShowProgress

        try {
            withContext(Dispatchers.IO) {
                _state.value = respository.updateWine(wine)
            }
        } finally {
            _state.value = UpdateState.HideProgress
        }
    }

    private suspend fun requestWine(id: Double) {
        _state.value = UpdateState.ShowProgress

        try {
            withContext(Dispatchers.IO) {
                _state.value = respository.requestWind(id)
            }
        } finally {
            _state.value = UpdateState.HideProgress
        }
    }


}