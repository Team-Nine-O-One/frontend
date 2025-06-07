package com.imeanttobe.app901.ui.component

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.launch

@Composable
fun NaverMap(
    modifier: Modifier = Modifier,
    start: LatLng? = null,
    goal: LatLng? = null,
    waypoints: List<LatLng>? = null,
    pathPoints: List<LatLng> = emptyList(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // remember MapView once
    val mapView = remember { MapView(context) }
    var naverMap by remember { mutableStateOf<NaverMap?>(null) }
    var pathOverlay = remember { PathOverlay() }
    val routeColor = 0xBB979797.toInt()
    val startMarkerColor = 0xFF1E88E5.toInt()
    val goalMarkerColor = 0xFFE53935.toInt()
    val waypointMarkerColor = 0xFF8E24AA.toInt()

    // Set lifecycleObserver
    val lifecycleObserver =
        remember {
            LifecycleEventObserver { _, event ->
                coroutineScope.launch {
                    when (event) {
                        Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                        Lifecycle.Event.ON_START -> mapView.onStart()
                        Lifecycle.Event.ON_RESUME -> mapView.onResume()
                        Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                        Lifecycle.Event.ON_STOP -> mapView.onStop()
                        Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                        else -> Unit
                    }
                }
            }
        }

    // Apply new path when pathPoints change
    LaunchedEffect(naverMap, pathPoints) {
        if (naverMap != null && pathPoints.size >= 2) {
            Log.d("NaverMap", "LaunchedEffect: applying path")

            // apply path
            pathOverlay.map = null
            pathOverlay.coords = pathPoints
            pathOverlay.width = 20
            pathOverlay.color = routeColor
            pathOverlay.map = naverMap

            // apply start marker
            start?.let { latLng ->
                val startMarker = Marker()
                startMarker.icon = MarkerIcons.BLACK
                startMarker.iconTintColor = startMarkerColor
                startMarker.position = latLng
                startMarker.map = naverMap
            }

            // apply end marker
            goal?.let { latLng ->
                val endMarker = Marker()
                endMarker.icon = MarkerIcons.BLACK
                endMarker.iconTintColor = goalMarkerColor
                endMarker.position = latLng
                endMarker.map = naverMap
            }

            // apply waypoint marker
            waypoints?.let { list ->
                list.forEach { latLng ->
                    val waypointMarker = Marker()
                    waypointMarker.icon = MarkerIcons.BLACK
                    waypointMarker.iconTintColor = waypointMarkerColor
                    waypointMarker.position = latLng
                    waypointMarker.map = naverMap
                }
            }

            // move camera
            val bounds = LatLngBounds.from(pathPoints)
            val update =
                CameraUpdate
                    .fitBounds(bounds, 100)
                    .animate(CameraAnimation.Easing, 1000)
            naverMap!!.moveCamera(update)
        }
    }

    // Destroy MapView lifecycle
    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            mapView.onDestroy()
        }
    }

    // Print AndroidView
    AndroidView(
        factory = {
            Log.d("NaverMap", "factory called")
            mapView.apply {
                getMapAsync { nMap ->
                    val seoul = LatLng(37.5665, 126.9780)
                    val cameraUpdate = CameraUpdate.scrollTo(seoul)
                    nMap.moveCamera(cameraUpdate)

                    naverMap = nMap
                }
            }
        },
        modifier = modifier,
    )
}
