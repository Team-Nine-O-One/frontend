package com.imeanttobe.app901.ui.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HistorySectionSearchType

@Composable
fun HistorySearchBar(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    searchType: HistorySectionSearchType,
    onSearchTypeChange: (HistorySectionSearchType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    val backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest
    val searchTypeColor = MaterialTheme.colorScheme.surfaceContainerLowest
    val searchTypeText =
        if (searchType == HistorySectionSearchType.TITLE) {
            stringResource(id = R.string.title)
        } else {
            stringResource(id = R.string.store)
        }

    BasicTextField(
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = contentColor),
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(contentColor),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier =
                    Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = backgroundColor)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // Search icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search Icon",
                        tint = contentColor,
                    )

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        innerTextField()
                        if (text.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.example_search),
                                style = MaterialTheme.typography.bodyLarge,
                                color = contentColor.copy(alpha = 0.5f),
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    // Clear button
                    if (text.isNotEmpty()) {
                        IconButton(
                            onClick = { onValueChange("") },
                            enabled = text.isNotEmpty(),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear Icon",
                                tint = contentColor,
                            )
                        }
                    }

                    // Filter
                    Box(
                        modifier =
                            Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(color = searchTypeColor)
                                .clickable { onExpandedChange(true) }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        // Filter button contents
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.FilterList,
                                contentDescription = "Filter Icon",
                                tint = contentColor,
                                modifier = Modifier.size(16.dp),
                            )
                            Text(
                                text = searchTypeText,
                                style = MaterialTheme.typography.labelMedium,
                                color = contentColor,
                            )
                        }

                        // Menu
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { onExpandedChange(false) },
                        ) {
                            HistorySectionSearchType.entries.forEach { type ->
                                DropdownMenuItem(
                                    text = { Text(text = stringResource(type.stringResId)) },
                                    onClick = {
                                        onSearchTypeChange(type)
                                        onExpandedChange(false)
                                    },
                                )
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier,
    )
}
