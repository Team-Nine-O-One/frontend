package com.imeanttobe.app901.ui.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun IngredientItemCard(
    modifier: Modifier = Modifier,
    ingredient: Ingredient,
    enabled: Boolean,
    onCheckboxClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(CardDefaults.outlinedShape)
            .then(modifier)
    ) {
        Row() {
            // Image here
            Box(modifier = Modifier.aspectRatio(1f)) {
                // TODO: Have to change this Image composable to Coil's one
                Image(
                    painter = painterResource(R.drawable.tomato),
                    contentDescription = null
                )
                Checkbox(
                    checked = enabled,
                    onCheckedChange = { newValue -> onCheckboxClick(newValue) }
                )
            }

            // Data here
            Row(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Item's name and mart
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = ingredient.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "이마트 용산점", // TODO: Have to set mart info here
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Price
                Text(
                    text = "3,000", // TODO: Have to set price here
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun IngredientItemCardPreview() {
    IngredientItemCard(
        ingredient = Ingredient.getDefaultInstance(),
        enabled = true,
        onCheckboxClick = {}
    )
}