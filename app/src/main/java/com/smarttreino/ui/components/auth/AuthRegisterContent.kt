package com.smarttreino.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.smarttreino.ui.components.AuthLoginContent

@Composable
fun AuthRegisterContent (
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onLoginClick: () -> Unit
){

    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_smart_treino),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            contentDescription = stringResource(R.string.content_description_logo),
            modifier = androidx.compose.ui.Modifier
                .width(300.dp)
                .height(220.dp)
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(32.dp))
        // NAME
        SmartTreinoTextField(
            value = name,
            onValueChange = onNameChange,
            label = R.string.label_name,
            icon = Icons.Default.AccountCircle,
        )
        Spacer(modifier = Modifier.height(12.dp))

        // EMAIL
        SmartTreinoTextField(
            value = email,
            onValueChange = onEmailChange,
            label = R.string.label_email,
            icon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(12.dp))

        // SENHA
        SmartTreinoTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = R.string.label_password,
            icon = Icons.Default.Lock,
            isPassword = true,
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(12.dp))
        // CONFIRMAR SENHA
        SmartTreinoTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = R.string.label_confirm_password,
            icon = Icons.Default.Lock,
            isPassword = true,
            passwordVisible = confirmPasswordVisible,
            onTogglePasswordVisibility = onToggleConfirmPasswordVisibility
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_signup))
        }

        Spacer(modifier = androidx.compose.ui.Modifier.width(4.dp))
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
                text = stringResource(R.string.text_have_account),
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
            text = stringResource(R.string.text_google),
            iconResId = R.drawable.ic_google_logo,
            borderColor = Color.Red,
            onClick = { onGoogleSignInClick }
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginLinkText(
            description = stringResource(R.string.text_already_have_account),
            action = stringResource(R.string.text_login),
            onClick = { onLoginClick() }
        )
    }

}