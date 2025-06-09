package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.ImportMemoFromUrlResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CrawlerService {
    @GET("/crawler/youtube")
    suspend fun importMemoFromYouTube(
        @Query("url") url: String,
    ): Response<ImportMemoFromUrlResponse>

    @GET("/crawler/naverToProduct")
    suspend fun importMemoFromNaverBlog(
        @Query("url") url: String,
    ): Response<ImportMemoFromUrlResponse>
}
