package com.imeanttobe.app901.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.api.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSectionViewModel
    @Inject
    constructor(
        userRepo: UserRepo,
    ) : ViewModel() {
        val nickname: State<String> = derivedStateOf { userRepo.getNickname() }
    }
