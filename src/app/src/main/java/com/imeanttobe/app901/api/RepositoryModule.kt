package com.imeanttobe.app901.api

import com.imeanttobe.app901.api.repo.FakeMemoRepoImpl
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.service.CartService
import com.imeanttobe.app901.api.service.MemoService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFakeMemoRepo(memoRepoImpl: FakeMemoRepoImpl): MemoRepo

    @Binds
    abstract fun bindFakeCartRepo(cartRepoImpl: FakeCartRepoImpl): CartRepo

    companion object {
        @Provides
        fun provideMemoService(): MemoService = RetrofitClient.memoService

        @Provides
        fun provideCartService(): CartService = RetrofitClient.cartService
    }
}
