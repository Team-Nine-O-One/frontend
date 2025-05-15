package com.imeanttobe.app901.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    title: String,
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(imageVector = Icons.Rounded.History, contentDescription = null)
            }
            IconButton(onClick = onProfileClick) {
                Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
fun HeaderPreview() {
    Header(
        title = "Title",
        onHistoryClick = {},
        onProfileClick = {},
    )
}
