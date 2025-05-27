package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imeanttobe.app901.ui.home.HomePage
import com.imeanttobe.app901.ui.login.LoginPage
import com.imeanttobe.app901.ui.register.RegisterPage
import com.imeanttobe.app901.ui.splash.SplashPage

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.SplashNavItem.route,
        modifier = modifier,
    ) {
        composable(route = NavItem.HomeNavItem.route) {
            HomePage(navigate = { route ->
                navController.navigate(route) {
                    popUpTo(NavItem.HomeNavItem.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }

        composable(route = NavItem.SplashNavItem.route) {
            SplashPage(navigate = { route ->
                navController.navigate(route) {
                    popUpTo(NavItem.SplashNavItem.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }

        composable(route = NavItem.LoginNavItem.route) {
            LoginPage(navigate = { route -> navController.navigate(route) })
        }

        composable(route = NavItem.RegisterNavItem.route) {
            RegisterPage(navigate = { route -> navController.navigate(route) })
        }
    }
}
