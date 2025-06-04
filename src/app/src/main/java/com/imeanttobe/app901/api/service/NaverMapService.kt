package com.imeanttobe.app901.api.service

import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMapService {
    @GET("/driving")
    suspend fun getRoute(
        @Query("start") start: String,
        @Query("goal") goal: String,
        @Query("waypoints") option: String,
    )
}
