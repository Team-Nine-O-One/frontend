package com.imeanttobe.app901.ui.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.R
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.util.Verifier.isValidEmail
import com.imeanttobe.app901.util.Verifier.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel
    @Inject
    constructor(
        private val userRepo: UserRepo,
    ) : ViewModel() {
        // Variables
        private val _email = mutableStateOf("")
        private val _password = mutableStateOf("")
        private val _emailErrorMessage = mutableStateOf("")
        private val _passwordErrorMessage = mutableStateOf("")
        private val _isPasswordVisible = mutableStateOf(false)
        private val _emailEnabled = mutableStateOf(true)
        private val _passwordEnabled = mutableStateOf(true)
        private val _loginState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Default)

        // Getter
        val email: State<String> = _email
        val password: State<String> = _password
        val emailErrorMessage: State<String> = _emailErrorMessage
        val passwordErrorMessage: State<String> = _passwordErrorMessage
        val isPasswordVisible: State<Boolean> = _isPasswordVisible
        val emailEnabled: State<Boolean> = _emailEnabled
        val passwordEnabled: State<Boolean> = _passwordEnabled
        val loginState: State<ConcurrencyState> = _loginState

        // Setter
        fun setEmail(newValue: String) {
            _email.value = newValue
        }

        fun setPassword(newValue: String) {
            _password.value = newValue
        }

        fun setIsPasswordVisible(newValue: Boolean) {
            _isPasswordVisible.value = newValue
        }

        // Functions
        private fun setInputAvailability(value: Boolean) {
            _emailEnabled.value = value
            _passwordEnabled.value = value
        }

        private fun verifyInput(context: Context): Boolean {
            var isValid = true

            if (!isValidEmail(email.value)) {
                _emailErrorMessage.value = context.getString(R.string.error_empty_email)
                isValid = false
            } else {
                _emailErrorMessage.value = ""
            }

            if (!isValidPassword(password.value)) {
                _passwordErrorMessage.value = context.getString(R.string.error_empty_password)
                isValid = false
            } else {
                _passwordErrorMessage.value = ""
            }

            return isValid
        }

        fun login(context: Context) {
            _loginState.value = ConcurrencyState.Loading
            setInputAvailability(false)

            if (!verifyInput(context)) {
                _loginState.value = ConcurrencyState.Default
                setInputAvailability(true)
                return
            }

            viewModelScope.launch {
                val result = userRepo.login(email.value, password.value)
                if (result.isSuccess) {
                    _loginState.value = ConcurrencyState.Success()
                } else {
                    if (result.exceptionOrNull() != null && result.exceptionOrNull()!!.message != null) {
                        _loginState.value = ConcurrencyState.Failure(result.exceptionOrNull()!!.message!!)
                        setInputAvailability(true)
                    } else {
                        _loginState.value = ConcurrencyState.Failure("Unknown Error")
                        setInputAvailability(true)
                    }
                }
            }
        }
    }
