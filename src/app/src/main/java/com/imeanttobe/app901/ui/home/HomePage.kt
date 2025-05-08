package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.navigation.BottomNavItem
import com.imeanttobe.app901.navigation.NavItem.CartDetailPageNavItem
import com.imeanttobe.app901.navigation.NavItem.RecipeDetailPageNavItem
import com.imeanttobe.app901.ui.cart.CartListPage
import com.imeanttobe.app901.ui.components.bottombar.BottomBar
import com.imeanttobe.app901.ui.home.components.AddCartDialog
import com.imeanttobe.app901.ui.home.components.AddCartFloatingActionButton
import com.imeanttobe.app901.ui.item.ItemFindPage
import com.imeanttobe.app901.ui.recipe.RecipeHomePage

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val tabs = listOf(BottomNavItem.Cart, BottomNavItem.Recipe, BottomNavItem.Item)

    Scaffold(
        bottomBar = {
            BottomBar(
                currentTab = viewModel.currentTab,
                onTabChange = { newValue -> viewModel.setCurrentTab(newValue) },
                tabs = tabs
            ) },
        floatingActionButton = {
            if (viewModel.currentTab == BottomNavItem.Cart) {
                AddCartFloatingActionButton(onClick = { viewModel.setIsDialogOpened(true) })
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (viewModel.currentTab) {
                BottomNavItem.Cart -> {
                    CartListPage(
                        navigateToCartDetail = { id -> navigate(CartDetailPageNavItem.createRoute(id)) }
                    )
                    if (viewModel.isDialogOpened) {
                        AddCartDialog(
                            onDismissRequest = { viewModel.setIsDialogOpened(false) },
                            onConfirm = { _, _ -> {} }
                        )
                    }
                }
                BottomNavItem.Item -> {
                    ItemFindPage()
                }
                BottomNavItem.Recipe -> {
                    RecipeHomePage(
                        navigateToRecipeDetail = { id -> navigate(RecipeDetailPageNavItem.createRoute(id)) }
                    )
                }
            }
        }
    }
}