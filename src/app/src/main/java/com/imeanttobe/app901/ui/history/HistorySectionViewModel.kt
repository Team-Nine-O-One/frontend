package com.imeanttobe.app901.ui.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.CartRepo
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.enum.HistorySectionFilterType
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
        private val _tabIndex = mutableStateOf(HistorySectionFilterType.ALL)

        // States
        val searchBarTextValue: State<String> = _searchBarTextValue
        val historyList: State<List<SimplifiedHistory>> = _historyList
        val cartConcurrencyState: State<ConcurrencyState> = _cartConcurrencyState
        val tabIndex: State<HistorySectionFilterType> = _tabIndex

        // Functions
        fun setSearchBarTextValue(value: String) {
            _searchBarTextValue.value = value
        }

        fun setTabIndex(value: HistorySectionFilterType) {
            _tabIndex.value = value
        }

        fun resetConcurrencyState() {
            _cartConcurrencyState.value = ConcurrencyState.Default
        }

        fun loadHistories() {
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

        fun deleteHistory(history: SimplifiedHistory) {
            viewModelScope.launch {
                cartRepo.deleteCart(
                    cartId = history.cartId,
                    userId = userRepo.getUserId(),
                )
            }

            loadHistories()
        }

        fun search() {}
    }
