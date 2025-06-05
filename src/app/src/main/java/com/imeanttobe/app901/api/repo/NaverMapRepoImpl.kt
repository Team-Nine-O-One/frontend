package com.imeanttobe.app901.api.repo

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
                if (body != null) {
                    val start =
                        Pair(
                            body.route.traOptimal.summary.start.location[0],
                            body.route.traOptimal.summary.start.location[1],
                        )
                    val goal =
                        Pair(
                            body.route.traOptimal.summary.goal.location[0],
                            body.route.traOptimal.summary.goal.location[1],
                        )
                    val paths =
                        body.route.traOptimal.path.map {
                            Pair(it[0], it[1])
                        }

                    val result =
                        NaverMapRoute(
                            paths = listOf(start) + paths + listOf(goal),
                            distance = body.route.traOptimal.summary.distance,
                            duration = body.route.traOptimal.summary.duration,
                        )
                    return Result.success(result)
                } else {
                    return Result.failure(Exception("Response body is null"))
                }
            } else {
                return Result.failure(Exception("Failed to fetch route"))
            }
        }
    }
