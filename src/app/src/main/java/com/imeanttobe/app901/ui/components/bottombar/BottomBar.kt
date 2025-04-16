package com.imeanttobe.app901.ui.components.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.R
import com.imeanttobe.app901.navigation.NavItem

@Composable
fun BottomBar(
    currentIndex: Int,
    onIndexChange: (Int) -> Unit
) {
    NavigationBar {
        val items = listOf<Triple<NavItem, Int, Int>>(
            Triple(NavItem.CartListPage, 0, R.string.cart),
            Triple(NavItem.ItemFindPage, 1, R.string.item),
            Triple(NavItem.RecipeHomePage, 2, R.string.recipe),
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = currentIndex == item.second,
                onClick = { onIndexChange(item.second) },
                icon = { item.first.icon?.let { Icon(imageVector = it, contentDescription = item.first.label) } },
                label = { Text(text = stringResource(id = item.third)) }
            )
        }
    }
}