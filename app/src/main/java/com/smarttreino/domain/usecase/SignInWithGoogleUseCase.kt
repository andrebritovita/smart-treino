package com.smarttreino.domain.usecase

import com.smarttreino.core.util.EmptyFieldException
import com.smarttreino.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke (idToken: String) : Result <Boolean>{
        if (idToken.isBlank()) {
            return Result.failure(EmptyFieldException())
        }
        return authRepository.signInWithGoogle(idToken)
    }
}