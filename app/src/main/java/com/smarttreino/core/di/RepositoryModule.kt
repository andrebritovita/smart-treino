package com.smarttreino.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smarttreino.data.repository.AuthRepositoryImpl
import com.smarttreino.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // O App pede a interface, porém o Hilt devolve a implementação
    // Como aqui não existe código executável, a classe e o método podem
    // ser abstratos
    @Binds
    //O binds foi usado porque o Hilt sabe criar sozinho a classe authRepositoryImpl
    // Essa classe foi criada por mim e possui o @Inject
    @Singleton
    abstract fun bindAuthRepository (
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository
}