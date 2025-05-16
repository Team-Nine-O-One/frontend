package com.imeanttobe.app901.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.data.enum.HomePageIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    title: String,
    index: HomePageIndex,
    onNavButtonClick: () -> Unit,
    onProfileClick: () -> Unit,
    onDevClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            // Dev mode button
            if (BuildConfig.IS_DEV_MODE_ENABLED) {
                IconButton(onClick = onDevClick) {
                    Icon(imageVector = Icons.Rounded.DeveloperMode, contentDescription = null)
                }
            }

            // History & memo button
            if (index == HomePageIndex.HISTORY_PAGE) {
                IconButton(onClick = onNavButtonClick) {
                    Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
                }
            } else {
                IconButton(onClick = onNavButtonClick) {
                    Icon(imageVector = Icons.Rounded.History, contentDescription = null)
                }
            }

            // Profile button
            IconButton(onClick = onProfileClick) {
                Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(
        title = "Title",
        index = HomePageIndex.MEMO_PAGE,
        onNavButtonClick = {},
        onProfileClick = {},
    )
}
