package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.cart.components.CartItemCard

@Composable
fun CartListPage(
    modifier: Modifier = Modifier,
    viewModel: CartListPageViewModel = hiltViewModel(),
    onChangeIndex: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .verticalScroll(scrollState)
        ) {
            // Not completed cart
            Text(
                text = stringResource(R.string.not_completed_cart),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            viewModel.cart.forEach { cart ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    CartItemCard(cart = cart, onClick = { onChangeIndex(3) }) // TODO: Have to change to cart's id
                }
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

            // Completed cart
            Text(
                text = stringResource(R.string.completed_cart),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            viewModel.cart.forEach { cart ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    CartItemCard(cart = cart, onClick = { onChangeIndex(3) }) // TODO: Have to change to cart's id
                }
            }

            // Extra padding for FAB
            Box(modifier = Modifier.padding(vertical = 40.dp))
        }
    }
}

@Preview
@Composable
fun CartListPagePreview() {
    CartListPage(
        onChangeIndex = {}
    )
}