package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Store

@Composable
fun OnlineAnalysisCard(
    store: Store,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier =
            modifier
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(16.dp),
    ) {
        // Title
        Text(
            text =
                buildAnnotatedString {
                    append(stringResource(R.string.online))
                    append(stringResource(R.string.tips_analysis_efficient))
                },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
        )

        // Store summary
        StoreCard(
            store = store,
        ) {
            StoreCardDescription(
                store = store,
                isOffline = false,
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
