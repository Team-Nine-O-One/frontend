package com.imeanttobe.app901.ui.analysis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.analysis.component.AnalysisBottomButton
import com.imeanttobe.app901.ui.analysis.component.AnalysisHeader
import com.imeanttobe.app901.ui.analysis.component.OfflineAnalysisCard
import com.imeanttobe.app901.ui.analysis.component.OnlineAnalysisCard
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.util.Converter
import com.imeanttobe.app901.util.NativeUtil

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnalysisPage(
    analysisId: Long,
    navigateBack: () -> Unit,
    onDone: () -> Unit,
    viewModel: AnalysisPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Fetch the analysis data from the view model
    LaunchedEffect(key1 = null) {
        viewModel.fetchAnalysis(analysisId)
    }

    // Fetch the route between marts
    LaunchedEffect(key1 = viewModel.analysis.value) {
        if (viewModel.analysis.value != null) {
            viewModel.getRoute()
        }
    }

    // Composable
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Go to back")
                    }
                },
            )
        },
    ) { innerPadding ->
        if (viewModel.analysisConcurrencyState.value in listOf(ConcurrencyState.Loading, ConcurrencyState.Default)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ContainedLoadingIndicator(modifier = Modifier.size(72.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.tips_loading_analysis),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        } else if (viewModel.analysisConcurrencyState.value is ConcurrencyState.Failure || viewModel.analysis.value == null) {
            IconAndText(
                icon = Icons.Rounded.ErrorOutline,
                text = stringResource(R.string.error_failed_to_fetch_data),
                contentDescription = stringResource(R.string.error),
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .verticalScroll(scrollState),
            ) {
                // Header
                AnalysisHeader(
                    analysis = viewModel.analysis.value!!,
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                )

                // Online analysis
                OnlineAnalysisCard(
                    store = viewModel.analysis.value!!.onlineStore,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                )

                // Offline
                OfflineAnalysisCard(
                    stores = viewModel.analysis.value!!.offlineStores,
                    route = viewModel.route.value,
                    mapState = viewModel.routeConcurrencyState.value,
                    selectedOption = viewModel.selectedAnalysisOption.value,
                    onChangeOption = { newOption -> viewModel.setAnalysisOption(newOption) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                )

                // Buttons
                AnalysisBottomButton(
                    onClickCloseButton = { navigateBack() },
                    onClickCompleteButton = { onDone() },
                    onClickShareButton = {
                        NativeUtil.shareText(
                            context,
                            Converter.getShareTextFromProducts(
                                viewModel.analysis.value!!
                                    .offlineStores
                                    .first()
                                    .products,
                            ),
                        )
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            }
        }
    }
}
