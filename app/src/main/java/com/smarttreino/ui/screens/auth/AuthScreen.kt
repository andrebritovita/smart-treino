package com.smarttreino.ui.screens.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.smarttreino.R

@Composable
fun AuthScreen(navController: NavHostController) {
    Text(text = stringResource(id = R.string.auth_screen_title))

}