package com.imeanttobe.app901.api

import com.google.firebase.auth.FirebaseAuth
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.repo.CartRepo
import com.imeanttobe.app901.api.repo.FakeCartRepoImpl
import com.imeanttobe.app901.api.repo.FakeMemoRepoImpl
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.api.repo.UserRepoImpl
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
    // Repositories here
    @Provides
    @Singleton
    fun provideCartRepo(cartService: CartService): CartRepo =
        if (BuildConfig.IS_MOCK_ENABLED) {
            FakeCartRepoImpl()
        } else {
            FakeCartRepoImpl()
        }

    @Provides
    @Singleton
    fun provideMemoRepo(memoService: MemoService): MemoRepo =
        if (BuildConfig.IS_MOCK_ENABLED) {
            FakeMemoRepoImpl()
        } else {
            FakeMemoRepoImpl()
        }

    @Provides
    @Singleton
    fun provideUserRepo(firebaseAuth: FirebaseAuth): UserRepo = UserRepoImpl(firebaseAuth)

    // API services here
    @Provides
    @Singleton
    fun provideMemoService(): MemoService = RetrofitClient.memoService

    @Provides
    @Singleton
    fun provideCartService(): CartService = RetrofitClient.cartService

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}
