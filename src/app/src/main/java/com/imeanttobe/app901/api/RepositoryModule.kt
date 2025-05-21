package com.imeanttobe.app901.api

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.api.repo.CartRepo
import com.imeanttobe.app901.api.repo.FakeCartRepoImpl
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.repo.MemoRepoImpl
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.api.repo.UserRepoImpl
import com.imeanttobe.app901.api.service.CartService
import com.imeanttobe.app901.data.type.IdGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideUserRepo(firebaseAuth: FirebaseAuth): UserRepo = UserRepoImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideMemoRepo(
        @ApplicationContext context: Context,
    ): MemoRepo = MemoRepoImpl(context)

    @Provides
    @Singleton
    fun provideIdGenerator(
        @ApplicationContext context: Context,
    ): IdGenerator = IdGenerator(context)

    // API services here
    @Provides
    @Singleton
    fun provideCartService(): CartService = RetrofitClient.cartService

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}
