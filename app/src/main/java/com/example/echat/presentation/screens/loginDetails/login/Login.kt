package com.example.echat.presentation.screens.loginDetails.login

import PrefManager
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.googlefibase.GoogleAuthUiClient
import com.example.echat.googlefibase.SignInResult
import com.example.echat.googlefibase.SignInViewModel
import com.example.echat.presentation.navigation.Screens
import com.example.echat.presentation.screens.registerDetails.sginup.CustomAuthTextFieldEmail
import com.example.echat.presentation.screens.registerDetails.sginup.CustomAuthTextFieldPassword
import com.example.echat.presentation.screens.registerDetails.sginup.LoginButton
import com.example.echat.presentation.screens.registerDetails.sginup.SocialButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavController) {
    val context = LocalContext.current
    val prefManager = remember { PrefManager(context) }

    val titleFont = FontFamily(Font(R.font.robot_black))
    val coroutineScope = rememberCoroutineScope()
    val googleAuthUiClient = remember { GoogleAuthUiClient(context) }

    val viewModel: SignInViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    var isGoogleLoading by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            coroutineScope.launch {

                val signInResult: SignInResult =
                    googleAuthUiClient.signInWithIntent(
                        result.data ?: run {
                            isGoogleLoading = false
                            return@launch
                        }
                    )

                isGoogleLoading = false

                if (signInResult.data != null) {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()

                    navController.navigate(Screens.UserInformation.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }

                    viewModel.resetState()

                } else {
                    Toast.makeText(
                        context,
                        "Error: ${signInResult.errorMessage ?: "Unknown error"}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } else {
            isGoogleLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF0aaaf5))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFedf9ff))
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 28.dp, end = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Login",
                    fontFamily = titleFont,
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Text(
                text = "Welcome Back\nSign in to continue",
                fontFamily = titleFont,
                color = Color.White,
                fontSize = 24.sp,
                lineHeight = 36.sp,
                modifier = Modifier.padding(start = 28.dp, top = 10.dp, bottom = 100.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Sign in with your email and password\nto access your account.",
                    fontSize = 15.sp,
                    color = Color(0xFF1B526B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 30.dp)
                )

                CustomAuthTextFieldEmail(email, { email = it }, "Email", Icons.Default.Email)

                CustomAuthTextFieldPassword(
                    password,
                    { password = it },
                    "Password",
                    Icons.Default.Lock
                )

                errorMessage?.let {
                    Text(it, color = Color.Red, fontSize = 13.sp)
                }

                LoginButton(isLoading) {

                    errorMessage = null

                    when {
                        email.isBlank() || password.isBlank() ->
                            errorMessage = "Please enter email and password."

                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() ->
                            errorMessage = "Please enter valid email."

                        else -> {

                            isLoading = true

                            FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(email.trim(), password.trim())
                                .addOnCompleteListener { task ->

                                    isLoading = false

                                    if (task.isSuccessful) {

                                        Toast.makeText(
                                            context,
                                            "Login successful!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        navController.navigate(Screens.UserInformation.route) {
                                            popUpTo(Screens.Login.route) { inclusive = true }
                                        }

                                    } else {
                                        errorMessage = task.exception?.message
                                            ?: "Login failed"
                                    }
                                }
                        }
                    }
                }

                Text(
                    "Forgot Password?",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .clickable {  navController.navigate(Screens.ForgetPassword.route + "/$email")
                         },
                    color = Color(0xFF6B7580)
                )

                Text("Or", modifier = Modifier.padding(bottom = 16.dp))

                SocialButton(
                    text = "Continue with Google",
                    icon = R.drawable.google_color_svgrepo_com
                ) {
                    coroutineScope.launch {
                        isGoogleLoading = true
                        val intentSender = googleAuthUiClient.signIn()

                        intentSender?.let {
                            launcher.launch(IntentSenderRequest.Builder(it).build())
                        } ?: run {
                            isGoogleLoading = false
                        }
                    }

                }

                Row {
                    Text("Don’t have an account? ")
                    Text(
                        "Sign Up",
                        color = Color(0xFF0E33F3),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.SignUp.route)
                        }
                    )
                }
            }
        }

        if (isGoogleLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}