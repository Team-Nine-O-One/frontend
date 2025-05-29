package com.imeanttobe.app901.ui.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import com.imeanttobe.app901.ui.component.EmphasizedText

@Composable
fun HistoryListItem(
    history: SimplifiedAnalysis,
    onClick: () -> Unit,
    onDelete: (SimplifiedAnalysis) -> Unit,
    onShare: (SimplifiedAnalysis) -> Unit,
    modifier: Modifier = Modifier,
) {
    /*
    val backgroundColor =
        if (isCompleted) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.primaryContainer
        }
    val contentColor =
        if (isCompleted) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        }
    val priceColor =
        if (isCompleted) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.primary
        }
     */
    val backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest
    val contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    val priceColor = MaterialTheme.colorScheme.primary
    val labelContentColor =
        if (history.isCompleted) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        }
    val labelBackgroundColor =
        if (history.isCompleted) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.primaryContainer
        }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            Modifier
                .then(modifier)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = backgroundColor)
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Status here
                Box(
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(color = labelBackgroundColor)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // Filter button contents
                    Text(
                        text =
                            stringResource(
                                if (history.isCompleted) {
                                    R.string.completed
                                } else {
                                    R.string.on_going
                                },
                            ),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = labelContentColor,
                    )
                }

                // Title here
                Text(
                    text = history.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Delete button
                IconButton(
                    onClick = { onDelete(history) },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete history",
                        tint = contentColor,
                        modifier = Modifier.padding(4.dp).weight(1f),
                    )
                }

                // Share button
                IconButton(
                    onClick = { onShare(history) },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Share history",
                        tint = contentColor,
                        modifier = Modifier.padding(4.dp).weight(1f),
                    )
                }
            }
        }

        // Store info
        MartInfoItem(
            mart = history.marts[0],
            imageUrl = "",
            contentColor = contentColor,
            priceColor = priceColor,
            modifier = Modifier.fillMaxWidth(),
        )
        MartInfoItem(
            mart = history.marts[1],
            imageUrl = "",
            contentColor = contentColor,
            priceColor = priceColor,
            modifier = Modifier.fillMaxWidth(),
        )

        // Total price
        // HorizontalDivider(modifier = Modifier, color = contentColor)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Icon and title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Sell,
                    contentDescription = "Total price",
                    tint = contentColor,
                    modifier = Modifier.size(16.dp),
                )
                Text(
                    text = stringResource(R.string.format_total_items_count, history.totalItems),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = contentColor,
                )
            }

            // Price
            EmphasizedText(
                content = history.totalPrice,
                color = priceColor,
                tail = stringResource(R.string.won),
            )
        }
    }
}

@Preview
@Composable
fun HistoryListItemPreview() {
    HistoryListItem(
        history = SimplifiedAnalysis.getDefaultInstance(),
        onClick = {},
        onDelete = {},
        onShare = {},
    )
}
