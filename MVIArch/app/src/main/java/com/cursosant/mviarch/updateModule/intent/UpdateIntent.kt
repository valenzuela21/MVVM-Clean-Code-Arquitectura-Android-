package com.cursosant.mviarch.updateModule.intent

import com.cursosant.mviarch.commonModule.entries.Wine

sealed class UpdateIntent {
    data class RequestWine(var id: Double): UpdateIntent()
    data class UpdateWine(val wine: Wine): UpdateIntent()
}