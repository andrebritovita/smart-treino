package com.smarttreino.ui.components.reusables



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


// Deseja cada componente
@Composable
fun SmartTreinoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: Int,
    icon: ImageVector,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = label)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )
        },
        trailingIcon = {
            if (isPassword && onTogglePasswordVisibility != null) {
                IconButton(onClick = onTogglePasswordVisibility) {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    Icon(
                        imageVector = image,
                        contentDescription = "Alternar visibilidade da senha",
                        tint = Color.Gray
                    )
                }
            }
        },
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}