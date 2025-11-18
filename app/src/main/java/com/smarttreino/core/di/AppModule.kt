package com.smarttreino.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module // Ensina o Hilt a criar objetos atuando como uma fábrica central de dependências
@InstallIn(SingletonComponent::class)
object AppModule {

}


