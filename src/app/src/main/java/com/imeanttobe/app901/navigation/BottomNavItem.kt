package com.imeanttobe.app901.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Flatware
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.R

sealed class BottomNavItem(
    val icon: ImageVector,
    val name: String,
    @StringRes val label: Int
) {
    object Cart : BottomNavItem(
        icon = Icons.Outlined.ShoppingCart,
        name = "Cart",
        label = R.string.cart
    )
    object Item : BottomNavItem(
        icon = Icons.Outlined.ShoppingBag,
        name = "Item",
        label = R.string.item
    )
    object Recipe : BottomNavItem(
        icon = Icons.Outlined.Flatware,
        name = "Recipe",
        label = R.string.recipe
    )
}