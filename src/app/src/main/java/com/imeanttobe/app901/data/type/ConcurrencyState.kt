package com.imeanttobe.app901.data.type

sealed class ConcurrencyState {
    object Initial : ConcurrencyState()

    object Loading : ConcurrencyState()

    object Success : ConcurrencyState()

    data class Failure(
        val message: String,
    ) : ConcurrencyState()
}
