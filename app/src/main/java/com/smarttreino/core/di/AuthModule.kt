package com.smarttreino.core.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smarttreino.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Diz ao Hilt que isso é um módulo de configuração
@InstallIn(SingletonComponent::class) //Define onde e por quanto tempo os objetos criados vão viver.
object AuthModule {
    @Provides // Diz ao Hilt que essa função cria uma dependência
    @Singleton // Diz ao Hilt que essa será uma instância única no app inteiro
    fun provideFirebaseAuth () : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // Configurando login com o Google
    @Provides
    @Singleton
    fun provideGoogleSignInOptions (
        // Passa o contexto do app necessário para a configuração e criação do Google Sign-in
        @ApplicationContext context: Context
    ) : GoogleSignInOptions {
        return GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // Construtor
            .requestIdToken(context.getString(R.string.default_web_client_id)) // Token
            .requestEmail() // Pede o email
            .build() // Finaliza a criação
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        options: GoogleSignInOptions
    ) : GoogleSignInClient{
        return GoogleSignIn.getClient(context,options)
    }
}