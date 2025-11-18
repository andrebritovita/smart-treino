package com.smarttreino.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smarttreino.ui.screens.auth.AuthScreen
import com.smarttreino.ui.screens.home.HomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Auth.route) {
        composable(NavRoutes.Auth.route) { AuthScreen(navController) }
        composable(NavRoutes.Home.route) { HomeScreen() }
    }
}