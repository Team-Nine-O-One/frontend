package com.imeanttobe.app901.data.enum

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.R

enum class AnalysisOption(
    val stringResId: Int,
    val tooltipResId: Int,
    val checkedIcon: ImageVector,
    val uncheckedIcon: ImageVector,
) {
    DISTANCE(
        stringResId = R.string.distance,
        tooltipResId = R.string.tips_analysis_option_distance,
        checkedIcon = Icons.Rounded.Route,
        uncheckedIcon = Icons.Outlined.Route,
    ),
    BEST(
        stringResId = R.string.best,
        tooltipResId = R.string.tips_analysis_option_best,
        checkedIcon = Icons.Rounded.Star,
        uncheckedIcon = Icons.Outlined.StarOutline,
    ),
    PRICE(
        stringResId = R.string.price,
        tooltipResId = R.string.tips_analysis_option_price,
        checkedIcon = Icons.Rounded.Sell,
        uncheckedIcon = Icons.Outlined.Sell,
    ),
}
