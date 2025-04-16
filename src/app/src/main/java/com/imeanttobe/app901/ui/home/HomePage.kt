package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.components.bottombar.BottomBar

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentIndex = viewModel.index,
                onIndexChange = viewModel::setIndex
            ) },
        modifier = modifier
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "This is home page"
            )
        }
    }
}