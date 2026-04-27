package com.example.echat.presentation.screens.forgetpassword

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgetPassword(navController: NavController, email: String) {

    val titleFont = FontFamily(Font(R.font.robot_black))

    var emailState by remember { mutableStateOf(email) }
    var message by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        // Top background
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color(0xFF0aaaf5))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xFFedf9ff))
            )
        }

        // Title
        Text(
            text = "Forget Password",
            fontFamily = titleFont,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 60.dp, start = 28.dp)
        )

        // Main Content (CENTER FIX)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(top = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter your email to receive reset link",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                placeholder = { Text("Enter Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    sendResetEmail(emailState) { msg ->
                        message = msg
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF0aaaf5))
            ) {
                Text("Send Reset Link")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = message,
                color = Color.Green
            )
        }
    }
}

fun sendResetEmail(email: String, callback: (String) -> Unit) {

    if (email.isEmpty()) {
        callback("Email cannot be empty")
        return
    }

    val auth = FirebaseAuth.getInstance()

    auth.sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback("Reset link sent to email")
                Log.d("RESET", "Email sent")
            } else {
                callback(task.exception?.message ?: "Error occurred")
                Log.e("RESET", task.exception?.message.toString())
            }
        }
}

