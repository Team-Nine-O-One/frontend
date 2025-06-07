package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Store
import com.imeanttobe.app901.ui.component.NaverMap

@Composable
fun OfflineAnalysisCard(
    stores: List<Store>,
    modifier: Modifier = Modifier,
) {
    AnalysisSectionCard(
        title =
            buildAnnotatedString {
                append(stringResource(R.string.offline))
                append(stringResource(R.string.tips_analysis_efficient))
            },
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NaverMap(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
            )

            stores.forEach { store ->
                OfflineMartCard(
                    store = store,
                    products = store.products,
                ) {
                    StoreCardDescription(
                        store = store,
                        isOffline = true,
                    )
                }
            }
        }
    }
}
