package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Store

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
    ) { }
}
