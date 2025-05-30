package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.ImportMemoFromUrlResponse
import retrofit2.Response
import retrofit2.http.GET

interface UtilService {
    @GET("/api/import")
    suspend fun importStringsFromUrl(): Response<ImportMemoFromUrlResponse>
}
