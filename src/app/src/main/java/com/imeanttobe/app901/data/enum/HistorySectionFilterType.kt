package com.imeanttobe.app901.data.enum

import com.imeanttobe.app901.R

enum class HistorySectionFilterType(
    val stringResId: Int,
) {
    ON_GOING(R.string.on_going),
    COMPLETED(R.string.completed),
    ALL(R.string.all),
}
