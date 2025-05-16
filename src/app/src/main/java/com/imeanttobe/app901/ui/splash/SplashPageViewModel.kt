package com.imeanttobe.app901.ui.splash

import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.api.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashPageViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        fun isLoggedIn(): Boolean = userRepo.isLoggedIn()
    }
