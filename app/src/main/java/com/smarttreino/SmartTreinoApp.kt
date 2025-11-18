package com.smarttreino

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Anotação que "ativa" o Hilt
// Gera todo o código necessário para criar a árvore de dependências do app e ligar os módulos desejados.
@HiltAndroidApp
class SmartTreinoApp : Application() {

}