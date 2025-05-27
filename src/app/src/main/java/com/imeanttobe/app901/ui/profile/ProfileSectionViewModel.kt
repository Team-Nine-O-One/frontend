package com.imeanttobe.app901.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSectionViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _concurrencyState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)
        private val _nicknameTextfieldValue = mutableStateOf("")

        // States
        val nickname: State<String> = derivedStateOf { userRepo.getNickname() }
        val concurrencyState: State<ConcurrencyState> = _concurrencyState
        val nicknameTextfieldValue: State<String> = _nicknameTextfieldValue

        // Functions
        fun logout() {
            userRepo.logout()
        }

        fun setNicknameTextfieldValue(newValue: String) {
            _nicknameTextfieldValue.value = newValue
        }

        fun updateNickname(newValue: String) {
            _concurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updateNickname(newValue)
                    .onSuccess {
                        _concurrencyState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _concurrencyState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }

        fun updatePassword(newValue: String) {
            _concurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updatePassword(newValue)
                    .onSuccess {
                        _concurrencyState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _concurrencyState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }
    }
