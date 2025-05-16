package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
    @Inject
    constructor() : ViewModel() {
        // Variables
        private val _bottomNavIndex = mutableIntStateOf(0)
        private val _fabMenuExpanded = mutableStateOf(false)

        // Getter
        val bottomNavIndex: State<Int> = _bottomNavIndex
        val fabMenuExpanded: State<Boolean> = _fabMenuExpanded

        // Setter
        fun setBottomNavIndex(newValue: Int) {
            _bottomNavIndex.intValue = newValue
        }

        fun setFabMenuExpanded(newValue: Boolean) {
            _fabMenuExpanded.value = newValue
        }
    }
