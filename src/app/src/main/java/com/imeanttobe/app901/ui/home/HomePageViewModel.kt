package com.imeanttobe.app901.ui.home

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
        val index = mutableStateOf<HomePageIndex>(HomePageIndex.MEMO_PAGE)
            private set

        // Setter
        fun setIndex(newValue: HomePageIndex) {
            index.value = newValue
        }
    }
