package com.imeanttobe.app901.navigation

sealed class NavItem(
    open val label: String,
    open val route: String,
) {
    object HomeNavItem : NavItem(label = "Home", route = "/home")

    object DevNavItem : NavItem(label = "Dev", route = "/dev")
}
