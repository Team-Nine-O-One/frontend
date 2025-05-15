package com.imeanttobe.app901.api

import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.service.CartService
import com.imeanttobe.app901.api.service.MemoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = BuildConfig.API_BASE_URL

    val memoService: MemoService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemoService::class.java)
    }

    val cartService: CartService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartService::class.java)
    }
}
