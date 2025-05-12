package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.MartType

data class Mart (
    val martId: Long, // PK
    val name: String,
    val type: MartType, // ENUM
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val deliveryFee: Long, // BIGINT
    val logoUrl: String,
    val homepageUrl: String
) {
    companion object {
        fun getDefaultInstance(): Mart = Mart(
            martId = -1,
            name = "홈플러스",
            type = MartType.OFFLINE,
            location = "서울시 강남구 역삼동",
            latitude = 37.49795,
            longitude = 127.02761,
            deliveryFee = 2500,
            logoUrl = "",
            homepageUrl = ""
        )
    }
}