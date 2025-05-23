package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.enum.HomePageDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepo,
    ) : ViewModel() {
        // Variables
        private val _bottomNavIndex = mutableIntStateOf(0)
        private val _fabMenuExpanded = mutableStateOf(false)
        private val _dialogState = mutableStateOf(HomePageDialogState.NONE)

        private val _memoDialogText = mutableStateOf("")

        // Getter
        val bottomNavIndex: State<Int> = _bottomNavIndex
        val fabMenuExpanded: State<Boolean> = _fabMenuExpanded
        val dialogState: State<HomePageDialogState> = _dialogState

        val memoDialogText: State<String> = _memoDialogText

        // Setter
        fun setBottomNavIndex(newValue: Int) {
            _bottomNavIndex.intValue = newValue
        }

        fun setFabMenuExpanded(newValue: Boolean) {
            _fabMenuExpanded.value = newValue
        }

        fun setDialogState(newValue: HomePageDialogState) {
            _dialogState.value = newValue
        }

        fun setMemoDialogText(newValue: String) {
            _memoDialogText.value = newValue
        }

        fun createMemo(content: String) {
            viewModelScope.launch {
                memoRepo.addMemo(content)
            }
        }
    }
