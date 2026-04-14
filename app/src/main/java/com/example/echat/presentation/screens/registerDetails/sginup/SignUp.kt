package com.example.echat.presentation.screens.registerDetails.sginup

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens
import com.example.echat.presentation.screens.loginDetails.login.countries

@Composable
fun SignUp(navController: NavController) {
    val titleFont = FontFamily(Font(R.font.robot_black))


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.login___background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 28.dp, end = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .width(140.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Login",
                            color = Color(0xFF2196F3),
                            fontSize = 16.sp
                        )
                    }
                }

                Text(
                    text = "Register",
                    fontFamily = titleFont,
                    color = Color.White,
                    fontSize = 34.sp, modifier = Modifier
                )
            }

            Text(
                text = "Enter your \nmobile phone",
                fontFamily = titleFont,
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 38.sp, textAlign = TextAlign.Right,
                modifier = Modifier.align(alignment = Alignment.End)
                    .padding(end = 28.dp, top = 10.dp, bottom = 140.dp)
            )

            Text(
                text = "You will get a code via sms.",
                fontSize = 15.sp,
                color = Color(0xFF1B526B),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                textAlign = TextAlign.Center
            )

            SignPhoneOtp(navController)
        }
    }
}

@Composable
fun SignPhoneOtp(navController: NavController) {

    var checked by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var selectedCountry by remember {
        mutableStateOf(countries[0])
    }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(selectedCountry.flag, fontSize = 22.sp)

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color(0xFF2C2D3A)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    selectedCountry.dialCode,
                    color = Color(0xFF2C2D3A)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            BasicTextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                decorationBox = {
                    if (phone.isEmpty()) {
                        Text(
                            "00 0000 0000",
                            color = Color(0xFFB0B7C3),
                            fontSize = 16.sp
                        )
                    }
                    it()
                }
            )
        }

        Divider(
            thickness = 1.5.dp,
            color = if (phone.isNotEmpty()) Color(0xFF40C4FF) else Color(0xFF2C2D3A),
            modifier = Modifier.padding(
                top = 8.dp,
                start = 28.dp,
                end = 28.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF40C4FF)
                    )
                )

                Text("Remember me")
            }
            Box(
                modifier = Modifier
                    .clickable { navController.navigate(Screens.SignUpOtp.route) }
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        if (phone.isNotEmpty()) {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF40C4FF),
                                    Color(0xFF03A9F4)
                                )
                            )
                        } else {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF9ED9F6),
                                    Color(0xFF7CC4E8)
                                )
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(country.flag)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(country.name)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(country.dialCode)
                        }
                    },
                    onClick = {
                        selectedCountry = country
                        expanded = false
                    }
                )
            }
        }
    }
}
