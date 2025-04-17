package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imeanttobe.app901.type.Cart
import com.imeanttobe.app901.type.Recipe
import com.imeanttobe.app901.ui.cart.CartDetailPage
import com.imeanttobe.app901.ui.home.HomePage
import com.imeanttobe.app901.ui.onboarding.LoginPage
import com.imeanttobe.app901.ui.onboarding.PermissionPage
import com.imeanttobe.app901.ui.onboarding.SplashPage
import com.imeanttobe.app901.ui.profile.ProfilePage
import com.imeanttobe.app901.ui.recipe.RecipeDetailPage

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.HomePageNavItem.route,
        modifier = modifier
    ) {
        // Cart pages
        composable(
            route = NavItem.CartDetailPageNavItem.baseRoute,
        ) {
            val cartId = it.arguments?.getString("cartId")
            CartDetailPage(
                cart = Cart.getDefaultInstance(),
                popBackStack = { navController.popBackStack() }
            )
        }

        // Recipe pages
        composable(
            route = NavItem.RecipeDetailPageNavItem.baseRoute,
        ) {
            val itemId = it.arguments?.getString("itemId")
            RecipeDetailPage(
                recipe = Recipe.getDefaultInstance(),
                popBackStack = { navController.popBackStack() }
            )
        }

        // Onboarding pages
        composable(
            route = NavItem.SplashPageNavItem.route,
        ) {
            SplashPage()
        }
        composable(
            route = NavItem.LoginPageNavItem.route,
        ) {
            LoginPage()
        }
        composable(
            route = NavItem.PermissionPageNavItem.route,
        ) {
            PermissionPage()
        }

        // Profile page
        composable(
            route = NavItem.ProfilePageNavItem.route,
        ) {
            ProfilePage()
        }

        // Home page
        composable(
            route = NavItem.HomePageNavItem.route,
        ) {
            HomePage(
                navigate =  { route -> navController.navigate(route) }
            )
        }
    }
}