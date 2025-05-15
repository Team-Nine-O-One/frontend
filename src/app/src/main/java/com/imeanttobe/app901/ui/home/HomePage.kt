package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.data.enum.HomePageIndex
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.ui.component.Header

@Composable
fun HomePage(
    navigate: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            Header(
                title = "Home",
                onHistoryClick = {},
                onProfileClick = {},
                onDevClick = { navigate(NavItem.DevNavItem.route) },
            )
        },
    ) { innerPadding ->
        when (viewModel.index.value) {
            HomePageIndex.MEMO_PAGE -> {
                Text(
                    modifier = Modifier.padding(innerPadding),
                    text = "Memo Page",
                )
            }
            HomePageIndex.HISTORY_PAGE -> {
                Text(
                    modifier = Modifier.padding(innerPadding),
                    text = "History Page",
                )
            }
        }
    }
}
