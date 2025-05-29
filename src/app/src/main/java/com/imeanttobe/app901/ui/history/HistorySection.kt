package com.imeanttobe.app901.ui.history

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HistorySectionFilterType
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.history.component.HistoryFilterTab
import com.imeanttobe.app901.ui.history.component.HistoryListItem
import com.imeanttobe.app901.ui.history.component.HistorySearchBar
import com.imeanttobe.app901.util.NativeUtil

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HistorySection(
    navigateToCart: (Long) -> Unit,
    viewModel: HistorySectionViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = null) {
        viewModel.loadHistories()
    }

    Column {
        HistorySearchBar(
            text = viewModel.searchBarTextValue.value,
            onValueChange = viewModel::setSearchBarTextValue,
            onSearch = viewModel::search,
            expanded = viewModel.searchTypeMenuExpanded.value,
            onExpandedChange = viewModel::setSearchTypeMenuExtended,
            searchType = viewModel.searchType.value,
            onSearchTypeChange = viewModel::setSearchType,
            modifier = Modifier.padding(16.dp),
        )
        HistoryFilterTab(
            tab = viewModel.filterTab.value,
            onChangeTab = viewModel::setFilterTab,
            modifier = Modifier.padding(),
        )

        if (viewModel.cartConcurrencyState.value is ConcurrencyState.Loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) { ContainedLoadingIndicator() }
        } else if (viewModel.cartConcurrencyState.value is ConcurrencyState.Failure) {
            IconAndText(
                icon = Icons.Rounded.Error,
                text = stringResource(R.string.error_failed_to_fetch_data),
                contentDescription = "Empty history",
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            if (viewModel.historyList.value.isNotEmpty()) {
                LazyColumn(
                    modifier =
                        Modifier.pointerInput(viewModel.filterTab.value) {
                            var accumulatedDrag: Float = 0f

                            detectHorizontalDragGestures(
                                onDragStart = { accumulatedDrag = 0f },
                                onHorizontalDrag = { _, dragAmount ->
                                    accumulatedDrag += dragAmount
                                },
                                onDragEnd = {
                                    val swipeThreshold = 50f // Larger threshold for end-of-drag
                                    val entries = HistorySectionFilterType.entries
                                    val currentIndex = entries.indexOf(viewModel.filterTab.value)

                                    if (accumulatedDrag > swipeThreshold) { // Swiped right
                                        if (currentIndex == 0) {
                                            return@detectHorizontalDragGestures
                                        }
                                        val prevIndex = (currentIndex - 1 + entries.size) % entries.size
                                        viewModel.setFilterTab(entries[prevIndex])
                                    } else if (accumulatedDrag < -swipeThreshold) { // Swiped left
                                        if (currentIndex == entries.lastIndex) {
                                            return@detectHorizontalDragGestures
                                        }
                                        val nextIndex = (currentIndex + 1) % entries.size
                                        viewModel.setFilterTab(entries[nextIndex])
                                    }
                                },
                            )
                        },
                ) {
                    itemsIndexed(viewModel.historyList.value) { index, history ->
                        val topPadding = if (index == 0) 16.dp else 8.dp
                        val bottomPadding = if (index == viewModel.historyList.value.lastIndex) 16.dp else 8.dp

                        HistoryListItem(
                            history = viewModel.historyList.value[index],
                            onClick = { navigateToCart(viewModel.historyList.value[index].cartId) },
                            onDelete = { history -> viewModel.deleteHistory(history) },
                            onShare = { history ->
                                NativeUtil.shareText(
                                    context = context,
                                    textToShare = history.toString(),
                                )
                            },
                            modifier =
                                Modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = topPadding,
                                        bottom = bottomPadding,
                                    ).fillMaxWidth(),
                        )
                    }
                }
            } else if (viewModel.historyList.value.isEmpty()) {
                IconAndText(
                    icon = Icons.Rounded.Error,
                    text = stringResource(R.string.error_no_items_history),
                    contentDescription = "Empty history",
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
