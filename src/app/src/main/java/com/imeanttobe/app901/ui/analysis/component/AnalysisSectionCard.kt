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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnalysisSectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    AnalysisSectionCardContent(
        title = AnnotatedString(title),
        modifier = modifier,
        content = content,
    )
}

@Composable
fun AnalysisSectionCard(
    title: AnnotatedString,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    AnalysisSectionCardContent(
        title = title,
        modifier = modifier,
        content = content,
    )
}

@Composable
private fun AnalysisSectionCardContent(
    title: AnnotatedString,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier =
            modifier
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(24.dp),
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        content()
    }
}
