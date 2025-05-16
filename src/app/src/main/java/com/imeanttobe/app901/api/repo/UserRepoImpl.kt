package com.imeanttobe.app901.api.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepoImpl
    @Inject
    constructor(
        private val firebaseAuth: FirebaseAuth,
    ) : UserRepo {
        override suspend fun login(
            email: String,
            password: String,
        ): Result<Boolean> =
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    Result.success(true)
                } else {
                    Result.failure(IllegalStateException("Login is successful but user is null"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun register(
            userId: String,
            email: String,
            password: String,
        ): Result<Boolean> =
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    Result.success(true)
                } else {
                    Result.failure(IllegalStateException("Register is successful but user is null"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        override fun logout(): Unit = firebaseAuth.signOut()

        override fun isLoggedIn(): Boolean = firebaseAuth.currentUser != null

        override fun getUserId(): String {
            if (firebaseAuth.currentUser != null) {
                return firebaseAuth.currentUser!!.uid
            } else {
                throw IllegalStateException("User is not logged in")
            }
        }

        override fun getNickname(): String {
            if (firebaseAuth.currentUser != null) {
                return firebaseAuth.currentUser!!.displayName ?: ""
            } else {
                throw IllegalStateException("User is not logged in")
            }
        }

        override suspend fun updateNickname(newNickname: String): Result<Boolean> {
            if (firebaseAuth.currentUser != null) {
                firebaseAuth.currentUser!!
                    .updateProfile(
                        UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(newNickname)
                            .build(),
                    ).await()

                return if (firebaseAuth.currentUser!!.displayName == newNickname) {
                    Result.success(true)
                } else {
                    Result.failure(IllegalStateException("Update is failed"))
                }
            } else {
                throw IllegalStateException("User is not logged in")
            }
        }
    }
