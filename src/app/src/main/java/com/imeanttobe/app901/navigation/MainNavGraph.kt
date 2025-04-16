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
        startDestination = "splash",
        modifier = modifier
    ) {
        // Cart pages
        composable(
            route = "cart",
        ) {
            CartListPage()
        }
        composable(
            route = "cart/add",
        ) {
            CartAddPage()
        }
        composable(
            route = "cart/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId")
            CartDetailPage(itemId = itemId)
        }

        // Item pages
        composable(
            route = "item",
        ) {
            ItemFindPage()
        }

        // Onboarding pages
        composable(
            route = "splash",
        ) {
            SplashPage()
        }
        composable(
            route = "login",
        ) {
            LoginPage()
        }
        composable(
            route = "permission",
        ) {
            PermissionPage()
        }

        // Recipe pages
        composable(
            route = "recipe",
        ) {
            RecipeHomePage()
        }
        composable(
            route = "recipe/find",
        ) {
            RecipeFindPage()
        }
        composable(
            route = "recipe/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId")
            RecipeDetailPage(itemId = itemId)
        }

        // Profile page
        composable(
            route = "profile",
        ) {
            ProfilePage()
        }
    }
}