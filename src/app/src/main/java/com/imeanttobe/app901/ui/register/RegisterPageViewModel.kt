package com.imeanttobe.app901.ui.register

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.R
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.util.Verifier.isValidEmail
import com.imeanttobe.app901.util.Verifier.isValidNickname
import com.imeanttobe.app901.util.Verifier.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterPageViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        // - Variables
        private val _email = mutableStateOf("")
        private val _nickname = mutableStateOf("")
        private val _password = mutableStateOf("")
        private val _confirmPassword = mutableStateOf("")

        // - Error messages
        private val _emailErrorMessage = mutableStateOf("")
        private val _nicknameErrorMessage = mutableStateOf("")
        private val _passwordErrorMessage = mutableStateOf("")
        private val _confirmPasswordErrorMessage = mutableStateOf("")

        // - Visibility
        private val _isPasswordVisible = mutableStateOf(false)
        private val _isConfirmPasswordVisible = mutableStateOf(false)

        // - State
        private val _registerState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)

        // - Text field enabled
        private val _emailEnabled = mutableStateOf(true)
        private val _nicknameEnabled = mutableStateOf(true)
        private val _passwordEnabled = mutableStateOf(true)
        private val _confirmPasswordEnabled = mutableStateOf(true)

        // Getter
        // - Variables
        val email: State<String> = _email
        val nickname: State<String> = _nickname
        val password: State<String> = _password
        val confirmPassword: State<String> = _confirmPassword

        // - Error messages
        val emailErrorMessage: State<String> = _emailErrorMessage
        val nicknameErrorMessage: State<String> = _nicknameErrorMessage
        val passwordErrorMessage: State<String> = _passwordErrorMessage
        val confirmPasswordErrorMessage: State<String> = _confirmPasswordErrorMessage

        // - Visibility
        val isPasswordVisible: State<Boolean> = _isPasswordVisible
        val isConfirmPasswordVisible: State<Boolean> = _isConfirmPasswordVisible

        // - State
        val registerState: State<ConcurrencyState> = _registerState

        // - Text field enabled
        val emailEnabled: State<Boolean> = _emailEnabled
        val nicknameEnabled: State<Boolean> = _nicknameEnabled
        val passwordEnabled: State<Boolean> = _passwordEnabled
        val confirmPasswordEnabled: State<Boolean> = _confirmPasswordEnabled

        // Setter
        fun setEmail(newValue: String) {
            _email.value = newValue
        }

        fun setNickname(newValue: String) {
            _nickname.value = newValue
        }

        fun setPassword(newValue: String) {
            _password.value = newValue
        }

        fun setConfirmPassword(newValue: String) {
            _confirmPassword.value = newValue
        }

        fun setIsPasswordVisible(newValue: Boolean) {
            _isPasswordVisible.value = newValue
        }

        fun setIsConfirmPasswordVisible(newValue: Boolean) {
            _isConfirmPasswordVisible.value = newValue
        }

        // Functions
        private fun setInputAvailability(value: Boolean) {
            _emailEnabled.value = value
            _nicknameEnabled.value = value
            _passwordEnabled.value = value
            _confirmPasswordEnabled.value = value
        }

        private fun verifyInput(context: Context): Boolean {
            var isValid = true

            if (!isValidEmail(email.value)) {
                _emailErrorMessage.value = context.getString(R.string.error_empty_email)
                isValid = false
            } else {
                _emailErrorMessage.value = ""
            }

            if (!isValidNickname(nickname.value)) {
                _nicknameErrorMessage.value = context.getString(R.string.error_empty_nickname)
                isValid = false
            } else {
                _nicknameErrorMessage.value = ""
            }

            if (!isValidPassword(password.value)) {
                _passwordErrorMessage.value = context.getString(R.string.error_empty_password)
                isValid = false
            } else {
                _passwordErrorMessage.value = ""
            }

            if (!isValidPassword(confirmPassword.value)) {
                _confirmPasswordErrorMessage.value = context.getString(R.string.error_empty_password)
                isValid = false
            } else {
                _confirmPasswordErrorMessage.value = ""
            }

            if (password.value != confirmPassword.value) {
                _confirmPasswordErrorMessage.value = context.getString(R.string.error_password_not_match)
                isValid = false
            } else {
                _confirmPasswordErrorMessage.value = ""
            }

            return isValid
        }

        fun register(context: Context) {
            _registerState.value = ConcurrencyState.Loading
            setInputAvailability(false)

            if (!verifyInput(context)) {
                _registerState.value = ConcurrencyState.Default
                setInputAvailability(true)
                return
            }

            viewModelScope.launch {
                val result =
                    userRepo.register(
                        nickname = nickname.value,
                        email = email.value,
                        password = password.value,
                    )

                if (result.isSuccess) {
                    _registerState.value = ConcurrencyState.Success()
                } else {
                    if (result.exceptionOrNull() != null && result.exceptionOrNull()!!.message != null) {
                        _registerState.value = ConcurrencyState.Failure(result.exceptionOrNull()!!.message!!)
                        setInputAvailability(true)
                    } else {
                        _registerState.value = ConcurrencyState.Failure("Unknown Error")
                        setInputAvailability(true)
                    }
                }
            }
        }
    }
