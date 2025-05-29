package com.imeanttobe.app901.data.enum

import com.imeanttobe.app901.R

enum class HistorySectionFilterType(
    val stringResId: Int,
) {
    ALL(R.string.all),
    ON_GOING(R.string.on_going),
    COMPLETED(R.string.completed),
}
