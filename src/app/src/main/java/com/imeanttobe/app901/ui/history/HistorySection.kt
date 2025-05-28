package com.imeanttobe.app901.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.history.component.HistorySearchBar

@Composable
fun HistorySection(viewModel: HistorySectionViewModel = hiltViewModel()) {
    Column {
        HistorySearchBar(
            text = viewModel.searchBarTextValue.value,
            onValueChange = viewModel::setSearchBarTextValue,
            onSearch = viewModel::search,
            modifier = Modifier.padding(16.dp),
        )
    }
}
