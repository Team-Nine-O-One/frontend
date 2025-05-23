package com.imeanttobe.app901.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.navigation.BottomNavItem

@Composable
fun BottomBar(
    selectedIndex: Int = 0,
    onChangeIndex: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        BottomNavItem.items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onChangeIndex(item) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(stringResource(item.stringResId)) },
            )
        }
    }
}
