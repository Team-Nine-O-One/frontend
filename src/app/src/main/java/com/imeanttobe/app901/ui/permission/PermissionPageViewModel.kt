package com.imeanttobe.app901.ui.permission

import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.api.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PermissionPageViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        val isLoggedIn: Boolean = userRepo.isLoggedIn()
    }
