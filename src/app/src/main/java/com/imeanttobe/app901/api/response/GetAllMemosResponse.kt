package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.model.Memo

data class GetAllMemosResponse(
    val items: List<Memo>,
)
