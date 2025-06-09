package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.ImportMemoFromUrlResponse
import retrofit2.Response
import retrofit2.http.GET

interface CrawlerService {
    @GET("/crawler/youtube")
    suspend fun importMemoFromYouTube(url: String): Response<ImportMemoFromUrlResponse>

    @GET("/crawler/naverToProduct")
    suspend fun importMemoFromNaverBlog(url: String): Response<ImportMemoFromUrlResponse>
}
