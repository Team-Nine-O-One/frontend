package com.imeanttobe.app901.type.data

import com.imeanttobe.app901.type.enums.MartType

data class Market(
    val marketId: Long, // PK
    val name: String,
    val type: MartType // ENUM
)