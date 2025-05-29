package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.component.EmphasizedText

@Composable
fun StoreCard(
    imageUrl: String,
    name: String,
    numOfProducts: Int,
    price: String,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        // Store info
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
        ) {
            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Mart image",
                placeholder = rememberVectorPainter(image = Icons.Rounded.Image),
                error = rememberVectorPainter(image = Icons.Rounded.Error),
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                )
                description()
            }
        }

        // Price info
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier =
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                        .padding(16.dp),
            ) {
                EmphasizedText(
                    content = numOfProducts,
                    tail = stringResource(R.string.count),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Text(
                    text = stringResource(R.string.number_of_products),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier =
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                        .padding(16.dp),
            ) {
                EmphasizedText(
                    content = price,
                    tail = stringResource(R.string.won),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Text(
                    text = stringResource(R.string.total_price),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun StoreCardPreview() {
    StoreCard(
        imageUrl = "",
        name = "Mart",
        numOfProducts = 10,
        price = "10000",
        description = {
            Text(
                text = "Mart is a store that sells products.",
                style = MaterialTheme.typography.bodySmall,
            )
        },
    )
}
