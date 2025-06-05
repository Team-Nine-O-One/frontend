package com.imeanttobe.app901.data.model

import com.naver.maps.geometry.LatLng

data class LatAndLng(
    val lat: Double,
    val lng: Double,
) {
    fun toLatLng(): LatLng = LatLng(lat, lng)
}
