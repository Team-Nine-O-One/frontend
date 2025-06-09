package com.imeanttobe.app901.util

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.imeanttobe.app901.api.RetrofitClient
import com.imeanttobe.app901.api.repo.AnalysisRepo
import com.imeanttobe.app901.api.repo.AnalysisRepoImpl
import com.imeanttobe.app901.api.repo.CrawlerRepo
import com.imeanttobe.app901.api.repo.CrawlerRepoImpl
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.repo.MemoRepoImpl
import com.imeanttobe.app901.api.repo.NaverMapRepo
import com.imeanttobe.app901.api.repo.NaverMapRepoImpl
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.api.repo.UserRepoImpl
import com.imeanttobe.app901.api.service.AnalysisService
import com.imeanttobe.app901.api.service.CrawlerService
import com.imeanttobe.app901.api.service.NaverMapService
import com.imeanttobe.app901.data.type.IdGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    // Repositories here
    @Provides
    @Singleton
    fun provideCartRepo(analysisService: AnalysisService): AnalysisRepo = AnalysisRepoImpl(analysisService)

    @Provides
    @Singleton
    fun provideUserRepo(firebaseAuth: FirebaseAuth): UserRepo = UserRepoImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideMemoRepo(
        @ApplicationContext context: Context,
        idGenerator: IdGenerator,
    ): MemoRepo = MemoRepoImpl(context, idGenerator)

    @Provides
    @Singleton
    fun provideNaverMapRepo(naverMapService: NaverMapService): NaverMapRepo = NaverMapRepoImpl(naverMapService)

    @Provides
    @Singleton
    fun provideCrawlerRepo(crawlerService: CrawlerService): CrawlerRepo = CrawlerRepoImpl(crawlerService)

    // Services here
    @Provides
    @Singleton
    fun provideCartService(): AnalysisService = RetrofitClient.analysisService

    @Provides
    @Singleton
    fun provideNaverMapService(): NaverMapService = RetrofitClient.naverMapService

    @Provides
    @Singleton
    fun provideCrawlerService(): CrawlerService = RetrofitClient.crawlerService

    // Other stuff here
    @Provides
    @Singleton
    fun provideIdGenerator(
        @ApplicationContext context: Context,
    ): IdGenerator = IdGenerator(context)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}
