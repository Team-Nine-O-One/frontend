package com.imeanttobe.app901.data.model

import com.naver.maps.geometry.LatLng

data class LatAndLng(
    val lat: Double,
    val lng: Double,
) {
    constructor() : this(0.0, 0.0)

    fun toLatLng(): LatLng = LatLng(lat, lng)
}
