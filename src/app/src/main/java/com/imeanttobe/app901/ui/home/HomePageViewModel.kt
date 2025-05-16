package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.data.enum.HomePageIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
    @Inject
    constructor() : ViewModel() {
        // Variables
        private val _index = mutableStateOf<HomePageIndex>(HomePageIndex.MEMO_PAGE)
        private val _bottomNavIndex = mutableIntStateOf(0)

        // Getter
        val index: State<HomePageIndex> = _index
        val bottomNavIndex: State<Int> = _bottomNavIndex

        // Setter
        fun setIndex(newValue: HomePageIndex) {
            _index.value = newValue
        }

        fun setBottomNavIndex(newValue: Int) {
            _bottomNavIndex.intValue = newValue
        }
    }
