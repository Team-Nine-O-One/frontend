package com.imeanttobe.app901.api

import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.repo.CartRepo
import com.imeanttobe.app901.api.repo.FakeCartRepoImpl
import com.imeanttobe.app901.api.repo.FakeMemoRepoImpl
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.service.CartService
import com.imeanttobe.app901.api.service.MemoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCartRepo(cartService: CartService): CartRepo =
        if (BuildConfig.IS_MOCK_ENABLED) {
            FakeCartRepoImpl(cartService = cartService)
        } else {
            FakeCartRepoImpl(cartService = cartService)
        }

    @Provides
    @Singleton
    fun provideMemoRepo(memoService: MemoService): MemoRepo =
        if (BuildConfig.IS_MOCK_ENABLED) {
            FakeMemoRepoImpl(memoService = memoService)
        } else {
            FakeMemoRepoImpl(memoService = memoService)
        }

    @Provides
    @Singleton
    fun provideMemoService(): MemoService = RetrofitClient.memoService

    @Provides
    @Singleton
    fun provideCartService(): CartService = RetrofitClient.cartService
}
