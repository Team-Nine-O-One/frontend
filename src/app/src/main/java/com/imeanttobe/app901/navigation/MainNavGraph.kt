package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imeanttobe.app901.ui.dev.DevSection
import com.imeanttobe.app901.ui.home.HomePage

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.HomeNavItem.route,
        modifier = modifier,
    ) {
        composable(route = NavItem.HomeNavItem.route) {
            HomePage(navigate = { route -> navController.navigate(route) })
        }
        composable(route = NavItem.DevNavItem.route) {
            DevSection()
        }
    }
}
