package com.imeanttobe.app901.data.model

import com.naver.maps.geometry.LatLng

/**
 * 위도와 경도를 갖는 데이터 클래스
 * @param lat 위도 (한국은 33도에서 43도 사이)
 * @param lng 경도 (한국은 124도에서 132도 사이)
 */
data class LatAndLng(
    val lat: Double,
    val lng: Double,
) {
    constructor() : this(0.0, 0.0)

    fun toLatLng(): LatLng = LatLng(lat, lng)
}
