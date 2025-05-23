package com.imeanttobe.app901.data.type

sealed class ConcurrencyState {
    object Default : ConcurrencyState()

    object Loading : ConcurrencyState()

    data class Success(
        val result: Any? = null,
    ) : ConcurrencyState()

    data class Failure(
        val message: String,
    ) : ConcurrencyState()
}
