package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.model.SimplifiedAnalysis

data class GetAllAnalysesResponse(
    val carts: List<SimplifiedAnalysis>,
)
