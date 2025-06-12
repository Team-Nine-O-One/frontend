package com.imeanttobe.app901.api.body

import com.imeanttobe.app901.data.enum.AnalysisStatus
import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.Product
import com.imeanttobe.app901.data.model.Store

data class GetAnalysisByIdResponse(
    val onlineMart: OnlineMart,
    val offlineMarts: List<OfflineMart>,
    val status: AnalysisStatus,
)

data class OnlineMart(
    val martName: String,
    val totalItems: Int,
    val totalPrice: Double,
    val products: List<Product>,
) {
    fun toStore(): Store {
        val link =
            if (martName.contains("네이버")) {
                "https://shopping.naver.com/"
            } else if (martName.contains("쿠팡")) {
                "https://www.coupang.com/"
            } else {
                "링크 확인 불가"
            }

        return Store(
            name = martName,
            totalItems = totalItems,
            totalPrice = totalPrice.toInt(),
            link = link,
            products = products,
            isOnline = true,
        )
    }
}

data class OfflineMart(
    val martName: String,
    val distance: Double,
    val estimatedTime: String,
    val totalItems: Int,
    val totalPrice: Double,
    val products: List<Product>,
    val latitude: Double,
    val longitude: Double,
) {
    fun toStore(): Store =
        Store(
            name = martName,
            distance = distance,
            estimatedTime = estimatedTime.replace("분", "").toInt(),
            totalItems = totalItems,
            totalPrice = totalPrice.toInt(),
            products = products,
            isOnline = false,
            pos = LatAndLng(lat = latitude, lng = longitude),
        )
}
