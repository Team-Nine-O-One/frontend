package com.imeanttobe.app901.ui.components.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.navigation.BottomNavItem

@Composable
fun BottomBar(
    currentTab: BottomNavItem,
    onTabChange: (BottomNavItem) -> Unit,
    tabs: List<BottomNavItem>,
) {
    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tab == currentTab,
                onClick = { onTabChange(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.name
                    )
                },
                label = { Text(text = stringResource(id = tab.label)) }
            )
        }
    }
}