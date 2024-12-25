package com.cursosant.mviarch.updateModule.model
import com.cursosant.mviarch.commonModule.entries.Wine

sealed class UpdateState {
    data object Init: UpdateState()
    data object ShowProgress: UpdateState()
    data object HideProgress: UpdateState()
    data class Fail(val code: Int, val msgRes: Int): UpdateState()
    data class RequestWineSuccess(val wine: Wine): UpdateState()
    data object UpdateWineSuccess: UpdateState()
}