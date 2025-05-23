package com.imeanttobe.app901.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.navigation.BottomNavItem

@Composable
fun BottomBar(
    selectedIndex: Int = 0,
    onChangeIndex: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val list =
        listOf(
            BottomNavItem.MemoBottomNavItem,
            BottomNavItem.HistoryBottomNavItem,
            BottomNavItem.ProfileBottomNavItem,
        ) + if (BuildConfig.IS_DEV_MODE_ENABLED) listOf(BottomNavItem.DevBottomNavItem) else emptyList<BottomNavItem>()

    NavigationBar(modifier = modifier) {
        list.forEach { item ->
            NavigationBarItem(
                selected = selectedIndex == item.id,
                onClick = { onChangeIndex(item) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(stringResource(item.stringResId)) },
            )
        }
    }
}
