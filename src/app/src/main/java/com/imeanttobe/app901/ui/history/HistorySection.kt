package com.imeanttobe.app901.ui.history

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HistorySectionFilterType
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.BottomNavItem
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.history.component.HistoryFilterTab
import com.imeanttobe.app901.ui.history.component.HistoryListItem
import com.imeanttobe.app901.ui.history.component.HistorySearchBar
import com.imeanttobe.app901.util.NativeUtil

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HistorySection(
    navigateToCart: (Long) -> Unit,
    onChangeTab: (BottomNavItem) -> Unit,
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
            modifier = Modifier.padding(horizontal = 16.dp),
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
            } else {
                if (viewModel.searchBarTextValue.value.isNotEmpty()) {
                    // Items are exist but no matching search keyword
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        IconAndText(
                            icon = Icons.Rounded.QuestionMark,
                            text = stringResource(R.string.error_no_search_result),
                            contentDescription = "No search result",
                        )
                    }
                } else {
                    // No search keyword and no items
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        // Let user know there isn't any items yet
                        IconAndText(
                            icon = Icons.Rounded.ErrorOutline,
                            text =
                                buildAnnotatedString {
                                    append(stringResource(R.string.error_no_items_history))
                                    append("\n")
                                    append(stringResource(R.string.tips_add_new_memo))
                                },
                            contentDescription = "Empty history",
                        )

                        Button(
                            onClick = { onChangeTab(BottomNavItem.MemoBottomNavItem) },
                            modifier = Modifier.padding(top = 32.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Add new memo",
                                modifier = Modifier.padding(end = 8.dp),
                            )

                            Text(
                                text = stringResource(R.string.add_new_memo),
                            )
                        }
                    }
                }
            }
        }
    }
}
