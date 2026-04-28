package com.example.echat.presentation.navigation


import Splash
import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.echat.R
import com.example.echat.presentation.screens.addfriend.AddFriend
import com.example.echat.presentation.screens.forgetpassword.ForgetPassword
import com.example.echat.presentation.screens.groups.Groups
import com.example.echat.presentation.screens.home.Home
import com.example.echat.presentation.screens.loginDetails.login.Login
import com.example.echat.presentation.screens.more.More
import com.example.echat.presentation.screens.onboarding.Onboarding
import com.example.echat.presentation.screens.profile.Profile
import com.example.echat.presentation.screens.registerDetails.sginup.SignUp
import com.example.echat.presentation.screens.registerDetails.userinformation.UserInformation
import com.example.echat.presentation.screens.settingnotification.SettingNotification

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
        composable(
            route = Screens.SignUp.route,
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
            SignUp(navController)
        }
        composable(
            route = Screens.UserInformation.route,
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
            UserInformation(navController)
        }
        composable(
            route = Screens.Home.route,
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
            Home(navController)
        }
        composable(
            route = Screens.SettingNotification.route,
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
            SettingNotification(navController)
        }
        composable(
            route = Screens.ForgetPassword.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            ),
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
        ) { backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""

            ForgetPassword(
                navController = navController,
                email
            )
        }

        composable(
            route = Screens.Groups.route,
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
            Groups(navController)
        }
        composable(
            route = Screens.Profile.route,
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
            Profile(navController)
        }
        composable(
            route = Screens.More.route,
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
            More(navController)
        }
        composable(
            route = Screens.AddFriend.route,
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
            AddFriend(navController)
        }
    }
}

sealed class Screens(val title: String, val route: String, val icon: Int) {
    object Splash : Screens("Splash", "Splash", R.drawable.vector)
    object SettingNotification :
        Screens("SettingNotification", "SettingNotification", R.drawable.vector)

    object ForgetPassword : Screens("ForgetPassword", "ForgetPassword", R.drawable.vector)
    object OnBoarding : Screens("OnBoarding", "OnBoarding", R.drawable.vector)
    object Login : Screens("Login", "Login", R.drawable.vector)
    object SignUp : Screens("SignUp", "SignUp", R.drawable.vector)

    object UserInformation : Screens("UserInformation", "UserInformation", R.drawable.vector)
    object Home : Screens("Chats", "Home", R.drawable.vector)
    object Groups : Screens("Groups", "Groups", R.drawable.group)
    object Profile : Screens("Profile", "Profile", R.drawable.profile)
    object More : Screens("More", "More", R.drawable.menu)
    object AddFriend : Screens("AddFriend", "AddFriend", R.drawable.menu)
}


@Composable
fun ProBottomNav(navController: NavController) {

    val items = listOf(
        Screens.Home,
        Screens.Groups,
        Screens.Profile,
        Screens.More
    )

    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .shadow(8.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { screen ->

                val isSelected = currentRoute == screen.route

                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.1f else 1f,
                    label = ""
                )

                val contentColor by animateColorAsState(
                    targetValue = if (isSelected) Color.White else Color.Gray,
                    label = ""
                )

                val gradient = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF40C4FF),
                        Color(0xFF03A9F4)
                    )
                )


                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier
                            .scale(scale)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isSelected) gradient else SolidColor(Color.Transparent)
                            )
                            .padding(horizontal = 14.dp, vertical = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            tint = contentColor,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = screen.title,
                            color = contentColor,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomNav = when (currentRoute) {
        Screens.Splash.route,
        Screens.OnBoarding.route,
        Screens.Login.route,
        Screens.AddFriend.route,
        Screens.UserInformation.route -> false

        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                ProBottomNav(navController)
            }
        }
    ) {
        Navigation(navController)
    }
}


