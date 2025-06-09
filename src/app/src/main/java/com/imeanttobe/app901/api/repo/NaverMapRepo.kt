package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.NaverMapRoute

interface NaverMapRepo {
    suspend fun getRoute(
        start: LatAndLng,
        goal: LatAndLng,
        waypoints: List<LatAndLng> = emptyList(),
    ): Result<NaverMapRoute>
}
