package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imeanttobe.app901.ui.analysis.AnalysisPage
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
            HomePage(
                navigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                },
                navigateAndClearBackStack = { route ->
                    navController.navigate(route) {
                        popUpTo(route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable(route = NavItem.SplashNavItem.route) {
            SplashPage(navigate = { route ->
                navController.navigate(route) {
                    popUpTo(0) // Pop all back stack entries
                    launchSingleTop = true
                }
            })
        }

        composable(route = NavItem.LoginNavItem.route) {
            LoginPage(
                navigate = { route -> navController.navigate(route) },
                navigateAndClearBackStack = { route ->
                    navController.navigate(route) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                },
            )
        }

        composable(route = NavItem.RegisterNavItem.route) {
            RegisterPage(navigate = { route ->
                navController.navigate(route) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            })
        }

        composable(
            route = NavItem.AnalysisNavItem.route + "/{analysisId}",
            arguments =
                listOf(
                    navArgument("analysisId") {
                        type = NavType.StringType
                    },
                ),
        ) {
            val analysisId = it.arguments?.getLong("analysisId")
            requireNotNull(analysisId) { "analysisId parameter wasn't found. Please make sure it's set!" }
            AnalysisPage(
                analysisId = analysisId,
                navigateBack = { navController.popBackStack() },
                onDone = {},
            )
        }
    }
}
