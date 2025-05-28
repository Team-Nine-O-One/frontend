package com.imeanttobe.app901.ui.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.CartRepo
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.model.SimplifiedHistory
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorySectionViewModel
    @Inject
    constructor(
        private val cartRepo: CartRepo,
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _searchBarTextValue = mutableStateOf("")
        private val _historyList = mutableStateOf<List<SimplifiedHistory>>(emptyList())
        private val _cartConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)

        // States
        val searchBarTextValue: State<String> = _searchBarTextValue
        val historyList: State<List<SimplifiedHistory>> = _historyList
        val cartConcurrencyState: State<ConcurrencyState> = _cartConcurrencyState

        // Functions
        fun setSearchBarTextValue(value: String) {
            _searchBarTextValue.value = value
        }

        fun resetConcurrencyState() {
            _cartConcurrencyState.value = ConcurrencyState.Default
        }

        fun loadCarts() {
            _cartConcurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                val userId = userRepo.getUserId()
                val result = cartRepo.getAllCarts(userId = userId)

                if (result.isSuccess) {
                    _historyList.value = result.getOrThrow()
                    _cartConcurrencyState.value = ConcurrencyState.Success()
                } else {
                    val exception = result.exceptionOrNull() ?: Exception("Unknown error")
                    _cartConcurrencyState.value = ConcurrencyState.Failure(exception.toString())
                }
            }
        }

        fun search() {}
    }
