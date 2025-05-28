package com.imeanttobe.app901.ui.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.SimplifiedHistory

@Composable
fun HistoryListItem(
    history: SimplifiedHistory,
    onClick: () -> Unit,
    onDelete: (SimplifiedHistory) -> Unit,
    onShare: (SimplifiedHistory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            Modifier
                .then(modifier)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .clickable { onClick() }
                .padding(16.dp),
    ) {
        // Top
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Title
            Text(
                text = history.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Delete button
                IconButton(
                    onClick = { onDelete(history) },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete history",
                        modifier = Modifier.padding(4.dp).weight(1f),
                    )
                }

                // Share button
                IconButton(
                    onClick = { onShare(history) },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Share history",
                        modifier = Modifier.padding(4.dp).weight(1f),
                    )
                }
            }
        }

        // Store info
        MartInfoItem(
            mart = history.marts[0],
            imageUrl = "",
            modifier = Modifier.fillMaxWidth(),
        )
        MartInfoItem(
            mart = history.marts[1],
            imageUrl = "",
            modifier = Modifier.fillMaxWidth(),
        )

        // Total price
        HorizontalDivider(modifier = Modifier)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.format_total_items_count, history.totalItems),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = history.totalPrice.toString(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
fun HistoryListItemPreview() {
    HistoryListItem(
        history = SimplifiedHistory.getDefaultInstance(),
        onClick = {},
        onDelete = {},
        onShare = {},
    )
}
