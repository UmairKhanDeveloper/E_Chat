package com.example.echat.presentation.screens.creategroups

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateGroups(navController: NavController) {
    val titleFont = FontFamily(Font(R.font.robot_black))
    var group by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(containerColor = Color(0xFFFFFFFF), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Create Group",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.padding(start = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color(0xFFFFFFFF).copy(alpha = 0.20f), shape = CircleShape
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF135CAF)))
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            Text(
                text = "Name Group",
                fontFamily = titleFont,
                fontSize = 14.sp,
                color = Color(0xFF686A8A),
                modifier = Modifier.padding(top = 32.dp, start = 24.dp, bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD0D1DB),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {

                BasicTextField(
                    value = group,
                    onValueChange = {
                        group = it
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color(0xFF2C2D3A),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    decorationBox = { innerTextField ->

                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {

                            if (group.isEmpty()) {
                                Text(
                                    text = "Enter Name Group",
                                    color = Color(0xFF2C2D3A),
                                    fontSize = 16.sp
                                )
                            }

                            innerTextField()
                        }
                    }
                )
            }

            Text(
                text = "Members",
                fontFamily = titleFont,
                fontSize = 14.sp,
                color = Color(0xFF686A8A),
                modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .clickable { showBottomSheet = true }
                    .padding(start = 24.dp, end = 24.dp, bottom = 350.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFedf9ff)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.plus), contentDescription = "")
                    Text(
                        text = "Add members to group",
                        fontSize = 18.sp,
                        color = Color(0xFF03A9F4),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(onDismissRequest = {
                    showBottomSheet = false
                }, sheetState = sheetState)
                {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Add members to group",
                            fontSize = 18.sp,
                            color = Color(0xFF292929),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                }
            }
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(30.dp))
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF40C4FF),
                                    Color(0xFF03A9F4)
                                )
                            ),
                            shape = RoundedCornerShape(30.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Create Group",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }


    }

}