package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.cart.components.CartItemCard

@Composable
fun CartListPage(
    modifier: Modifier = Modifier,
    viewModel: CartListPageViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
                .verticalScroll(scrollState)
        ) {
            viewModel.cart.forEach { item ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    CartItemCard(title = item, content = "Description")
                }
            }
        }
    }
}

@Preview
@Composable
fun CartListPagePreview() {
    CartListPage()
}