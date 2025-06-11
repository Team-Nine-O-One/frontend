package com.imeanttobe.app901.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.imeanttobe.app901.data.model.LatAndLng
import kotlinx.coroutines.tasks.await

object NativeUtil {
    fun shareText(
        context: Context,
        textToShare: String,
        subject: String = "",
    ) {
        val sendIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textToShare)
                type = "text/plain"
                if (subject.isNotBlank()) {
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                }
            }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun openUrl(
        context: Context,
        url: String,
    ) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = url.toUri()
        context.startActivity(intent)
    }

    fun openUrl(
        context: Context,
        url: Uri,
    ) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = url
        context.startActivity(intent)
    }

    suspend fun getCurrentLocation(
        context: Context,
        priority: Int = Priority.PRIORITY_HIGH_ACCURACY,
    ): LatAndLng {
        // 권한 체크
        val fineLocationGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED

        if (!fineLocationGranted) {
            throw SecurityException("ACCESS_FINE_LOCATION 권한이 없습니다.")
        }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val location = fusedLocationClient.getCurrentLocation(priority, null).await()

        if (location != null) {
            return LatAndLng(location.latitude, location.longitude)
        } else {
            throw Exception("현재 위치를 가져올 수 없습니다.")
        }
    }
}
