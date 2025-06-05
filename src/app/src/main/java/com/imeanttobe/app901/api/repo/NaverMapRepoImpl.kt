package com.imeanttobe.app901.api.repo

import android.util.Log
import com.imeanttobe.app901.api.service.NaverMapService
import com.imeanttobe.app901.data.model.NaverMapRoute
import javax.inject.Inject

class NaverMapRepoImpl
    @Inject
    constructor(
        private val naverMapService: NaverMapService,
    ) : NaverMapRepo {
        override suspend fun getRoute(
            start: Pair<Double, Double>,
            goal: Pair<Double, Double>,
            waypoints: List<Pair<Double, Double>>,
        ): Result<NaverMapRoute> {
            val flattenStart = "${start.first},${start.second}"
            val flattenGoal = "${goal.first},${goal.second}"
            val flattenWaypoints =
                waypoints.joinToString(separator = ":") { "${it.first},${it.second}" }

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
                        Pair(
                            body.route
                                .traoptimal
                                .first()
                                .summary.goal.location[0],
                            body.route
                                .traoptimal
                                .first()
                                .summary.goal.location[1],
                        )
                    val goal =
                        Pair(
                            body.route
                                .traoptimal
                                .first()
                                .summary.goal.location[0],
                            body.route
                                .traoptimal
                                .first()
                                .summary.goal.location[1],
                        )
                    val paths =
                        body.route
                            .traoptimal
                            .first()
                            .path
                            .map { path ->
                                Pair(path[0], path[1])
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
