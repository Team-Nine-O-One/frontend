package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.component.EmphasizedText

@Composable
fun ProductListItem(
    imageUrl: String,
    name: String,
    price: String,
    description: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .padding(4.dp),
        )

        // Details of product
        Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
            )
            description()
        }
    }

    // Price
    EmphasizedText(
        content = price,
        tail = stringResource(R.string.won),
    )
}
