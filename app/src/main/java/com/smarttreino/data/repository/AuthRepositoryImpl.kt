package com.smarttreino.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.smarttreino.core.util.EmailAlreadyInUseException
import com.smarttreino.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.smarttreino.core.constants.FirestoreCollections
import com.smarttreino.core.util.UserNotFoundException


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signInWithGoogle(idToken: String): Result<Boolean> {
        try {
            // 1. Transforma o token do Google em uma credencial do Firebase
            val credential = GoogleAuthProvider
                .getCredential(idToken, null)

            // 2. Faz o login no Auth usando essa credencial
            val authResult = firebaseAuth
                // Credencial do Google será associada ao email
                .signInWithCredential(credential)
                .await()

            // Recupera o usuário para pegar UID, Nome e Email
            val user = authResult.user ?: throw UserNotFoundException()
            val uid = user.uid

            // 3. Verificação de Segurança (Lógica de "Upsert")
            // Verifica se o usuário JÁ tem cadastro no Firestore
            val docSnapshot = firestore
                .collection(FirestoreCollections.USERS)
                .document(uid)
                .get()
                .await()

            if (!docSnapshot.exists()) {
                // SE NÃO EXISTE: Significa que é o primeiro acesso dele.
                // Precisamos criar a ficha no banco para controlar o "termsAccepted"
                val userMap = hashMapOf(
                    "uid" to uid,
                    "name" to (user.displayName ?: "Usuário Google"),
                    "email" to (user.email ?: ""),
                    "termsAccepted" to false
                )
                firestore
                    .collection(FirestoreCollections.USERS)
                    .document(uid)
                    .set(userMap)
                    .await()

            }
            return Result.success(true)
        } catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun signUpWithEmail(
        name: String,
        email: String,
        password: String
    ): Result<Boolean> {
        try {
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(
                    email,
                    password)
                .await()
            // Exception() genérica. Será mostrado uma mensagem de erro genérica no else do Viewmodel
            val uid = authResult.user?.uid ?: throw Exception()
            val userMap = hashMapOf(
                "uid" to uid,
                "name" to name,
                "email" to email,
                "termsAccepted" to false
            )
            firestore
                .collection(FirestoreCollections.USERS)
                .document(uid)
                .set(userMap)
                .await()
            return Result.success(true)
        } catch (e: Exception) {
            // 1. Verifica se a exceção é do Firebase
            if (e is FirebaseAuthException && e.errorCode =="ERROR_EMAIL_ALREADY_IN_USE"){
                // 2. Se for o erro de e-mail já em uso, retornamos uma exceção personalizada.
                // Isso sinaliza ao ViewModel/UI que o usuário deve tentar logar com Google.
                return Result.failure(EmailAlreadyInUseException())
            }
            // 3. Para qualquer outro erro (senha fraca, sem internet, etc.),
            // repassamos o erro original que o firebase lançou.
            // FirebaseNetworkException (Sem internet), FirebaseTooManyRequestsException (Muitas tentativas), etc.
            return Result.failure(e)
        }
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<Boolean> {
        try {
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}