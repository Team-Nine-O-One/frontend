package com.imeanttobe.app901.api.body

import com.imeanttobe.app901.data.enum.AnalysisStatus
import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.Product
import com.imeanttobe.app901.data.model.Store

data class GetAnalysisByIdResponse(
    val onlineMart: OnlineMart,
    val optimal: OfflineMart,
    val distance: OfflineMart,
    val price: OfflineMart,
    val status: AnalysisStatus,
    val optimalMartRoute: List<String>,
    val distancePriorityMartRoute: List<String>,
    val pricePriorityMartRoute: List<String>,
)

data class OnlineMart(
    val martName: String,
    val totalItems: Int,
    val totalPrice: Double,
    val products: List<Product>,
) {
    fun toStore(): Store {
        val link =
            if (this@OnlineMart.martName.contains("네이버")) {
                "https://shopping.naver.com/"
            } else if (this@OnlineMart.martName.contains("쿠팡")) {
                "https://www.coupang.com/"
            } else {
                "링크 확인 불가"
            }

        return Store(
            martName = this@OnlineMart.martName,
            totalItems = totalItems,
            totalPrice = totalPrice.toInt(),
            link = link,
            products = products,
            isOnline = true,
        )
    }
}

data class OfflineMart(
    val routeName: String,
    val marts: List<Mart>,
)

data class Mart(
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
            martName = this@Mart.martName,
            distance = this@Mart.distance,
            estimatedTime = this@Mart.estimatedTime.replace("분", "").toInt(),
            totalItems = this@Mart.totalItems,
            totalPrice = this@Mart.totalPrice.toInt(),
            products = this@Mart.products,
            isOnline = false,
            pos = LatAndLng(lat = this@Mart.latitude, lng = this@Mart.longitude),
        )
}
