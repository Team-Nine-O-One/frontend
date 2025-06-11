package com.imeanttobe.app901.api

import android.util.Log
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.service.AnalysisService
import com.imeanttobe.app901.api.service.CrawlerService
import com.imeanttobe.app901.api.service.NaverMapService
import com.imeanttobe.app901.api.service.RemoteMemoService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Base URLs
    private const val API_BASE_URL = BuildConfig.API_BASE_URL
    private const val NAVER_MAP_DIRECTIONS_5_BASE_URL = BuildConfig.NAVER_MAP_DIRECTIONS_5_BASE_URL

    // HTTP client, if needed
    private val naverMapOkHttpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest =
                    originalRequest
                        .newBuilder()
                        .header("x-ncp-apigw-api-key-id", BuildConfig.NAVER_MAP_API_CLIENT_ID)
                        .header("x-ncp-apigw-api-key", BuildConfig.NAVER_MAP_API_CLIENT_KEY)
                        .build()
                chain.proceed(newRequest)
            }.build()
    }
    private val loggingOkHttpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("RetrofitClient", "Request URL: ${request.url}")
                val response = chain.proceed(request)
                val responseBody = response.body?.string()
                Log.d("RetrofitClient", "Response JSON: $responseBody")
                // Re-create the response body because it can be consumed only once
                response
                    .newBuilder()
                    .body(responseBody?.toResponseBody(response.body?.contentType()))
                    .build()
            }.build()
    }

    // Services
    val analysisService: AnalysisService by lazy {
        Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .client(loggingOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnalysisService::class.java)
    }

    val crawlerService: CrawlerService by lazy {
        Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .client(loggingOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CrawlerService::class.java)
    }

    val naverMapService: NaverMapService by lazy {
        Retrofit
            .Builder()
            .baseUrl(NAVER_MAP_DIRECTIONS_5_BASE_URL)
            .client(naverMapOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverMapService::class.java)
    }

    val remoteMemoService: RemoteMemoService by lazy {
        Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .client(loggingOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteMemoService::class.java)
    }
}
