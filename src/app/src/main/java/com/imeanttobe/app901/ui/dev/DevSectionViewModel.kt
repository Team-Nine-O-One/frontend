package com.imeanttobe.app901.ui.dev

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.type.IdGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevSectionViewModel
    @Inject
    constructor(
        private val idGenerator: IdGenerator,
        private val memoRepo: MemoRepo,
    ) : ViewModel() {
        private var _id = mutableLongStateOf(0)
        private var _result = mutableStateOf("EMPTY")
        val id: State<Long> = _id
        val result: State<String> = _result

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

        fun addGroupMemo() {
            viewModelScope.launch {
                memoRepo.addMemoGroup(
                    title = "Group example",
                    contents = listOf("Content 1", "Content 2", "Content 3"),
                )
            }
        }

        fun export() {
            viewModelScope.launch {
                _result.value = memoRepo.exportToString()
            }
        }
    }
