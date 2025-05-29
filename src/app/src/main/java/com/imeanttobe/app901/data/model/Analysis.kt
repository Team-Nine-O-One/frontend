package com.imeanttobe.app901.data.model

data class Analysis(
    val onlineCount: Int,
    val offlineCount: Int,
    val onlineStore: Store,
    val offlineStore: Store,
) {
    companion object {
        fun getDefaultInstance(): Analysis =
            Analysis(
                onlineCount = 12,
                offlineCount = 5,
                onlineStore = Store.getDefaultInstance(),
                offlineStore = Store.getDefaultInstance(),
            )
    }
}
