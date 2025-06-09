package com.imeanttobe.app901.api.repo

interface CrawlerRepo {
    suspend fun importMemoFromYouTube(url: String): Result<Pair<String, List<String>>>

    suspend fun importMemoFromNaverBlog(url: String): Result<Pair<String, List<String>>>
}
