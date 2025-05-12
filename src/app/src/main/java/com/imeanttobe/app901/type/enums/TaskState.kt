package com.imeanttobe.app901.type.enums

sealed class TaskState {
    object Nothing : TaskState()
    object Loading : TaskState()
    object Success : TaskState()
    data class Error(val message: String) : TaskState()
}