package com.imeanttobe.app901.api.repo

interface UtilRepo {
    suspend fun importStringsFromUrl(): Result<Pair<String, List<String>>>
}
