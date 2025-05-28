package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.model.SimplifiedHistory

data class GetAllCartsResponse(
    val carts: List<SimplifiedHistory>,
)
