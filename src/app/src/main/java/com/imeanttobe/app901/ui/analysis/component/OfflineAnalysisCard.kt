package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.AnalysisOption
import com.imeanttobe.app901.data.model.NaverMapRoute
import com.imeanttobe.app901.data.model.Store
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.component.NaverMap

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OfflineAnalysisCard(
    stores: List<Store>,
    priceDiff: Int,
    distanceDiff: Double,
    selectedOption: AnalysisOption,
    onChangeOption: (AnalysisOption) -> Unit,
    mapState: ConcurrencyState,
    route: NaverMapRoute,
    modifier: Modifier = Modifier,
) {
    AnalysisSectionCard(
        title =
            buildAnnotatedString {
                withStyle(style = TextStyle(color = MaterialTheme.colorScheme.primary).toSpanStyle()) {
                    append(stringResource(R.string.offline))
                }
                append(stringResource(R.string.tips_analysis_efficient))
            },
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Title
            Text(
                text = stringResource(R.string.best_route),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            // Map that prints route
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(24.dp)),
            ) {
                when (mapState) {
                    is ConcurrencyState.Loading -> {
                        ContainedLoadingIndicator()
                    }
                    is ConcurrencyState.Failure -> {
                        IconAndText(
                            icon = Icons.Rounded.ErrorOutline,
                            text = stringResource(R.string.error_failed_to_fetch_data),
                            contentDescription = stringResource(R.string.error),
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    is ConcurrencyState.Success -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            // Map
                            // NaverMap(modifier = Modifier.fillMaxSize())
                            NaverMap(
                                start = stores.first().pos.toLatLng(),
                                goal = stores.last().pos.toLatLng(),
                                waypoints = stores.drop(1).dropLast(1).map { it.pos.toLatLng() },
                                pathPoints = route.paths.map { it.toLatLng() },
                                modifier = Modifier.fillMaxSize(),
                            )

                            // Description
                            ElevatedCard(
                                modifier =
                                    Modifier
                                        .align(Alignment.BottomCenter)
                                        .padding(8.dp),
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.LocationOn,
                                            contentDescription = "Distance diff",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(12.dp),
                                        )
                                        Text(
                                            text =
                                                buildAnnotatedString {
                                                    append(stringResource(R.string.distance) + " ")
                                                    append(stringResource(R.string.format_distance, distanceDiff) + " ")
                                                    append(stringResource(R.string.shorten))
                                                },
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(8.dp),
                                        )
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Sell,
                                            contentDescription = "Price diff",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(12.dp),
                                        )
                                        Text(
                                            text =
                                                buildAnnotatedString {
                                                    append(stringResource(R.string.cost) + " ")
                                                    append(stringResource(R.string.format_price, priceDiff) + " ")
                                                    append(stringResource(R.string.reduced))
                                                },
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(8.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                    is ConcurrencyState.Default -> {
                        IconAndText(
                            icon = Icons.Rounded.LocationOn,
                            text = stringResource(R.string.tips_analysis_map),
                            contentDescription = stringResource(R.string.map),
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
            // Buttons
            AnalysisOptionButton(
                selectedOption = selectedOption,
                onChangeOption = { newOption -> onChangeOption(newOption) },
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.tips_best_route),
                style = MaterialTheme.typography.labelMedium,
                color = LocalContentColor.current.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.tips_analysis_button_description),
                style = MaterialTheme.typography.labelMedium,
                color = LocalContentColor.current.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Data about each offline store
            // Title
            Text(
                text = stringResource(R.string.mart_order),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            stores.forEachIndexed { index, store ->
                OfflineMartCard(
                    store = store,
                    order = index + 1,
                    products = store.products,
                ) {
                    StoreCardDescription(
                        store = store,
                        isOffline = true,
                    )
                }
            }
            Text(
                text = stringResource(R.string.tips_mart_order),
                style = MaterialTheme.typography.labelMedium,
                color = LocalContentColor.current.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ExpandMore,
                    contentDescription = "Expand",
                    tint = LocalContentColor.current.copy(alpha = 0.7f),
                    modifier = Modifier.size(16.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.tips_mart_order_products),
                    style = MaterialTheme.typography.labelMedium,
                    color = LocalContentColor.current.copy(alpha = 0.7f),
                )
            }
        }
    }
}
