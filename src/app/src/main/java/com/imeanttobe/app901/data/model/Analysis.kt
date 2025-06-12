package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.AnalysisStatus

data class Analysis(
    val onlineMart: Store,
    val offlineMarts: List<Store>,
    val status: AnalysisStatus,
    val optimalMartRoute: List<String> = emptyList(),
    val distancePriorityMartRoute: List<String> = emptyList(),
    val pricePriorityMartRoute: List<String> = emptyList(),
) {
    companion object {
        fun getDefaultInstance(): Analysis =
            Analysis(
                onlineMart = Store.getDefaultInstance(),
                offlineMarts = listOf(Store.getDefaultInstance()),
                status = AnalysisStatus.IN_PROGRESS,
            )
    }
}
