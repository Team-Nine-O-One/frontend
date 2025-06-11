package com.imeanttobe.app901.navigation

sealed class NavItem(
    open val label: String,
    open val route: String,
) {
    object HomeNavItem : NavItem(
        label = "Home",
        route = "/home",
    )

    object DevNavItem : NavItem(
        label = "Dev",
        route = "/dev",
    )

    object SplashNavItem : NavItem(
        label = "Splash",
        route = "/",
    )

    object LoginNavItem : NavItem(
        label = "Login",
        route = "/login",
    )

    object RegisterNavItem : NavItem(
        label = "Register",
        route = "/register",
    )

    object AnalysisNavItem : NavItem(
        label = "Analysis",
        route = "/analysis",
    )

    object PermissionNavItem : NavItem(
        label = "Permission",
        route = "/permission",
    )
}
