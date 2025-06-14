package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.model.Product
import com.imeanttobe.app901.data.model.Store
import com.imeanttobe.app901.ui.component.MartLogo

@Composable
fun OfflineMartCard(
    store: Store,
    order: Int,
    products: List<Product>,
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val tweenDuration = 200

    Column(
        modifier =
            Modifier
                .animateContentSize(animationSpec = tween(tweenDuration))
                .then(modifier),
    ) {
        // Store info
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
        ) {
            // Mart logo
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                ) {
                    Text(
                        text = order.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp),
                    )
                }

                MartLogo(
                    name = store.martName,
                    modifier =
                        Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Mart info
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = store.martName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                description()
            }

            // Expand button
            IconButton(
                onClick = { expanded = !expanded },
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                )
            }
        }

        // List
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(animationSpec = tween(tweenDuration)) + fadeIn(),
            exit = shrinkVertically(animationSpec = tween(tweenDuration)) + fadeOut(),
        ) {
            // This is where your expanded content will go.
            // For example, you could show the list of products.
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            ) {
                // List of products
                products.forEach { product ->
                    ProductListItem(product = product)
                }
            }
        }
    }
}
