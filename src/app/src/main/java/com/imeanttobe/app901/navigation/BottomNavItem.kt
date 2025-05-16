package com.imeanttobe.app901.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.R

sealed class BottomNavItem(
    open val stringResId: Int,
    open val label: String,
    open val icon: ImageVector,
) {
    object CartBottomNavItem : BottomNavItem(
        stringResId = R.string.cart,
        label = "cart",
        icon = Icons.Rounded.ShoppingCart,
    )

    object AnalysisBottomNavItem : BottomNavItem(
        stringResId = R.string.analysis,
        label = "analysis",
        icon = Icons.Rounded.AutoGraph,
    )

    object ProfileBottomNavItem : BottomNavItem(
        stringResId = R.string.profile,
        label = "profile",
        icon = Icons.Rounded.Person,
    )

    object DevBottomItem : BottomNavItem(
        stringResId = R.string.dev,
        label = "dev",
        icon = Icons.Rounded.DeveloperMode,
    )

    companion object {
        val items =
            if (BuildConfig.IS_DEV_MODE_ENABLED) {
                listOf(
                    CartBottomNavItem,
                    AnalysisBottomNavItem,
                    ProfileBottomNavItem,
                    DevBottomItem,
                )
            } else {
                listOf(
                    CartBottomNavItem,
                    AnalysisBottomNavItem,
                    ProfileBottomNavItem,
                )
            }
    }
}
