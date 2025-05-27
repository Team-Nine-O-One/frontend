package com.imeanttobe.app901.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.enum.ProfileSectionSheetState
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
        private val _passwordTextfieldValue = mutableStateOf("")
        private val _bottomSheetState =
            mutableStateOf<ProfileSectionSheetState>(
                ProfileSectionSheetState.NONE,
            )

        // States
        val nickname: State<String> = derivedStateOf { userRepo.getNickname() }
        val concurrencyState: State<ConcurrencyState> = _concurrencyState
        val nicknameTextfieldValue: State<String> = _nicknameTextfieldValue
        val passwordTextfieldValue: State<String> = _passwordTextfieldValue
        val bottomSheetState: State<ProfileSectionSheetState> = _bottomSheetState
        val isPasswordVisible = mutableStateOf(false)

        // Functions
        fun logout() {
            userRepo.logout()
        }

        fun setBottomSheetState(state: ProfileSectionSheetState) {
            _bottomSheetState.value = state
        }

        fun setNicknameTextfieldValue(newValue: String) {
            _nicknameTextfieldValue.value = newValue
        }

        fun setPasswordTextfieldValue(newValue: String) {
            _passwordTextfieldValue.value = newValue
        }

        fun setIsPasswordVisible(newValue: Boolean) {
            isPasswordVisible.value = newValue
        }

        fun updateNickname() {
            _concurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updateNickname(_nicknameTextfieldValue.value)
                    .onSuccess {
                        _concurrencyState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _concurrencyState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }

        fun updatePassword() {
            _concurrencyState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updatePassword(_passwordTextfieldValue.value)
                    .onSuccess {
                        _concurrencyState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _concurrencyState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }
    }
