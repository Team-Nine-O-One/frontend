package com.imeanttobe.app901.data.model

data class NaverMapRoute(
    val paths: List<LatAndLng>,
    val start: LatAndLng,
    val goal: LatAndLng,
    val distance: Int,
    val duration: Long,
) {
    val distanceAsKilometer = distance / 1000.0
    val durationAsMinute = duration / 1000 / 60
}
