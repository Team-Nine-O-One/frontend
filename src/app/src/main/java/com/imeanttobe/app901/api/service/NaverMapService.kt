package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.body.Directions5Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMapService {
    @GET("/map-direction/v1/driving")
    suspend fun getRoute(
        @Query("start") start: String,
        @Query("goal") goal: String,
        @Query("waypoints") option: String,
    ): Response<Directions5Response>
}
