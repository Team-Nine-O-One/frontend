package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

@Composable
fun StoreCard(
    imageUrl: String,
    name: String,
    productNum: Int,
    price: String,
    description: @Composable RowScope.() -> Unit,
) {
}
