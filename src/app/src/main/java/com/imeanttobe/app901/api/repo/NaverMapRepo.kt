package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.NaverMapRoute

interface NaverMapRepo {
    suspend fun getRoute(
        start: Pair<Double, Double>,
        goal: Pair<Double, Double>,
        waypoints: List<Pair<Double, Double>>,
    ): Result<NaverMapRoute>
}
