package com.imeanttobe.app901.ui.analysis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.AnalysisRepo
import com.imeanttobe.app901.api.repo.NaverMapRepo
import com.imeanttobe.app901.data.enum.AnalysisOption
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.NaverMapRoute
import com.imeanttobe.app901.data.model.Store
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisPageViewModel
    @Inject
    constructor(
        private val analysisRepo: AnalysisRepo,
        private val naverMapRepo: NaverMapRepo,
    ) : ViewModel() {
        // Variables
        private val _analysisConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)
        private val _routeConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)
        private val _analysis = mutableStateOf<Analysis?>(null)
        private val _selectedAnalysisOption = mutableStateOf<AnalysisOption>(AnalysisOption.BEST)
        private val _route = mutableStateOf<NaverMapRoute>(NaverMapRoute(emptyList(), LatAndLng(), LatAndLng(), 0, 0))
        private val _currentMarts = mutableStateOf<List<Store>>(emptyList())

        // States
        val analysisConcurrencyState: State<ConcurrencyState> = _analysisConcurrencyState
        val routeConcurrencyState: State<ConcurrencyState> = _routeConcurrencyState
        val analysis: State<Analysis?> = _analysis
        val selectedAnalysisOption: State<AnalysisOption> = _selectedAnalysisOption
        val route: State<NaverMapRoute> = _route
        val currentMarts: State<List<Store>> = _currentMarts

        // Functions
        fun resetConcurrencyState() {
            _analysisConcurrencyState.value = ConcurrencyState.Default
            _routeConcurrencyState.value = ConcurrencyState.Default
        }

        fun setAnalysisOption(option: AnalysisOption) {
            _selectedAnalysisOption.value = option
            _currentMarts.value =
                when (option) {
                    AnalysisOption.BEST -> {
                        _analysis.value!!.optimalMarts
                    }
                    AnalysisOption.DISTANCE -> {
                        _analysis.value!!.distancePriorityMarts
                    }
                    AnalysisOption.PRICE -> {
                        _analysis.value!!.pricePriorityMarts
                    }
                }
            getRoute()
        }

        fun getRoute() {
            if (_analysis.value != null) {
                _routeConcurrencyState.value = ConcurrencyState.Loading

                viewModelScope.launch {
                    if (_currentMarts.value.isNotEmpty()) {
                        val start = _currentMarts.value.first().pos!!
                        val goal = _currentMarts.value.last().pos!!
                        val waypoints = _currentMarts.value.subList(1, _currentMarts.value.size - 1)

                        val result =
                            naverMapRepo.getRoute(
                                start = start,
                                goal = goal,
                                waypoints = waypoints.map { it.pos!! },
                            )

                        if (result.isSuccess) {
                            val temp = result.getOrNull()
                            if (temp != null) {
                                _route.value = temp
                            }
                            _routeConcurrencyState.value = ConcurrencyState.Success()
                        } else {
                            _routeConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown Error")
                        }
                    } else {
                        _routeConcurrencyState.value = ConcurrencyState.Failure("No offline stores")
                    }
                }
            }
        }

        fun fetchAnalysis(analysisId: Long) {
            _analysisConcurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                try {
                    val result =
                        analysisRepo.getAnalysisById(analysisId = analysisId)
                    if (result.isSuccess) {
                        _analysis.value = result.getOrNull()
                        _currentMarts.value = _analysis.value!!.optimalMarts
                        _analysisConcurrencyState.value = ConcurrencyState.Success()
                    } else {
                        _analysisConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown Error")
                    }
                } catch (e: Exception) {
                    _analysisConcurrencyState.value = ConcurrencyState.Failure(e.message ?: "Unknown Error")
                }
            }
        }

        fun confirmAnalysis(analysisId: Long) {
            viewModelScope.launch {
                if (_analysis.value != null) {
                    val result = analysisRepo.confirmAnalysis(analysisId = analysisId)
                    if (result.isSuccess) {
                        _analysis.value = _analysis.value!!.copy(status = com.imeanttobe.app901.data.enum.AnalysisStatus.CONFIRMED)
                    } else {
                        _analysisConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown Error")
                    }
                }
            }
        }

        fun completeAnalysis(analysisId: Long) {
            viewModelScope.launch {
                if (_analysis.value != null) {
                    val result = analysisRepo.completeAnalysis(analysisId = analysisId)
                    if (result.isSuccess) {
                        _analysis.value = _analysis.value!!.copy(status = com.imeanttobe.app901.data.enum.AnalysisStatus.COMPLETED)
                    } else {
                        _analysisConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown Error")
                    }
                }
            }
        }
    }
