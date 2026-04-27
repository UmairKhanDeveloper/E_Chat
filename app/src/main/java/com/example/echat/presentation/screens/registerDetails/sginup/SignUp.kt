package com.example.echat.presentation.screens.registerDetails.sginup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.firebaseauth.AuthRepositoryImpl
import com.example.echat.firebaseauth.AuthViewModel
import com.example.echat.firebaserealtimedatabase.RealTimeDbRepository
import com.example.echat.firebaserealtimedatabase.RealTimeUser
import com.example.echat.firebaserealtimedatabase.RealTimeViewModel
import com.example.echat.presentation.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun SignUp(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val firebaseAuth = FirebaseAuth.getInstance()
    val authRepo = AuthRepositoryImpl(firebaseAuth, context)
    val authViewModel = AuthViewModel(authRepo)
    val databaseReference = FirebaseDatabase.getInstance().reference.child("users")
    val realTimeRepo = remember { RealTimeDbRepository(databaseReference, context) }
    val realTimeViewModel = remember { RealTimeViewModel(realTimeRepo) }

    val titleFont = FontFamily(Font(R.font.robot_black))

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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navController.navigate(Screens.Login.route) },
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(22.dp)
                    )
                }

            }

            Text(
                text = "Sign Up",
                fontFamily = titleFont,
                color = Color.White,
                fontSize = 34.sp, textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Create your account",
                fontFamily = titleFont,
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 38.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 28.dp, top = 10.dp, bottom = 140.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Fill in your details to create\n a new account.",
                    fontSize = 15.sp,
                    color = Color(0xFF1B526B),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    textAlign = TextAlign.Center
                )

                CustomAuthTextFieldEmail(
                    value = username,
                    onValueChange = { username = it },
                    hint = "Username",
                    icon = Icons.Default.Person,
                )

                CustomAuthTextFieldEmail(
                    value = email,
                    onValueChange = { email = it },
                    hint = "Email",
                    icon = Icons.Default.Email,
                )

                CustomAuthTextFieldPassword(
                    value = password,
                    onValueChange = { password = it },
                    hint = "Password",
                    icon = Icons.Default.Lock,
                )

                LoginButton(
                    isLoading = isLoading
                ) {

                    errorMessage = null

                    when {

                        username.isBlank() || email.isBlank() || password.isBlank() -> {
                            errorMessage = "All fields required"
                            return@LoginButton
                        }

                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> {
                            errorMessage = "Invalid Email"
                            return@LoginButton
                        }

                        password.length < 6 -> {
                            errorMessage = "Password must be 6+ characters"
                            return@LoginButton
                        }

                        else -> {

                            isLoading = true

                            FirebaseAuth.getInstance()
                                .createUserWithEmailAndPassword(
                                    email.trim(),
                                    password.trim()
                                )
                                .addOnCompleteListener { task ->

                                    if (task.isSuccessful) {

                                        val uid =
                                            FirebaseAuth.getInstance()
                                                .currentUser?.uid ?: ""

                                        val userData = RealTimeUser(
                                            key = uid,
                                            items = RealTimeUser.RealTimeItems(
                                                userFistName = username.trim(),
                                                email = email.trim(),
                                                password = password.trim()
                                            )
                                        )

                                        FirebaseDatabase
                                            .getInstance()
                                            .reference
                                            .child("users")
                                            .child(uid)
                                            .setValue(userData)
                                            .addOnSuccessListener {

                                                isLoading = false

                                                Toast.makeText(
                                                    context,
                                                    "Account Created Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                FirebaseAuth
                                                    .getInstance()
                                                    .signOut()

                                                navController.navigate(
                                                    Screens.Login.route
                                                ) {
                                                    popUpTo(
                                                        Screens.SignUp.route
                                                    ) {
                                                        inclusive = true
                                                    }
                                                }

                                            }
                                            .addOnFailureListener {

                                                isLoading = false
                                                errorMessage =
                                                    it.message ?: "Database save failed"
                                            }

                                    } else {

                                        isLoading = false
                                        errorMessage =
                                            task.exception?.message
                                                ?: "Signup failed"
                                    }

                                }

                        }
                    }

                }
            }
        }
    }
}


@Composable
fun CustomAuthTextFieldEmail(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    icon: ImageVector,
) {

    var isFocused by remember { mutableStateOf(false) }

    val focusedColor = Color(0xFF37ABFF)
    val unfocusedColor = Color(0xFF94A3B8)
    val focusedBg = Color(0xFFF8FAFC)
    val unfocusedBg = Color(0xFFF1F5F9)
    val textColor = Color(0xFF0F172A)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = textColor
        ),
        modifier = Modifier
            .padding(bottom = 20.dp)
            .width(280.dp)
            .height(48.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },

        decorationBox = { innerTextField ->

            val borderColor =
                if (isFocused) focusedColor
                else unfocusedColor

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.3.dp, borderColor, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isFocused) focusedBg
                        else unfocusedBg
                    )
                    .padding(horizontal = 12.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = borderColor
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(Modifier.weight(1f)) {

                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = unfocusedColor,
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun CustomAuthTextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    icon: ImageVector
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val focusedColor = Color(0xFF37ABFF)
    val unfocusedColor = Color(0xFF94A3B8)
    val focusedBg = Color(0xFFF8FAFC)
    val unfocusedBg = Color(0xFFF1F5F9)
    val textColor = Color(0xFF0F172A)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,

        visualTransformation =
            if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),

        textStyle = TextStyle(
            fontSize = 14.sp,
            color = textColor
        ),

        modifier = Modifier
            .padding(bottom = 24.dp)
            .width(280.dp)
            .height(48.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },

        decorationBox = { innerTextField ->

            val borderColor =
                if (isFocused)
                    focusedColor
                else
                    unfocusedColor

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.3.dp,
                        borderColor,
                        RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isFocused)
                            focusedBg
                        else
                            unfocusedBg
                    )
                    .padding(horizontal = 12.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = borderColor,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    Modifier.weight(1f)
                ) {

                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = unfocusedColor,
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }

                Icon(
                    imageVector =
                        if (passwordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,

                    contentDescription = null,
                    tint = borderColor,

                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            passwordVisible = !passwordVisible
                        }
                )
            }
        }
    )
}

@Composable
fun LoginButton(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF1E3CFF),
            Color(0xFF2ED3FF)
        )
    )

    Box(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .width(280.dp)
            .height(48.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false
            )
            .clip(RoundedCornerShape(10.dp))
            .background(gradientBrush)
            .clickable(enabled = !isLoading) { onClick() },
        contentAlignment = Alignment.Center
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.size(22.dp)
            )
        } else {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun SocialButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 28.dp)
            .width(280.dp)
            .height(48.dp)
            .border(
                1.dp,
                Color(0xFF9BA1A8),
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}