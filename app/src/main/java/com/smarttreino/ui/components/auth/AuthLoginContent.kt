package com.smarttreino.ui.components.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttreino.R
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.smarttreino.ui.components.reusables.SmartTreinoTextField

// Recebe os dados de quem detém a memória e os coloca no lugar certo
// Define onde cada componente vai ficar (layout)
@Composable
fun AuthLoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onGoogleSignInClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // 1. Título
        Image(
            painter = painterResource(id = R.drawable.logo_smart_treino),
            contentScale = ContentScale.Crop,
            contentDescription = "Logo Smart Treino",
            modifier = Modifier
                .width(300.dp)
                .height(220.dp)
        )

        //EMAIL
        Spacer(modifier = Modifier.height(32.dp))
        SmartTreinoTextField(
            value = email,
            onValueChange = onEmailChange,
            label = R.string.label_email,
            icon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        //SENHA
        SmartTreinoTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = R.string.label_password,
            icon = Icons.Default.Lock,
            isPassword = true,
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 4. BOTÃO ENTRAR
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_login))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BOTÃO DE ENTRAR COM O GOOGLE
        OutlinedButton(
            onClick = onGoogleSignInClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_signin_google))
        }

        // TEXTO PARA IR PARA A TELA DE CADASTRO
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.text_no_account),
                fontSize = 13.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                onClick = { onNavigateToRegister() }
            ) {
                Text(text = stringResource(id = R.string.text_signup_here))
            }
        }
    }
}
