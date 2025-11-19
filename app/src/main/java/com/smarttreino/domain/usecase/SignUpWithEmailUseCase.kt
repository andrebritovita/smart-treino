package com.smarttreino.domain.usecase

import com.smarttreino.core.util.EmptyFieldException
import com.smarttreino.core.util.PasswordTooShortException
import com.smarttreino.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    // Operador 'invoke' permite chamar a classe como uma função
    suspend operator fun invoke(name: String, email: String, password: String): Result<Boolean> {

        // Validação básica antes de chamar o servidor garantindo que não serão enviados dados em branco
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            return Result.failure(EmptyFieldException())
        }
        // Garante a segurança da senha
        if (password.length < 6) {
            return Result.failure(PasswordTooShortException())
        }

        // 2. Se passou nas regras, chama o Repositório
        return authRepository.signUpWithEmail(name, email, password)
    }
}