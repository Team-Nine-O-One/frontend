package com.imeanttobe.app901.ui.history

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.AnalysisRepo
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.enum.HistorySectionFilterType
import com.imeanttobe.app901.data.enum.HistorySectionSearchType
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorySectionViewModel
    @Inject
    constructor(
        private val analysisRepo: AnalysisRepo,
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _searchBarTextValue = mutableStateOf("")
        private val _historyList = mutableStateOf<List<SimplifiedAnalysis>>(emptyList())
        private val _cartConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)
        private val _filterTab = mutableStateOf(HistorySectionFilterType.ON_GOING)
        private val _searchTypeMenuExtended = mutableStateOf(false)
        private val _searchType = mutableStateOf(HistorySectionSearchType.TITLE)

        // States
        val searchBarTextValue: State<String> = _searchBarTextValue
        val historyList: State<List<SimplifiedAnalysis>> =
            derivedStateOf {
                when (_filterTab.value) {
                    HistorySectionFilterType.ALL -> _historyList.value.sortedBy { it.isCompleted }
                    HistorySectionFilterType.COMPLETED -> _historyList.value.filter { it.isCompleted }
                    HistorySectionFilterType.ON_GOING -> _historyList.value.filter { !it.isCompleted }
                }
            }
        val cartConcurrencyState: State<ConcurrencyState> = _cartConcurrencyState
        val filterTab: State<HistorySectionFilterType> = _filterTab
        val searchTypeMenuExpanded: State<Boolean> = _searchTypeMenuExtended
        val searchType: State<HistorySectionSearchType> = _searchType

        // Functions
        fun setSearchBarTextValue(value: String) {
            _searchBarTextValue.value = value
        }

        fun setFilterTab(value: HistorySectionFilterType) {
            _filterTab.value = value
        }

        fun setSearchTypeMenuExtended(value: Boolean) {
            _searchTypeMenuExtended.value = value
        }

        fun setSearchType(value: HistorySectionSearchType) {
            _searchType.value = value
        }

        fun resetConcurrencyState() {
            _cartConcurrencyState.value = ConcurrencyState.Default
        }

        fun loadHistories() {
            _cartConcurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                val userId = userRepo.getUserId()
                val result = analysisRepo.getAllAnalyses(userId = userId)

                if (result.isSuccess) {
                    _historyList.value = result.getOrThrow()
                    _cartConcurrencyState.value = ConcurrencyState.Success()
                } else {
                    val exception = result.exceptionOrNull() ?: Exception("Unknown error")
                    _cartConcurrencyState.value = ConcurrencyState.Failure(exception.toString())
                }
            }
        }

        fun deleteHistory(history: SimplifiedAnalysis) {
            viewModelScope.launch {
                analysisRepo.deleteAnalysis(
                    analysisId = history.cartId,
                    userId = userRepo.getUserId(),
                )
            }

            loadHistories()
        }

        fun search() {}
    }
