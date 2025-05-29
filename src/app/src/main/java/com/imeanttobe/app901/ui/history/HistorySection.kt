package com.imeanttobe.app901.ui.history

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.history.component.HistoryFilterTab
import com.imeanttobe.app901.ui.history.component.HistoryListItem
import com.imeanttobe.app901.ui.history.component.HistorySearchBar

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
            modifier = Modifier.padding(16.dp),
        )
        HistoryFilterTab(
            tab = viewModel.tabIndex.value,
            onChangeTab = viewModel::setTabIndex,
            modifier = Modifier.padding(bottom = 8.dp),
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
                LazyColumn {
                    items(viewModel.historyList.value.size) { index ->
                        HistoryListItem(
                            history = viewModel.historyList.value[index],
                            onClick = { navigateToCart(viewModel.historyList.value[index].cartId) },
                            onDelete = { history -> viewModel.deleteHistory(history) },
                            onShare = { history ->
                                shareText(
                                    context = context,
                                    textToShare = history.toString(),
                                )
                            },
                            modifier =
                                Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
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

// Helper function from above (can be in a separate utility file)
fun shareText(
    context: Context,
    textToShare: String,
    subject: String = "",
) {
    val sendIntent: Intent =
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textToShare)
            type = "text/plain"
            if (subject.isNotBlank()) {
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
        }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
