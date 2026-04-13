package com.example.echat.presentation.navigation


import Splash
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.echat.presentation.screens.login.Login
import com.example.echat.presentation.screens.onboarding.Onboarding

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {

        composable(
            route = Screens.Splash.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            Splash(navController)
        }

        composable(
            route = Screens.OnBoarding.route,
            enterTransition = {
                androidx.compose.animation.fadeIn(
                    animationSpec = androidx.compose.animation.core.tween(600)
                )
            },
            exitTransition = {
                androidx.compose.animation.fadeOut(
                    animationSpec = androidx.compose.animation.core.tween(300)
                )
            }
        ) {
            Onboarding(navController)
        }
        composable(
            route = Screens.Login.route,
            enterTransition = {
                androidx.compose.animation.fadeIn(
                    animationSpec = androidx.compose.animation.core.tween(600)
                )
            },
            exitTransition = {
                androidx.compose.animation.fadeOut(
                    animationSpec = androidx.compose.animation.core.tween(300)
                )
            }
        ) {
            Login(navController)
        }
    }
}

sealed class Screens(val title: String, val route: String) {
    object Splash : Screens("Splash", "Splash")
    object OnBoarding : Screens("OnBoarding", "OnBoarding")
    object Login : Screens("Login", "Login")
}