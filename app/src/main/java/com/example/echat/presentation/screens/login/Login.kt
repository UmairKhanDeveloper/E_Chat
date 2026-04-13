package com.example.echat.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

data class Country(
    val name: String,
    val dialCode: String,
    val flag: String
)

val countries = listOf(
    Country("United Kingdom", "+44", "🇬🇧"),
    Country("United States", "+1", "🇺🇸"),
    Country("Pakistan", "+92", "🇵🇰"),
    Country("India", "+91", "🇮🇳"),
    Country("Canada", "+1", "🇨🇦"),
    Country("Australia", "+61", "🇦🇺"),
    Country("Germany", "+49", "🇩🇪"),
    Country("France", "+33", "🇫🇷"),
    Country("Saudi Arabia", "+966", "🇸🇦"),
    Country("UAE", "+971", "🇦🇪"),
    Country("Turkey", "+90", "🇹🇷"),
    Country("China", "+86", "🇨🇳"),
    Country("Japan", "+81", "🇯🇵"),
    Country("Brazil", "+55", "🇧🇷"),
    Country("South Africa", "+27", "🇿🇦")
)

@Composable
fun Login(navController: NavController) {
    val titleFont = FontFamily(Font(R.font.robot_black))


    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painter = painterResource(id = R.drawable.login___empty),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, bottom = 24.dp, start = 32.dp, end = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Login",
                    fontFamily = titleFont,
                    color = colorResource(id = R.color.white),
                    fontSize = 35.sp
                )

                Button(
                    onClick = {

                    }, modifier = Modifier
                        .size(width = 112.dp, height = 53.dp)
                        .clip(
                            RoundedCornerShape(28.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
                ) {
                    Text(
                        text = "Register",
                        color = colorResource(id = R.color.blue),
                        fontSize = 14.sp
                    )

                }
            }

            Text(
                text = "Enter your \n" +
                        "mobile phone",
                fontFamily = titleFont,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Medium,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(start = 32.dp, bottom = 203.dp)
            )
            Text(
                text = "You will get a code via SMS.",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = colorResource(id = R.color.primary_dark),
                lineHeight = 22.sp,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )
            PhoneInput()


        }
    }
}

@Composable
fun PhoneInput() {
    var checked by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val addNumber by remember { mutableStateOf(false) }

    var selectedCountry by remember {
        mutableStateOf(countries[0])
    }

    Column {

        Row(
            modifier = Modifier.padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {

                Text(selectedCountry.flag, fontSize = 22.sp)

                Spacer(modifier = Modifier.width(6.dp))
                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null, modifier = Modifier, tint = Color(0xFF2C2D3A)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

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
                keyboardActions = KeyboardActions(
                    onNext = {
                    }
                ),
                decorationBox = {
                    if (phone.isEmpty()) {
                        Text("00 0000 0000", color = Color.Gray)
                    }
                    it()
                }
            )
        }

        Divider(
            thickness = 2.dp,
            color = if (phone.isNotEmpty()) Color(0xFF40C4FF) else Color(0xFF2C2D3A),
            modifier = Modifier.padding(
                top = 8.dp,
                start = 32.dp,
                end = 32.dp
            )
        )



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {

                Checkbox(
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF40C4FF)),
                    checked = checked,
                    onCheckedChange = { checked = it }
                )

                Text("Remember me")
            }

            Box(
                modifier = Modifier
                    .size(55.dp)
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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


