package com.imeanttobe.app901.navigation

import androidx.annotation.DrawableRes

sealed class NavItem(
    open val route: String,
    open val label: String,
    @DrawableRes open val icon: Int? = null,
) {
    // Cart pages
    data object CartListPage : NavItem(
        route = "cart",
        label = "Cart",
        icon = null,
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
        icon = null,
    )

    // Onboarding pages
    data object SplashPage : NavItem(
        route = "splash",
        label = "Splash",
        icon = null,
    )
    data object LoginPage : NavItem(
        route = "login",
        label = "Login",
        icon = null,
    )
    data object PermissionPage : NavItem(
        route = "permission",
        label = "Permission",
        icon = null,
    )

    // Recipe pages
    data object RecipeHomePage : NavItem(
        route = "recipe",
        label = "Recipe",
        icon = null,
    )
    data object RecipeFindPage : NavItem(
        route = "recipe/find",
        label = "Find Recipe",
        icon = null,
    )
    data object RecipeAddPage : NavItem(
        route = "recipe/add",
        label = "Create Recipe",
        icon = null,
    )
    data class RecipeDetailPage(val itemId: Int) : NavItem(
        route = "recipe/$itemId",
        label = "Recipe Detail",
        icon = null,
    ) {
        companion object {
            const val baseRoute = "recipe/{itemId}"
            fun createRoute(itemId: Int) = "recipe/$itemId"
        }
    }

    // Profile page
    data object ProfilePage : NavItem(
        route = "profile",
        label = "Profile",
        icon = null,
    )
}