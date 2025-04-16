package com.imeanttobe.app901.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        // Cart pages
        composable(
            route = NavItem.CartListPage.route,
        ) {
            CartListPage()
        }
        composable(
            route = NavItem.CartAddPage.route,
        ) {
            CartAddPage()
        }
        composable(
            route = NavItem.CartDetailPage.baseRoute,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId")
            CartDetailPage(itemId = itemId)
        }

        // Item pages
        composable(
            route = NavItem.ItemFindPage.route,
        ) {
            ItemFindPage()
        }

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

        // Recipe pages
        composable(
            route = NavItem.RecipeHomePage.route,
        ) {
            RecipeHomePage()
        }
        composable(
            route = NavItem.RecipeFindPage.route,
        ) {
            RecipeFindPage()
        }
        composable(
            route = NavItem.RecipeDetailPage.baseRoute,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId")
            RecipeDetailPage(itemId = itemId)
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