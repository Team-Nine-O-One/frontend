package com.imeanttobe.app901.data.model

data class Analysis(
    val onlineCount: Int,
    val offlineCount: Int,
    val onlineStore: Store,
    val offlineStores: List<Store>,
) {
    companion object {
        fun getDefaultInstance(): Analysis =
            Analysis(
                onlineCount = 12,
                offlineCount = 5,
                onlineStore = Store.getDefaultInstance(),
                offlineStores =
                    listOf(
                        Store.getDefaultInstance().copy(
                            name = "GS더프레시 용산점",
                            pos = LatAndLng(lat = 37.529036, lng = 126.966016),
                        ),
                        Store.getDefaultInstance().copy(
                            name = "롯데마트 이촌점",
                            pos = LatAndLng(lat = 37.520996, lng = 126.974770),
                        ),
                        Store.getDefaultInstance(),
                    ),
            )
    }
}
