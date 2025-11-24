package com.smarttreino.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.smarttreino.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.smarttreino.ui.components.auth.AuthLoginContent
import com.smarttreino.ui.components.auth.AuthRegisterContent
import com.smarttreino.ui.components.reusables.LoginLinkText
import com.smarttreino.ui.components.reusables.SocialLoginButton

@Composable
fun AuthScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    // Estados locais para controlar o que o usuário digita
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    //Define qual tela mostrar
    // false = Mostra a Tela 1 (Login com Google/Email)
    // true = Mostra a Tela 2 (Tela de cadastro)
    var showForm by remember { mutableStateOf(false) }

    // Atua na tela 2
    // true = Mostra tela 2 sem nome e sem confirmar senha
    // false = Mostra tela 2 com nome e confirmar senha
    var isLoginMode by remember { mutableStateOf(true) }


    if (!showForm) {
        // TELA 1: BOTÕES
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color(0xFF1E1E1E))
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 1. LOGO (PNG)
            Image(
                painter = painterResource(id = R.drawable.logo_smart_treino),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                contentDescription = "Logo Smart Treino",
                modifier = androidx.compose.ui.Modifier
                    .width(500.dp)
                    .height(460.dp)
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(32.dp))

            // 2. BOTÃO GOOGLE
            Button(
                onClick = { /* Deixar vazio por enquanto */ },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_signin_google))
            }

            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            // 3. BOTÃO EMAIL
            OutlinedButton(
                onClick = {
                    // Torna o "if" falso (showForm) não é true, ou seja, é false
                    // Direciona para o Else
                    showForm = true
                    // Torna o if do else verdadeiro
                    isLoginMode = true
                },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_signin_email))
            }

            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            // 4. RODAPÉ (CADASTRO)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    color = Color(0xFFFFC107),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.text_no_account),
                    color = Color(0xFFFFC107),
                    fontSize = 14.sp
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    color = Color(0xFFFFC107),
                    thickness = 1.dp
                )
            }
            SocialLoginButton(
                text = stringResource(R.string.btn_signup),
                iconResId = R.drawable.add_pserson_24,
                borderColor = Color.Red,
                onClick = {
                    showForm = true
                    isLoginMode = false
                }
            )
        }
    } else {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color(0xFF1E1E1E))
        ) {
            if (isLoginMode) {
                AuthLoginContent(
                    email = email,
                    password = password,
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    passwordVisible = passwordVisible,
                    onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
                    onLoginClick = {
                        viewModel.onLogin(email, password)
                    },
                    onGoogleSignInClick = {
                        // viewModel.onGoogleSignIn(...) // Faremos depois
                    },
                    onNavigateToRegister = {
                        // Troca para o modo cadastro mantendo na tela de formulário
                        isLoginMode = false
                    }
                )
            } else {
                TextButton(onClick = { isLoginMode = true }) {
                    AuthRegisterContent(
                        name = name,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        passwordVisible = passwordVisible,
                        confirmPasswordVisible = confirmPasswordVisible,
                        onNameChange = {name = it},
                        onEmailChange = {email = it},
                        onPasswordChange = {password = it},
                        onConfirmPasswordChange = {confirmPassword = it},
                        onTogglePasswordVisibility = {passwordVisible = !passwordVisible},
                        onToggleConfirmPasswordVisibility = {confirmPasswordVisible = !confirmPasswordVisible},
                        onRegisterClick = {viewModel.onSignUp(name, email, password)},
                        onGoogleSignInClick = {}, // viewModel.onGoogleSignIn(...)
                        onLoginClick = {isLoginMode = true}
                    )
                }
            }
        }
    }
}

