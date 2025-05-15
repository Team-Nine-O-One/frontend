package com.imeanttobe.app901.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.imeanttobe.app901.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    title: String,
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit,
    onDevClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            if (BuildConfig.IS_DEV_MODE_ENABLED) {
                IconButton(onClick = onDevClick) {
                    Icon(imageVector = Icons.Rounded.DeveloperMode, contentDescription = null)
                }
            }
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
