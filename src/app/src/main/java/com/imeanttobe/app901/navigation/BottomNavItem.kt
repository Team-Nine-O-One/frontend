package com.imeanttobe.app901.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.R

sealed class BottomNavItem(
    val id: Int,
    val stringResId: Int,
    val label: String,
    val icon: ImageVector,
) {
    object MemoBottomNavItem : BottomNavItem(
        id = 0,
        stringResId = R.string.cart,
        label = "memo",
        icon = Icons.Rounded.ShoppingCart,
    )

    object HistoryBottomNavItem : BottomNavItem(
        id = 1,
        stringResId = R.string.history,
        label = "history",
        icon = Icons.Rounded.History,
    )

    object ProfileBottomNavItem : BottomNavItem(
        id = 2,
        stringResId = R.string.profile,
        label = "profile",
        icon = Icons.Rounded.Person,
    )

    object DevBottomNavItem : BottomNavItem(
        id = 3,
        stringResId = R.string.dev,
        label = "dev",
        icon = Icons.Rounded.DeveloperMode,
    )
}
