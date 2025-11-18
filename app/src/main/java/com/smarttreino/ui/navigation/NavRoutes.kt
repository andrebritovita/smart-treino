package com.smarttreino.ui.navigation

sealed class NavRoutes(val route: String) {
    object Auth : NavRoutes("auth")
    object Home : NavRoutes("home")
    object Workout : NavRoutes("workout")
    object Settings : NavRoutes("settings")
}