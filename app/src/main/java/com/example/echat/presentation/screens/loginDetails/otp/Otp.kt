package com.example.echat.presentation.screens.loginDetails.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@Composable
fun Otp(navController: NavController) {

    val titleFont = FontFamily(Font(R.font.robot_black))

    val otpValues = remember {
        mutableStateListOf("", "", "", "")
    }

    val isOtpComplete = otpValues.all { it.isNotEmpty() }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.login___background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

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
                    fontSize = 34.sp
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .size(width = 110.dp, height = 50.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "Register",
                        color = Color(0xFF2196F3),
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                text = "Enter OTP Code",
                fontFamily = titleFont,
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 28.dp, top = 10.dp)
            )

            Text(
                text = "Sent to : (+44) 20 1234 5629",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 28.dp, bottom = 140.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null
                )
                Text(
                    text = "00 : 45",
                    color = Color(0xFF2C2D3A),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "Resend Code",
                    color = Color(0xFFA5BAC4),
                    textDecoration = TextDecoration.Underline
                )
            }
            Box(
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OTPTextField(otpValues = otpValues)
            }

            Box(
                modifier = Modifier.padding(end = 28.dp)
                    .align(Alignment.End)
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        if (isOtpComplete) {
                            Brush.linearGradient(
                                listOf(Color(0xFF40C4FF), Color(0xFF03A9F4))
                            )
                        } else {
                            Brush.linearGradient(
                                listOf(Color(0xFF9ED9F6), Color(0xFF7CC4E8))
                            )
                        }
                    )
                    .clickable(enabled = isOtpComplete) {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
@Composable
fun OTPTextField(
    otpValues: SnapshotStateList<String>
) {
    val otpLength = otpValues.size
    val focusRequesters = List(otpLength) { FocusRequester() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(otpLength) { index ->

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                BasicTextField(
                    value = otpValues[index],
                    onValueChange = { value ->
                        if (value.length <= 1) {
                            otpValues[index] = value

                            if (value.isNotEmpty() && index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                            if (value.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .width(40.dp)
                        .focusRequester(focusRequesters[index]),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(
                            if (otpValues[index].isNotEmpty())
                                Color(0xFF00BCD4)
                            else
                                Color.LightGray
                        )
                )
            }
        }
    }
}