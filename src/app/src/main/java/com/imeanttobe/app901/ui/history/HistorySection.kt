package com.imeanttobe.app901.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.history.component.HistoryListItem
import com.imeanttobe.app901.ui.history.component.HistorySearchBar

@Composable
fun HistorySection(viewModel: HistorySectionViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = null) {
        viewModel.loadCarts()
    }

    Column {
        HistorySearchBar(
            text = viewModel.searchBarTextValue.value,
            onValueChange = viewModel::setSearchBarTextValue,
            onSearch = viewModel::search,
            modifier = Modifier.padding(16.dp),
        )

        if (viewModel.historyList.value.isNotEmpty()) {
            LazyColumn {
                items(viewModel.historyList.value.size) { index ->
                    HistoryListItem(
                        history = viewModel.historyList.value[index],
                        onClick = {},
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    )
                }
            }
        }
    }
}
