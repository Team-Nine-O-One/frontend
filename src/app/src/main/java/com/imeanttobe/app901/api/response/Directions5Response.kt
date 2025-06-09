package com.imeanttobe.app901.api.response

data class Directions5Response(
    val code: Int,
    val message: String,
    val currentDateTime: String,
    val route: Route,
)

data class Route(
    val traoptimal: List<Traoptimal>,
)

data class Traoptimal(
    val summary: Summary,
    val path: List<List<Double>>,
)

data class Summary(
    val start: Point,
    val goal: Goal,
    val distance: Int,
    val duration: Long,
)

data class Point(
    val location: List<Double>,
)

data class Goal(
    val location: List<Double>,
    val dir: Int,
)
