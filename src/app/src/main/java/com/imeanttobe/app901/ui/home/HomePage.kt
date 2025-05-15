package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HomePageIndex
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.ui.component.Header
import com.imeanttobe.app901.ui.home.component.history.HistorySection
import com.imeanttobe.app901.ui.home.component.memo.MemoSection

@Composable
fun HomePage(
    navigate: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
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
