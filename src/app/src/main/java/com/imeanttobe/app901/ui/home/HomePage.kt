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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HomePageIndex
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.ui.component.BottomBar
import com.imeanttobe.app901.ui.component.Header
import com.imeanttobe.app901.ui.home.component.history.HistorySection
import com.imeanttobe.app901.ui.home.component.memo.MemoFloatingActionButtonMenu
import com.imeanttobe.app901.ui.home.component.memo.MemoSection

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomePage(
    navigate: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    var fabMenuExpanded by remember { mutableStateOf(false) }
    val headerTitle =
        when (viewModel.index.value) {
            HomePageIndex.MEMO_PAGE -> stringResource(R.string.memo)
            HomePageIndex.HISTORY_PAGE -> stringResource(R.string.history)
        }
    val targetIndex =
        when (viewModel.index.value) {
            HomePageIndex.MEMO_PAGE -> HomePageIndex.HISTORY_PAGE
            HomePageIndex.HISTORY_PAGE -> HomePageIndex.MEMO_PAGE
        }
    val items =
        listOf(
            Icons.Rounded.Add to "메모 추가",
            Icons.Rounded.AutoGraph to "분석",
        )

    Scaffold(
        topBar = {
            Header(
                title = headerTitle,
                index = viewModel.index.value,
                onNavButtonClick = { viewModel.setIndex(targetIndex) },
                onProfileClick = { viewModel.setIndex(HomePageIndex.MEMO_PAGE) },
                onDevClick = { navigate(NavItem.DevNavItem.route) },
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
            when (viewModel.index.value) {
                HomePageIndex.MEMO_PAGE -> {
                    MemoSection(emptyList())
                }
                HomePageIndex.HISTORY_PAGE -> {
                    HistorySection()
                }
            }

            MemoFloatingActionButtonMenu(
                fabMenuExpanded = fabMenuExpanded,
                setFabMenuExpanded = { newValue -> fabMenuExpanded = newValue },
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
