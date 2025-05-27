package com.imeanttobe.app901.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.enum.ProfileSectionSheetState
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSectionViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _changePasswordState = MutableStateFlow<ConcurrencyState>(ConcurrencyState.Default)
        private val _changeNicknameState = MutableStateFlow<ConcurrencyState>(ConcurrencyState.Default)
        private val _nicknameTextfieldValue = mutableStateOf("")
        private val _passwordTextfieldValue = mutableStateOf("")
        private val _bottomSheetState =
            mutableStateOf<ProfileSectionSheetState>(
                ProfileSectionSheetState.NONE,
            )

        // States
        val nickname: StateFlow<String> =
            userRepo.getNicknameFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = "",
            )
        val changePasswordState: StateFlow<ConcurrencyState> = _changePasswordState
        val changeNicknameState: StateFlow<ConcurrencyState> = _changeNicknameState
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

        fun resetConcurrencyState() {
            _changeNicknameState.value = ConcurrencyState.Default
            _changePasswordState.value = ConcurrencyState.Default
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
            if (_changeNicknameState.value is ConcurrencyState.Loading) {
                return
            }

            _changeNicknameState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updateNickname(_nicknameTextfieldValue.value)
                    .onSuccess {
                        _changeNicknameState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _changeNicknameState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }

        fun updatePassword() {
            if (_changePasswordState.value is ConcurrencyState.Loading) {
                return
            }

            _changePasswordState.value = ConcurrencyState.Loading

            viewModelScope.launch {
                userRepo
                    .updatePassword(_passwordTextfieldValue.value)
                    .onSuccess {
                        _changePasswordState.value = ConcurrencyState.Success()
                    }.onFailure {
                        _changePasswordState.value = ConcurrencyState.Failure(it.message ?: "Unknown Error")
                    }
            }
        }
    }
