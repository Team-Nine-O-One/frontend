package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.cart.CartListPage
import com.imeanttobe.app901.ui.components.bottombar.BottomBar
import com.imeanttobe.app901.ui.home.components.AddCartDialog
import com.imeanttobe.app901.ui.home.components.AddCartFloatingActionButton
import com.imeanttobe.app901.ui.item.ItemFindPage
import com.imeanttobe.app901.ui.recipe.RecipeHomePage

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentIndex = viewModel.index,
                onIndexChange = viewModel::setIndex
            ) },
        floatingActionButton = {
            if (viewModel.index == 0) {
                AddCartFloatingActionButton(onClick = { viewModel.setIsDialogOpened(true) })
            }
        },
        modifier = modifier
    ) { innerPadding ->
        if (viewModel.isDialogOpened) {
            AddCartDialog(
                onDismissRequest = { viewModel.setIsDialogOpened(false) },
                onConfirm = { name, description -> {} }
            )
        }

        if (viewModel.index == 0) {
            CartListPage(modifier = Modifier.padding(innerPadding))
        } else if (viewModel.index == 1) {
            ItemFindPage(modifier = Modifier.padding(innerPadding))
        } else if (viewModel.index == 2) {
            RecipeHomePage(modifier = Modifier.padding(innerPadding))
        } else {
            Surface(modifier = Modifier.padding(innerPadding)) {
                Text(text = "404")
            }
        }
    }
}