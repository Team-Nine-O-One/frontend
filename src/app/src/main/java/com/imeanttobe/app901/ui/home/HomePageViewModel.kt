package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.CrawlerRepo
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.enum.HomePageDialogState
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.BottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepo,
        private val crawlerRepo: CrawlerRepo,
    ) : ViewModel() {
        // Variables
        private val _bottomNavItem = mutableStateOf<BottomNavItem>(BottomNavItem.HistoryBottomNavItem)
        private val _fabMenuExpanded = mutableStateOf(false)
        private val _dialogState = mutableStateOf<HomePageDialogState>(HomePageDialogState.NONE)

        private val _memoDialogText = mutableStateOf("")
        private val _editMemoDialogText = mutableStateOf("")
        private val _urlDialogText = mutableStateOf("")
        private val _importFromUrlConcurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)

        // Getter
        val bottomNavItem: State<BottomNavItem> = _bottomNavItem
        val fabMenuExpanded: State<Boolean> = _fabMenuExpanded
        val dialogState: State<HomePageDialogState> = _dialogState

        val memoDialogText: State<String> = _memoDialogText
        val editMemoDialogText: State<String> = _editMemoDialogText
        val urlDialogText: State<String> = _urlDialogText
        val importFromUrlConcurrencyState: State<ConcurrencyState> = _importFromUrlConcurrencyState

        // Setter
        fun setBottomNavIndex(newValue: BottomNavItem) {
            _bottomNavItem.value = newValue
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

        fun setEditMemoDialogText(newValue: String) {
            _editMemoDialogText.value = newValue
        }

        fun setUrlDialogText(newValue: String) {
            _urlDialogText.value = newValue
        }

        fun createMemo(content: String) {
            viewModelScope.launch {
                memoRepo.addMemoLeaf(content)
            }
        }

        fun resetImportFromUrlConcurrencyState() {
            _importFromUrlConcurrencyState.value = ConcurrencyState.Default
        }

        fun importMemosFromUrl(url: String) {
            _importFromUrlConcurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                val result = crawlerRepo.importMemoFromYouTube()

                if (result.isSuccess) {
                    val importedMemos = result.getOrNull()
                    if (importedMemos != null) {
                        memoRepo.addMemoGroup(importedMemos.first, importedMemos.second)
                        _importFromUrlConcurrencyState.value = ConcurrencyState.Success()
                    } else {
                        _importFromUrlConcurrencyState.value = ConcurrencyState.Failure("No memos imported")
                    }
                } else {
                    _importFromUrlConcurrencyState.value = ConcurrencyState.Failure(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
        }
    }
