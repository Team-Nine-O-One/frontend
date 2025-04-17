package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _index = mutableIntStateOf(0)
    private val _isDialogOpened = mutableStateOf(false)

    // Getter
    val index: Int
        get() = _index.intValue
    val isDialogOpened: Boolean
        get() = _isDialogOpened.value

    // Setter
    fun setIndex(index: Int) {
        _index.intValue = index
    }
    fun setIsDialogOpened(value: Boolean) {
        _isDialogOpened.value = value
    }
}