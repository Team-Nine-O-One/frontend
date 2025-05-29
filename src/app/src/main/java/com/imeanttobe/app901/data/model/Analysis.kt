package com.imeanttobe.app901.data.model

data class Analysis(
    val onlineCount: Int,
    val offlineCount: Int,
    val onlineMart: Mart,
    val offlineMart: Mart,
) {
    companion object {
        fun getDefaultInstance(): Analysis =
            Analysis(
                onlineCount = 12,
                offlineCount = 5,
                onlineMart = Mart.getDefaultInstance(),
                offlineMart = Mart.getDefaultInstance(),
            )
    }
}
