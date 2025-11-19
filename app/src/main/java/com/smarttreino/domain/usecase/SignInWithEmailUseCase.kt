package com.smarttreino.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.smarttreino.core.util.EmptyFieldException
import com.smarttreino.core.util.PasswordTooShortException
import com.smarttreino.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke (email: String, password: String) : Result<Boolean> {
        if (email.isBlank() || password.isBlank()){
            return Result.failure(EmptyFieldException())
        }

        if (password.length < 6) {
            return Result.failure(PasswordTooShortException())
        }
        return authRepository.signInWithEmail(email, password)
    }
}