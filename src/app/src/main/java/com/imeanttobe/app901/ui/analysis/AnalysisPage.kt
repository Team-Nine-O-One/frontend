package com.imeanttobe.app901.ui.analysis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.analysis.component.AnalysisHeader
import com.imeanttobe.app901.ui.analysis.component.DistancePriceSlider
import com.imeanttobe.app901.ui.analysis.component.ProductListItem
import com.imeanttobe.app901.ui.analysis.component.StoreCard
import com.imeanttobe.app901.ui.analysis.component.StoreCardDescription
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
    val sliderState =
        rememberSliderState(
            steps = 7,
            valueRange = -5f..5f,
        )

    // Fetch the analysis data from the view model
    LaunchedEffect(key1 = null) {
        viewModel.fetchAnalysis(analysisId)
    }

    // Apply the slider value to the view model
    LaunchedEffect(key1 = sliderState) {
        snapshotFlow { sliderState.value }.collect { value ->
            viewModel.setSliderValue(value)
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
            ) {
                IconAndText(
                    icon = Icons.Rounded.ErrorOutline,
                    text = stringResource(R.string.error_failed_to_fetch_data),
                    contentDescription = stringResource(R.string.error),
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .verticalScroll(scrollState),
            ) {
                // Header
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    AnalysisHeader(analysis = viewModel.analysis.value!!)
                }
                DistancePriceSlider(
                    sliderState = sliderState,
                    modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
                )

                // Offline
                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(background = MaterialTheme.colorScheme.primaryContainer)) {
                                append(stringResource(R.string.offline))
                                append(stringResource(R.string.tips_analysis_efficient))
                            }
                        },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 64.dp, start = 32.dp, end = 32.dp),
                )
                StoreCard(
                    imageUrl = "",
                    store = viewModel.analysis.value!!.offlineStore,
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    StoreCardDescription(
                        store = viewModel.analysis.value!!.offlineStore,
                        isOffline = true,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    viewModel.analysis.value!!.offlineStore.products.forEach { product ->
                        ProductListItem(
                            product = product,
                            imageUrl = "",
                        )
                    }
                }

                // Online
                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(background = MaterialTheme.colorScheme.primaryContainer)) {
                                append(stringResource(R.string.online))
                                append(stringResource(R.string.tips_analysis_efficient))
                            }
                        },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 64.dp, start = 32.dp, end = 32.dp),
                )
                StoreCard(
                    imageUrl = "",
                    store = viewModel.analysis.value!!.onlineStore,
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    StoreCardDescription(
                        store = viewModel.analysis.value!!.onlineStore,
                        isOffline = false,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    viewModel.analysis.value!!.onlineStore.products.forEach { product ->
                        ProductListItem(
                            product = product,
                            imageUrl = "",
                        )
                    }
                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(32.dp).fillMaxWidth(),
                ) {
                    // Confirm
                    Button(
                        colors =
                            ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary,
                            ),
                        onClick = { navigateBack() },
                        modifier = Modifier.weight(1f),
                    ) { Text(text = stringResource(R.string.confirm)) }

                    // Complete
                    Button(
                        onClick = { onDone() },
                        modifier = Modifier.weight(1f),
                    ) { Text(text = stringResource(R.string.complete_cart)) }

                    // Share
                    Button(
                        colors =
                            ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary,
                            ),
                        onClick = {
                            NativeUtil.shareText(
                                context,
                                Converter.getShareTextFromProducts(
                                    viewModel.analysis.value!!
                                        .offlineStore.products,
                                ),
                            )
                        },
                    ) { Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share") }
                }
            }
        }
    }
}
