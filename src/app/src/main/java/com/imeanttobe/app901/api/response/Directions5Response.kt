package com.imeanttobe.app901.api.response

data class Directions5Response(
    val code: Int,
    val message: String,
    val currentDateTime: String,
    val route: Route,
)

data class Route(
    val traOptimal: TraOptimal,
)

data class TraOptimal(
    val summary: Summary,
    val path: List<List<Double>>,
)

data class Summary(
    val start: LocationWrapper,
    val goal: LocationWrapper,
    val distance: Int,
    val duration: Long,
)

data class LocationWrapper(
    val location: List<Double>,
)
