package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Store

@Composable
fun OnlineAnalysisCard(
    store: Store,
    modifier: Modifier = Modifier,
) {
    AnalysisSectionCard(
        title =
            buildAnnotatedString {
                withStyle(style = TextStyle(color = MaterialTheme.colorScheme.primary).toSpanStyle()) {
                    append(stringResource(R.string.online))
                }
                append(stringResource(R.string.tips_analysis_efficient))
            },
        modifier = modifier,
    ) {
        // Store summary
        StoreCard(
            store = store,
        ) {
            StoreCardDescription(
                store = store,
            )
        }

        // Items
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            store.products.forEach { product ->
                ProductListItem(
                    product = product,
                )
            }
        }
    }
}
