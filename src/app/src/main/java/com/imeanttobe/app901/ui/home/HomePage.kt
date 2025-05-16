package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.component.BottomBar
import com.imeanttobe.app901.ui.dev.DevSection
import com.imeanttobe.app901.ui.history.HistorySection
import com.imeanttobe.app901.ui.memo.MemoSection
import com.imeanttobe.app901.ui.memo.component.MemoFloatingActionButtonMenu

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomePage(
    navigate: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    val items =
        listOf(
            Icons.Rounded.Add to "메모 추가",
            Icons.Rounded.AutoGraph to "분석",
        )

    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(
                selectedIndex = viewModel.bottomNavIndex.value,
                onChangeIndex = { newValue -> viewModel.setBottomNavIndex(newValue) },
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp),
        ) {
            when (viewModel.bottomNavIndex.value) {
                0 -> MemoSection()
                1 -> HistorySection()
                2 -> Box {}
                3 -> DevSection()
            }

            MemoFloatingActionButtonMenu(
                fabMenuExpanded = viewModel.fabMenuExpanded.value,
                setFabMenuExpanded = { newValue -> viewModel.setFabMenuExpanded(newValue) },
                items = items,
            )
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    HomePage(
        navigate = {},
    )
}
