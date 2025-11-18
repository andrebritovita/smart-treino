package com.smarttreino

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.smarttreino.ui.screens.home.HomeScreen
import com.smarttreino.ui.theme.SmartTreinoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Possibilita a Activity receber dependências do Hilt
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTreinoTheme {
                HomeScreen()
            }
        }
    }
}

