package com.smarttreino.ui.screens.auth

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.smarttreino.R
import com.smarttreino.core.util.EmailAlreadyInUseException
import com.smarttreino.core.util.EmptyFieldException
import com.smarttreino.core.util.PasswordTooShortException
import com.smarttreino.core.util.UserNotFoundException
import com.smarttreino.domain.usecase.SignInWithEmailUseCase
import com.smarttreino.domain.usecase.SignInWithGoogleUseCase
import com.smarttreino.domain.usecase.SignUpWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    // Função que a tela vai chamar quando o usuário clicar no botão
    fun onLogin (email: String, pass: String){
        viewModelScope.launch{
            _uiState.value = AuthUiState(isLoading = true)
            val resultSignIn = signInWithEmailUseCase(email, pass)

            if (resultSignIn.isSuccess){
                _uiState.value = AuthUiState(
                    isLoading = false,
                    isSuccess = true
                )
            } else {
                // Recupera a exceção de forma segura usando o Elvis Operator (?:)
                val exception = resultSignIn.exceptionOrNull() ?: Exception()
                _uiState.value = AuthUiState(
                    isLoading = false,
                    error = getErrorMessage(exception)
                )
            }
        }
    }

    fun onSignUp (name: String, email: String, pass: String){
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val resultSignUp = signUpWithEmailUseCase(name, email, pass)
            if (resultSignUp.isSuccess){
                _uiState.value = AuthUiState(
                    isLoading = false,
                    isSuccess= true
                )
            } else{
                val exception = resultSignUp.exceptionOrNull() ?: Exception()
                _uiState.value = AuthUiState (
                    isLoading = false,
                    error = getErrorMessage(exception)
                )
            }
        }
    }

    fun onGoogleSignIn (idToken: String){
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val resultGoogleSignIn = signInWithGoogleUseCase(idToken)
            if (resultGoogleSignIn.isSuccess){
                _uiState.value = AuthUiState(
                    isLoading = false,
                    isSuccess = true
                )
            } else{
                val exception = resultGoogleSignIn.exceptionOrNull() ?: Exception()
                _uiState.value = AuthUiState (
                    isLoading = false,
                    error = getErrorMessage(exception)
                )
            }
        }
    }

    // e: Throwable é o erro que veio do usecase/ authRepository/ authRepositoryImpl
    private fun getErrorMessage (e: Throwable) : Int {
        return when (e) {
            is EmptyFieldException ->  R.string.error_empty_fields
            is PasswordTooShortException -> R.string.error_password_too_short
            is UserNotFoundException -> R.string.error_google_user_not_found
            is EmailAlreadyInUseException -> R.string.error_email_already_in_use
            else -> R.string.error_generic
        }
    }
}