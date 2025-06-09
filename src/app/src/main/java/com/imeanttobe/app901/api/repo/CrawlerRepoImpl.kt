package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.service.CrawlerService
import com.imeanttobe.app901.util.Formatter

class CrawlerRepoImpl(
    private val crawlerService: CrawlerService,
) : CrawlerRepo {
    override suspend fun importMemoFromYouTube(url: String): Result<Pair<String, List<String>>> {
        val response =
            crawlerService.importMemoFromYouTube(Formatter.encodeUrl(url))

        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                Result.success(body.title to body.memos)
            } else {
                Result.failure(Exception("Response body is null"))
            }
        } else {
            return Result.failure(Exception("${response.code()}"))
        }
    }

    override suspend fun importMemoFromNaverBlog(url: String): Result<Pair<String, List<String>>> =
        throw Exception("Only runnable on localhost")
}
