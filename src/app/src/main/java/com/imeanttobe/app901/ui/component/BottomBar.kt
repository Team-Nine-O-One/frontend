package com.imeanttobe.app901.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun BottomBar() {
    val items =
        listOf(
            Icons.Rounded.ShoppingCart to "장바구니",
            Icons.Rounded.AutoGraph to "분석",
            Icons.Rounded.Person to "내 정보",
        )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { selectedItemIndex = index },
                icon = { Icon(item.first, contentDescription = item.second) },
                label = { Text(item.second) },
            )
        }
    }
}
