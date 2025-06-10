package com.imeanttobe.app901.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.IconButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.HomePageDialogState
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.BottomNavItem
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.ui.component.BottomBar
import com.imeanttobe.app901.ui.component.CreateMemoDialog
import com.imeanttobe.app901.ui.component.Header
import com.imeanttobe.app901.ui.component.ImportFromRecipeDialog
import com.imeanttobe.app901.ui.dev.DevSection
import com.imeanttobe.app901.ui.history.HistorySection
import com.imeanttobe.app901.ui.history.HistorySectionViewModel
import com.imeanttobe.app901.ui.memo.MemoSection
import com.imeanttobe.app901.ui.memo.MemoSectionViewModel
import com.imeanttobe.app901.ui.memo.component.MemoFloatingActionButtonMenu
import com.imeanttobe.app901.ui.profile.ProfileSection
import com.imeanttobe.app901.ui.profile.ProfileSectionViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomePage(
    navigate: (String) -> Unit,
    navigateAndClearBackStack: (String) -> Unit,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    // ViewModels here
    val historySectionViewModel: HistorySectionViewModel = hiltViewModel()
    val memoSectionViewModel: MemoSectionViewModel = hiltViewModel()
    val profileSectionViewModel: ProfileSectionViewModel = hiltViewModel()

    val context = LocalContext.current
    val fabItems: List<Triple<ImageVector, String, () -> Unit>> =
        listOf(
            Triple(
                Icons.Rounded.Add,
                context.getString(R.string.create_memo),
            ) { viewModel.setDialogState(HomePageDialogState.CREATE_MEMO) },
            Triple(
                Icons.Rounded.AutoGraph,
                context.getString(R.string.import_from_recipe),
            ) { viewModel.setDialogState(HomePageDialogState.IMPORT_FROM_RECIPE) },
        )

    LaunchedEffect(key1 = viewModel.importFromUrlConcurrencyState.value) {
        if (viewModel.importFromUrlConcurrencyState.value is ConcurrencyState.Success) {
            delay(300)
            viewModel.resetImportFromUrlConcurrencyState()
        } else if (viewModel.importFromUrlConcurrencyState.value is ConcurrencyState.Failure) {
            delay(300)
            viewModel.resetImportFromUrlConcurrencyState()
        }
    }

    Scaffold(
        topBar = {},
        floatingActionButton = {
            if (viewModel.bottomNavItem.value == BottomNavItem.MemoBottomNavItem) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                ) {
                    MemoFloatingActionButtonMenu(
                        fabMenuExpanded = viewModel.fabMenuExpanded.value,
                        setFabMenuExpanded = { newValue -> viewModel.setFabMenuExpanded(newValue) },
                        items = fabItems,
                        modifier = Modifier.offset(x = 16.dp),
                    )

                    MediumExtendedFloatingActionButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoGraph,
                            contentDescription = "Analyze",
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = stringResource(R.string.analyze))
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomBar(
                selectedIndex = viewModel.bottomNavItem.value.id,
                onChangeIndex = { newValue -> viewModel.setBottomNavIndex(newValue) },
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            when (viewModel.bottomNavItem.value) {
                BottomNavItem.MemoBottomNavItem -> MemoSection(viewModel = memoSectionViewModel)
                BottomNavItem.HistoryBottomNavItem ->
                    HistorySection(
                        viewModel = historySectionViewModel,
                        navigateToCart = { cartId ->
                            navigate(NavItem.AnalysisNavItem.route + "/$cartId")
                        },
                        onChangeTab = { newValue -> viewModel.setBottomNavIndex(newValue) },
                    )
                BottomNavItem.ProfileBottomNavItem ->
                    ProfileSection(
                        viewModel = profileSectionViewModel,
                        navigateAndClearBackStack = navigateAndClearBackStack,
                    )
                BottomNavItem.DevBottomNavItem -> DevSection()
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
                    concurrencyState = viewModel.importFromUrlConcurrencyState.value,
                    onConfirm = {
                        viewModel.importMemosFromUrl(printToast = {
                            Toast.makeText(context, context.getString(R.string.error_invalid_url), Toast.LENGTH_SHORT).show()
                        })
                    },
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
        navigateAndClearBackStack = {},
    )
}
