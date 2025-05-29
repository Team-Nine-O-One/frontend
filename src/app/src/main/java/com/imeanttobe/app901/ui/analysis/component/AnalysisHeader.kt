package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Analysis

@Composable
fun AnalysisHeader(analysis: Analysis) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Header graphics
        Box(
            modifier =
                Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(24.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.AutoGraph,
                contentDescription = "Analysis result",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.fillMaxSize(),
            )
        }

        // Title
        Text(
            text = stringResource(R.string.analysis_result),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        // Description
        Text(
            text = stringResource(R.string.format_analysis_items_count, analysis.offlineCount + analysis.onlineCount),
            style = MaterialTheme.typography.bodyMedium,
        )
        Column(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(16.dp),
        ) {
            Text(
                text =
                    buildAnnotatedString {
                        append(stringResource(R.string.offline))
                        append(stringResource(R.string.from) + " ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(
                                stringResource(
                                    R.string.format_analysis,
                                    analysis.offlineMart.products
                                        .first()
                                        .name,
                                    analysis.offlineCount,
                                ) + " ",
                            )
                        }
                        append(stringResource(R.string.product))
                    },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
            Text(
                text =
                    buildAnnotatedString {
                        append(stringResource(R.string.online))
                        append(stringResource(R.string.from) + " ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(
                                stringResource(
                                    R.string.format_analysis,
                                    analysis.onlineMart.products
                                        .first()
                                        .name,
                                    analysis.onlineCount,
                                ) + " ",
                            )
                        }
                        append(stringResource(R.string.product))
                    },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }
        Text(
            text = stringResource(R.string.analysis_recommendation),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun AnalysisHeaderPreview() {
    AnalysisHeader(
        analysis = Analysis.getDefaultInstance(),
    )
}
