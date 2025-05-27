package com.imeanttobe.app901.api.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepoImpl
    @Inject
    constructor(
        private val firebaseAuth: FirebaseAuth,
    ) : UserRepo {
        private val _nicknameFlow =
            MutableStateFlow(
                if (firebaseAuth.currentUser != null) {
                    firebaseAuth.currentUser!!.displayName ?: ""
                } else {
                    throw IllegalStateException("User is not logged in")
                },
            )
        val nicknameFlow: StateFlow<String> = _nicknameFlow.asStateFlow()

        override val getNicknameFlow: Flow<String> = nicknameFlow

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
            nickname: String,
            email: String,
            password: String,
        ): Result<Boolean> =
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    val user = result.user!!

                    user.updateProfile(
                        UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(nickname)
                            .build(),
                    )

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

//        override fun getNickname(): String {
//            if (firebaseAuth.currentUser != null) {
//                return firebaseAuth.currentUser!!.displayName ?: ""
//            } else {
//                throw IllegalStateException("User is not logged in")
//            }
//        }

        override suspend fun updateNickname(newValue: String): Result<Boolean> {
            if (firebaseAuth.currentUser != null) {
                firebaseAuth.currentUser!!
                    .updateProfile(
                        UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(newValue)
                            .build(),
                    ).await()

                return if (firebaseAuth.currentUser!!.displayName == newValue) {
                    _nicknameFlow.value = newValue
                    Result.success(true)
                } else {
                    Result.failure(IllegalStateException("Update is failed"))
                }
            } else {
                throw IllegalStateException("User is not logged in")
            }
        }

        override suspend fun updatePassword(newValue: String): Result<Boolean> =
            try {
                if (firebaseAuth.currentUser != null) {
                    firebaseAuth.currentUser!!.updatePassword(newValue).await()
                    Result.success(true)
                } else {
                    Log.e("UserRepoImpl", "User is not logged in")
                    Result.failure(IllegalStateException("User is not logged in"))
                }
            } catch (e: Exception) {
                Log.e("UserRepoImpl", "Error updating password", e)
                Result.failure(e)
            }
    }
