package com.imeanttobe.app901.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HomePageDialogState
import com.imeanttobe.app901.navigation.BottomNavItem
import com.imeanttobe.app901.ui.component.BottomBar
import com.imeanttobe.app901.ui.dev.DevSection
import com.imeanttobe.app901.ui.history.HistorySection
import com.imeanttobe.app901.ui.home.component.CreateMemoDialog
import com.imeanttobe.app901.ui.home.component.ImportFromRecipeDialog
import com.imeanttobe.app901.ui.memo.MemoSection
import com.imeanttobe.app901.ui.memo.component.MemoFloatingActionButtonMenu

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomePage(
    navigate: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val fabItems: List<Triple<ImageVector, String, () -> Unit>> =
        listOf(
            Triple(
                Icons.Rounded.Add,
                context.getString(R.string.create_memo),
                { viewModel.setDialogState(HomePageDialogState.CREATE_MEMO) },
            ),
            Triple(
                Icons.Rounded.AutoGraph,
                context.getString(R.string.import_from_recipe),
                { viewModel.setDialogState(HomePageDialogState.IMPORT_FROM_RECIPE) },
            ),
        )

    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(
                selectedIndex = viewModel.bottomNavItem.value.index,
                onChangeIndex = { newValue -> viewModel.setBottomNavIndex(newValue) },
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp),
        ) {
            when (viewModel.bottomNavItem.value) {
                BottomNavItem.MemoBottomNavItem -> MemoSection()
                BottomNavItem.HistoryBottomNavItem -> HistorySection()
                BottomNavItem.ProfileBottomNavItem -> Box { }
                BottomNavItem.DevBottomItem -> DevSection()
            }

            if (viewModel.bottomNavItem.value == BottomNavItem.MemoBottomNavItem) {
                MemoFloatingActionButtonMenu(
                    fabMenuExpanded = viewModel.fabMenuExpanded.value,
                    setFabMenuExpanded = { newValue -> viewModel.setFabMenuExpanded(newValue) },
                    items = fabItems,
                )
            }
        }
    }

    if (viewModel.bottomNavItem.value == BottomNavItem.MemoBottomNavItem) {
        when (viewModel.dialogState.value) {
            HomePageDialogState.CREATE_MEMO ->
                CreateMemoDialog(
                    textFieldContent = viewModel.memoDialogText.value,
                    onTextFieldChange = { newValue -> viewModel.setMemoDialogText(newValue) },
                    onDismiss = { viewModel.setDialogState(HomePageDialogState.NONE) },
                    onConfirm = { viewModel.createMemo(viewModel.memoDialogText.value) },
                )
            HomePageDialogState.IMPORT_FROM_RECIPE ->
                ImportFromRecipeDialog(
                    urlFieldContent = viewModel.urlDialogText.value,
                    onUrlChange = { newValue -> viewModel.setUrlDialogText(newValue) },
                    onDismiss = { viewModel.setDialogState(HomePageDialogState.NONE) },
                    onConfirm = { }, // TODO: Have to attach API here
                )
            HomePageDialogState.NONE -> null
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    HomePage(
        navigate = {},
    )
}
