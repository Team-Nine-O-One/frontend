package com.imeanttobe.app901.ui.dev

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.imeanttobe.app901.api.repo.FakeMemoRepoImpl
import com.imeanttobe.app901.data.model.Memo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Initial : AuthState()

    object Loading : AuthState()

    object Success : AuthState()

    data class Error(
        val message: String,
    ) : AuthState()
}

@HiltViewModel
class DevPageViewModel
    @Inject
    constructor(
        private var memoRepo: FakeMemoRepoImpl,
    ) : ViewModel() {
        private val auth: FirebaseAuth = Firebase.auth
        private val _memos: MutableState<List<Memo>> = mutableStateOf(emptyList())
        val memos: State<List<Memo>> = _memos

        private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
        val authState: StateFlow<AuthState> = _authState.asStateFlow()

        init {
            if (auth.currentUser != null) {
                _authState.value = AuthState.Success
            }
        }

        fun register() {
            _authState.value = AuthState.Loading

            viewModelScope.launch {
                auth
                    .createUserWithEmailAndPassword("aaa@aaa.com", "abc123")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.value = AuthState.Success
                        } else {
                            _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
                        }
                    }
            }
        }

        fun login() {
            _authState.value = AuthState.Loading

            viewModelScope.launch {
                auth
                    .signInWithEmailAndPassword("aaa@aaa.com", "abc123")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.value = AuthState.Success
                        } else {
                            _authState.value =
                                AuthState.Error(task.exception?.message ?: "Unknown error")
                        }
                    }
            }
        }

        fun getAllMemos() {
            viewModelScope.launch {
                memoRepo.getAllMemos().fold(
                    onSuccess = {
                        _memos.value = it
                    },
                    onFailure = {
                        Log.e("DevPageViewModel", "getAllMemos: ${it.message}")
                    },
                )
            }
        }
    }
