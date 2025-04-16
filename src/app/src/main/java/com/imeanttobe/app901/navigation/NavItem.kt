package com.imeanttobe.app901.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Flatware
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    open val route: String,
    open val label: String,
    open val icon: ImageVector? = null,
) {
    // Cart pages
    data object CartListPage : NavItem(
        route = "cart",
        label = "Cart",
        icon = Icons.Outlined.ShoppingCart,
    )
    data object CartAddPage : NavItem(
        route = "cart/add",
        label = "Create Cart",
        icon = null,
    )
    data class CartDetailPage(val itemId: Int) : NavItem(
        route = "cart/$itemId",
        label = "Cart Detail",
        icon = null,
    ) {
        companion object {
            const val baseRoute = "cart/{itemId}"
            fun createRoute(itemId: Int) = "cart/$itemId"
        }
    }

    // Item pages
    data object ItemFindPage : NavItem(
        route = "item",
        label = "Find Item",
        icon = Icons.Outlined.ShoppingBag,
    )

    // Onboarding pages
    data object SplashPage : NavItem(
        route = "/splash",
        label = "Splash",
        icon = null,
    )
    data object LoginPage : NavItem(
        route = "/login",
        label = "Login",
        icon = null,
    )
    data object PermissionPage : NavItem(
        route = "/permission",
        label = "Permission",
        icon = null,
    )

    // Recipe pages
    data object RecipeHomePage : NavItem(
        route = "/recipe",
        label = "Recipe",
        icon = Icons.Outlined.Flatware,
    )
    data object RecipeFindPage : NavItem(
        route = "/recipe/find",
        label = "Find Recipe",
        icon = null,
    )
    data object RecipeAddPage : NavItem(
        route = "/recipe/add",
        label = "Create Recipe",
        icon = null,
    )
    data class RecipeDetailPage(val itemId: Int) : NavItem(
        route = "/recipe/$itemId",
        label = "Recipe Detail",
        icon = null,
    ) {
        companion object {
            const val baseRoute = "/recipe/{itemId}"
            fun createRoute(itemId: Int) = "/recipe/$itemId"
        }
    }

    // Profile page
    data object ProfilePage : NavItem(
        route = "/profile",
        label = "Profile",
        icon = null,
    )

    // Home page
    data object HomePage : NavItem(
        route = "/home",
        label = "Home",
        icon = null,
    )
}