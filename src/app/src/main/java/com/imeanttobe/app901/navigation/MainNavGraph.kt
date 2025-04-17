package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imeanttobe.app901.type.Cart
import com.imeanttobe.app901.ui.cart.CartAddPage
import com.imeanttobe.app901.ui.cart.CartDetailPage
import com.imeanttobe.app901.ui.cart.CartListPage
import com.imeanttobe.app901.ui.home.HomePage
import com.imeanttobe.app901.ui.item.ItemFindPage
import com.imeanttobe.app901.ui.onboarding.LoginPage
import com.imeanttobe.app901.ui.onboarding.PermissionPage
import com.imeanttobe.app901.ui.onboarding.SplashPage
import com.imeanttobe.app901.ui.profile.ProfilePage
import com.imeanttobe.app901.ui.recipe.RecipeDetailPage
import com.imeanttobe.app901.ui.recipe.RecipeFindPage
import com.imeanttobe.app901.ui.recipe.RecipeHomePage

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.HomePage.route,
        modifier = modifier
    ) {
        // Onboarding pages
        composable(
            route = NavItem.SplashPage.route,
        ) {
            SplashPage()
        }
        composable(
            route = NavItem.LoginPage.route,
        ) {
            LoginPage()
        }
        composable(
            route = NavItem.PermissionPage.route,
        ) {
            PermissionPage()
        }

        // Profile page
        composable(
            route = NavItem.ProfilePage.route,
        ) {
            ProfilePage()
        }

        // Home page
        composable(
            route = NavItem.HomePage.route,
        ) {
            HomePage()
        }
    }
}