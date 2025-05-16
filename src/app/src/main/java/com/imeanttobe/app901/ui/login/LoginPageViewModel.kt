package com.imeanttobe.app901.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.api.repo.UserRepo
import com.imeanttobe.app901.data.type.ConcurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        private val _isEmailInvaild = mutableStateOf(false)
        private val _isPasswordInvaild = mutableStateOf(false)
        private val _isPasswordVisible = mutableStateOf(false)
        private val _loginState = mutableStateOf<ConcurrencyState>(ConcurrencyState.Initial)

        // Getter
        val email: State<String> = _email
        val password: State<String> = _password
        val isEmailInvaild: State<Boolean> = _isEmailInvaild
        val isPasswordInvaild: State<Boolean> = _isPasswordInvaild
        val isPasswordVisible: State<Boolean> = _isPasswordVisible
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
        fun login() {
            _loginState.value = ConcurrencyState.Loading
            if (!verifyInput()) return

            viewModelScope.launch {
                delay(10000)
                val result = userRepo.login(email.value, password.value)
                if (result.isSuccess) {
                    _loginState.value = ConcurrencyState.Success
                } else {
                    if (result.exceptionOrNull() != null && result.exceptionOrNull()!!.message != null) {
                        _loginState.value = ConcurrencyState.Failure(result.exceptionOrNull()!!.message!!)
                    } else {
                        _loginState.value = ConcurrencyState.Failure("Unknown Error")
                    }
                }
            }
        }

        private fun verifyInput(): Boolean = true
    }
