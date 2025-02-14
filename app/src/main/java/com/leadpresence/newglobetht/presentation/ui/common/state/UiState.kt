package com.leadpresence.newglobetht.presentation.ui.common.state

sealed class UiState<out T> {
    data object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}