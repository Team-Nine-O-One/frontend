package com.imeanttobe.app901.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.navigation.BottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _currentTab = mutableStateOf<BottomNavItem>(BottomNavItem.Cart)
    private val _isDialogOpened = mutableStateOf(false)

    // Getter
    val currentTab: BottomNavItem
        get() = _currentTab.value
    val isDialogOpened: Boolean
        get() = _isDialogOpened.value

    // Setter
    fun setCurrentTab(tab: BottomNavItem) {
        _currentTab.value = tab
    }
    fun setIsDialogOpened(value: Boolean) {
        _isDialogOpened.value = value
    }
}