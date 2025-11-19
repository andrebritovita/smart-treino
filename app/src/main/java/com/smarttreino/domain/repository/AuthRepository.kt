package com.smarttreino.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    // Login com o Google
    suspend fun signInWithGoogle(
        idToken: String
    ): Result<Boolean>

    // Cadastro com Email
    suspend fun signUpWithEmail(
        name: String,
        email:String,
        password: String
    ): Result<Boolean>

    // Login com Email
    suspend fun signInWithEmail (
        email: String,
        password: String
    ) : Result<Boolean>

    // Deslogar
    fun signOut()

    // Verificar usuário atual
    fun getCurrentUser(): FirebaseUser?
}