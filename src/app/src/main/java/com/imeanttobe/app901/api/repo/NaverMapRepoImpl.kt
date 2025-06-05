package com.imeanttobe.app901.api.repo

import android.util.Log
import com.imeanttobe.app901.api.service.NaverMapService
import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.NaverMapRoute
import javax.inject.Inject

class NaverMapRepoImpl
    @Inject
    constructor(
        private val naverMapService: NaverMapService,
    ) : NaverMapRepo {
        override suspend fun getRoute(
            start: LatAndLng,
            goal: LatAndLng,
            waypoints: List<LatAndLng>,
        ): Result<NaverMapRoute> {
            // API uses longitude, latitude order
            val flattenStart = "${start.lng},${start.lat}"
            val flattenGoal = "${goal.lng},${goal.lat}"
            val flattenWaypoints =
                waypoints.joinToString(separator = ":") { "${it.lng},${it.lat}" }

            if (waypoints.size > 3) {
                return Result.failure(Exception("Too many waypoints"))
            }

            val response =
                naverMapService.getRoute(
                    start = flattenStart,
                    goal = flattenGoal,
                    option = flattenWaypoints,
                )

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.code == 0) {
                    val start =
                        LatAndLng(
                            lat =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.goal.location[1],
                            lng =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.goal.location[0],
                        )
                    val goal =
                        LatAndLng(
                            lat =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.goal.location[1],
                            lng =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.goal.location[0],
                        )
                    val paths =
                        body.route
                            .traoptimal
                            .first()
                            .path
                            .map { path ->
                                LatAndLng(lat = path[1], lng = path[0])
                            }

                    val result =
                        NaverMapRoute(
                            paths = listOf(start) + paths + listOf(goal),
                            distance =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.distance,
                            duration =
                                body.route
                                    .traoptimal
                                    .first()
                                    .summary.duration,
                        )
                    return Result.success(result)
                } else {
                    Log.e("NaverMapRepoImpl", "Response body is null")
                    return Result.failure(Exception("Response body is null"))
                }
            } else {
                Log.e("NaverMapRepoImpl", "Failed to fetch route")
                return Result.failure(Exception("${response.code()}"))
            }
        }
    }
