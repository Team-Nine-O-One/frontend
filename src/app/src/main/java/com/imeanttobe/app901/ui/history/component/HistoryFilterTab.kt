package com.imeanttobe.app901.ui.history.component

import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.imeanttobe.app901.data.enum.HistorySectionFilterType

@Composable
fun HistoryFilterTab(
    tab: HistorySectionFilterType,
    onChangeTab: (HistorySectionFilterType) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryTabRow(
        selectedTabIndex = tab.ordinal,
        modifier = modifier,
    ) {
        HistorySectionFilterType.entries.forEach {
            Tab(
                selected = tab == it,
                onClick = { onChangeTab(it) },
                text = { Text(text = stringResource(it.stringResId)) },
            )
        }
    }
}

@Preview
@Composable
fun HistoryFilterTabPreview() {
    HistoryFilterTab(
        tab = HistorySectionFilterType.ALL,
        onChangeTab = {},
        modifier = Modifier,
    )
}
