package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.AnalysisStatus

data class Analysis(
    val onlineMart: Store,
    val optimalMarts: List<Store>,
    val distancePriorityMarts: List<Store>,
    val pricePriorityMarts: List<Store>,
    val status: AnalysisStatus,
) {
    companion object {
        fun getDefaultInstance(): Analysis =
            Analysis(
                onlineMart = Store.getDefaultInstance(),
                optimalMarts = listOf(Store.getDefaultInstance()),
                distancePriorityMarts = listOf(Store.getDefaultInstance()),
                pricePriorityMarts = listOf(Store.getDefaultInstance()),
                status = AnalysisStatus.IN_PROGRESS,
            )
    }
}
