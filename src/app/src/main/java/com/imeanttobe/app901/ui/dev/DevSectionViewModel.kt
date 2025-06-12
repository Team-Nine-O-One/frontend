package com.imeanttobe.app901.ui.dev

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.CrawlerRepo
import com.imeanttobe.app901.api.repo.NaverMapRepo
import com.imeanttobe.app901.data.model.LatAndLng
import com.imeanttobe.app901.data.model.NaverMapRoute
import com.imeanttobe.app901.data.type.IdGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevSectionViewModel
    @Inject
    constructor(
        private val idGenerator: IdGenerator,
        private val naverMapRepo: NaverMapRepo,
        private val crawlerRepo: CrawlerRepo,
    ) : ViewModel() {
        private var _id = mutableLongStateOf(0)
        private val _route = mutableStateOf<NaverMapRoute>(NaverMapRoute(emptyList(), LatAndLng(), LatAndLng(), 0, 0))
        private val _result = mutableStateOf<Pair<String, List<String>>>(Pair("", emptyList()))

        val id: State<Long> = _id
        val route: State<NaverMapRoute> = _route
        val result: State<Pair<String, List<String>>> = _result

        init {
            viewModelScope.launch {
                _id.longValue = idGenerator.peekId()
            }
        }

        fun assignId() {
            viewModelScope.launch {
                _id.longValue = idGenerator.assignId()
            }
        }

        fun getRoute(printToast: (String) -> Unit) {
            viewModelScope.launch {
                val result =
                    naverMapRepo.getRoute(
                        start = LatAndLng(lat = 37.854541, lng = 127.735130),
                        goal = LatAndLng(lat = 37.506821, lng = 126.958082),
                        waypoints =
                            listOf(
                                LatAndLng(37.507494, 126.962000), // 이마트 흑석
                                LatAndLng(37.507393, 126.948409), // 홈플러스 상도
                            ),
                    )
                result.onSuccess {
                    val temp = result.getOrNull()
                    if (temp != null) {
                        _route.value = temp
                    }
                    printToast("Successfully got route")
                }
                result.onFailure {
                    printToast(result.exceptionOrNull().toString())
                }
            }
        }

        fun getMemoFromYouTube() {
            viewModelScope.launch {
                try {
                    val result =
                        crawlerRepo.importMemoFromYouTube("https://www.youtube.com/shorts/A4RU7ObAY1I")
                    result.onSuccess {
                        val temp = result.getOrNull()
                        if (temp != null) {
                            _result.value = temp
                        }
                        Log.d("DevPage", temp.toString())
                    }
                    result.onFailure {
                        Log.e("DevPage", result.exceptionOrNull().toString())
                    }
                } catch (e: Exception) {
                    Log.e("DevPage", e.toString())
                }
            }
        }
    }
