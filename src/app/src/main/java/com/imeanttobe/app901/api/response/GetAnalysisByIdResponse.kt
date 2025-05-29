package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.model.Mart

data class GetAnalysisByIdResponse(
    val onlineCount: Int,
    val offlineCount: Int,
    val marts: List<Mart>,
)
