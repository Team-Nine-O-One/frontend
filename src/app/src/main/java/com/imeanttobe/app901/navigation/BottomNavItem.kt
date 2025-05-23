package com.imeanttobe.app901.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.R

sealed class BottomNavItem(
    open val index: Int,
    open val stringResId: Int,
    open val label: String,
    open val icon: ImageVector,
) {
    object MemoBottomNavItem : BottomNavItem(
        index = 0,
        stringResId = R.string.cart,
        label = "memo",
        icon = Icons.Rounded.ShoppingCart,
    )

    object HistoryBottomNavItem : BottomNavItem(
        index = 1,
        stringResId = R.string.history,
        label = "history",
        icon = Icons.Rounded.History,
    )

    object ProfileBottomNavItem : BottomNavItem(
        index = 2,
        stringResId = R.string.profile,
        label = "profile",
        icon = Icons.Rounded.Person,
    )

    object DevBottomItem : BottomNavItem(
        index = 3,
        stringResId = R.string.dev,
        label = "dev",
        icon = Icons.Rounded.DeveloperMode,
    )

    companion object {
        val items =
            if (BuildConfig.IS_DEV_MODE_ENABLED) {
                listOf(
                    MemoBottomNavItem,
                    HistoryBottomNavItem,
                    ProfileBottomNavItem,
                    DevBottomItem,
                )
            } else {
                listOf(
                    MemoBottomNavItem,
                    HistoryBottomNavItem,
                    ProfileBottomNavItem,
                )
            }
    }
}
