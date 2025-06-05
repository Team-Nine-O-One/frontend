package com.imeanttobe.app901.api

import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.service.AnalysisService
import com.imeanttobe.app901.api.service.NaverMapService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val API_BASE_URL = BuildConfig.API_BASE_URL
    private const val NAVER_MAP_DIRECTIONS_5_BASE_URL = BuildConfig.NAVER_MAP_DIRECTIONS_5_BASE_URL

    val analysisService: AnalysisService by lazy {
        Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnalysisService::class.java)
    }

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

    val naverMapService: NaverMapService by lazy {
        Retrofit
            .Builder()
            .baseUrl(NAVER_MAP_DIRECTIONS_5_BASE_URL)
            .client(naverMapOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverMapService::class.java)
    }
}
