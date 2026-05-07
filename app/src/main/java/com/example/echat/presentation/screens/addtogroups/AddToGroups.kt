package com.example.echat.presentation.screens.addtogroups

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToGroups(navController: NavController) {
    var search by remember { mutableStateOf("") }
    Scaffold(containerColor = Color(0xFFFFFFFF), topBar = {
        TopAppBar(title = {
            Text(
                text = "David Wayne",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }, navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(40.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        Color(0xFFFFFFFF),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }, actions = {
            Row(
                modifier = Modifier.padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .background(
                            Color(0xFFFFFFFF),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.videocall),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp)
                    )


                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .background(
                            Color(0xFFFFFFFF),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.audiocall),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp)
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFFFFF)))
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            item {
                Text(
                    text = "Add to Groups",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF686A8A),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 24.dp)
                )
            }

            item {

                TextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },

                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color(0xFFD0D1DB),
                            fontSize = 14.sp
                        )
                    },

                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            tint = Color(0xFFD0D1DB),
                            modifier = Modifier.size(22.dp)
                        )
                    },

                    singleLine = true,

                    shape = RoundedCornerShape(10.dp),

                    colors = TextFieldDefaults.colors(

                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        cursorColor = Color.White,

                        focusedTextColor = Color(0xFFD0D1DB),
                        unfocusedTextColor =Color(0xFFD0D1DB)
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD0D1DB),
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }
        }

    }

}