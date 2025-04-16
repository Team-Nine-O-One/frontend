package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _index = mutableIntStateOf(0)

    // Getter
    val index: Int
        get() = _index.intValue

    // Setter
    fun setIndex(index: Int) {
        _index.intValue = index
    }
}