package com.imeanttobe.app901.ui.dev

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.data.type.IdGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevSectionViewModel
    @Inject
    constructor(
        private val idGenerator: IdGenerator,
    ) : ViewModel() {
        private var _id = mutableLongStateOf(0)
        val id: State<Long> = _id

        init {
            viewModelScope.launch {
                _id.value = idGenerator.peekId()
            }
        }

        fun assignId() {
            viewModelScope.launch {
                _id.value = idGenerator.assignId()
            }
        }
    }
