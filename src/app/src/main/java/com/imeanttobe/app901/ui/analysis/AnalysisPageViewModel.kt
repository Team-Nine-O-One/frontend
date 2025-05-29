package com.imeanttobe.app901.ui.analysis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.AnalysisRepo
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisPageViewModel
    @Inject
    constructor(
        private val analysisRepo: AnalysisRepo,
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _analysisConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)
        private val _analysis = mutableStateOf<Analysis?>(null)
        private val _sliderValue = mutableFloatStateOf(0f)

        // States
        val analysisConcurrencyState: State<ConcurrencyState> = _analysisConcurrencyState
        val analysis: State<Analysis?> = _analysis
        val sliderValue: State<Float> = _sliderValue

        // Functions
        fun resetConcurrencyState() {
            _analysisConcurrencyState.value = ConcurrencyState.Default
        }

        fun setSliderValue(value: Float) {
            _sliderValue.value = value
        }

        fun fetchAnalysis(analysisId: Long) {
            _analysisConcurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                try {
                    val result =
                        analysisRepo.getAnalysisById(
                            analysisId = analysisId,
                            userId = userRepo.getUserId(),
                        )
                    if (result.isSuccess) {
                        _analysis.value = result.getOrNull()
                        _analysisConcurrencyState.value = ConcurrencyState.Success()
                    } else {
                        _analysisConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown Error")
                    }
                } catch (e: Exception) {
                    _analysisConcurrencyState.value = ConcurrencyState.Failure(e.message ?: "Unknown Error")
                }
            }
        }
    }
