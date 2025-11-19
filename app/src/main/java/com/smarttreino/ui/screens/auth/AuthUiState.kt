package com.smarttreino.ui.screens.auth

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: Int? = null, // Será usado Int porque será o ID da String,
    val isSuccess: Boolean = false
)
