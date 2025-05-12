package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.model.SimplifiedCart

data class GetAllCartResponse(
    val carts: List<SimplifiedCart>,
)
