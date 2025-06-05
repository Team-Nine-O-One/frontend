package com.imeanttobe.app901.data.model

data class NaverMapRoute(
    val paths: List<Pair<Double, Double>>,
    val distance: Int,
    val duration: Long,
) {
    val distanceAsKilometer = distance / 1000.0
    val durationAsMinute = duration / 1000 / 60
}
