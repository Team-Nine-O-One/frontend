package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.AnalysisStatus

data class Analysis(
    val onlineMart: Store,
    val offlineMarts: List<Store>,
    val status: AnalysisStatus,
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
