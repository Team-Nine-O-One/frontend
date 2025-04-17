package com.imeanttobe.app901.navigation

sealed class NavItem(
    open val route: String,
) {
    // Cart pages
    data class CartDetailPageNavItem(val cartId: Int) : NavItem(
        route = "/cart/$cartId",
    ) {
        companion object {
            const val baseRoute = "/cart/{cartId}"
            fun createRoute(cartId: Int) = "/cart/$cartId"
        }
    }

    // Recipe pages
    data class RecipeDetailPageNavItem(val itemId: Int) : NavItem(
        route = "/recipe/$itemId",
    ) {
        companion object {
            const val baseRoute = "/recipe/{itemId}"
            fun createRoute(itemId: Int) = "/recipe/$itemId"
        }
    }

    // Onboarding pages
    object SplashPageNavItem : NavItem(
        route = "/splash",
    )
    object LoginPageNavItem : NavItem(
        route = "/login",
    )
    object PermissionPageNavItem : NavItem(
        route = "/permission",
    )

    // Profile page
    object ProfilePageNavItem : NavItem(
        route = "/profile",
    )

    // Home page
    object HomePageNavItem : NavItem(
        route = "/home",
    )
}